package info.seanrice.allergyID.Models;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import info.seanrice.allergyID.AllergyData;


 public class FoodInfo extends Activity {
    //TODO: add more allergies (low priority)

    private static final String API_KEY ="2mdxcrznzd43t3wcc75nch7y";
    private  String upcCode = "";
    private  ArrayList<String> listOfPresentAllergens = new ArrayList<>();
    private  ArrayList<String> listOfPossibleAllergens = new ArrayList<>();
    private String productName ="";
    private boolean upcNotFound = false;
    private boolean noInternet = false;

    void setUpcCode(String code){
        upcCode = code;
    }
    private String formatApiRequestUrl(){

        return "http://api.foodessentials.com/label?u="+ upcCode
                +"&sid=af520b23-4799-4a54-bc94-488484fa8ac0&appid=demoApp_01&f=json&long=38.6300&lat=90.2000&api_key="+API_KEY;
    }

    //Called by getFoodInfo(), opens a connection with the api and returns the JSON
    class CallApi extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... requestUrl) {
            try {
                URL url = new URL(requestUrl[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000); /*TODO: this needs to be tested */

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder builder = new StringBuilder();
                String read = reader.readLine();
                if(read.equals("{}"))
                    upcNotFound = true;
                else{
                    while (read != null) {
                        builder.append(read);
                        builder.append(System.lineSeparator());
                        read = reader.readLine();
                    }
                }

                connection.disconnect();

                return builder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (java.net.SocketTimeoutException e){
                noInternet = true;
                finish();
            }
            catch (IOException e){
                noInternet = true;
                finish();
                e.printStackTrace();
            }

            return null;
        }
    }
    void getFoodInfo(){
        CallApi call = new CallApi();

        String results = null;
        try {
            results = call.execute(formatApiRequestUrl()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!noInternet()) {
            parseInputJSON(results);
        }
    }

    private void parseInputJSON(String input){

        try {
            JSONObject obj = new JSONObject(input);
            System.out.println(obj);
            JSONArray array = obj.getJSONArray("allergens");

            for(int x = 0; x<array.length();x++){
                JSONObject temp = array.getJSONObject(x);
                if(Integer.parseInt(temp.getString("allergen_value"))==1)
                    listOfPossibleAllergens.add(temp.getString("allergen_name"));
                if(Integer.parseInt(temp.getString("allergen_value"))==2)
                    listOfPresentAllergens.add(temp.getString("allergen_name"));

            }

            //cut off the name of the company for the product
            productName = obj.getString("product_name").substring(obj.getString("product_name").indexOf(',')+1).toLowerCase();

        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    String containsYourAllergen(){
        String result="";
        Set<String> results = new HashSet<>();

        for(String a: listOfPresentAllergens) {
            Log.i("listofpresent", a);
            if(AllergyData.allergyMap.containsKey(a))
                if(AllergyData.allergyMap.get(a) == 1)
                    results.add(a);
        }

        result = results.toString();
        result=result.replace("[","");
        result=result.replace("]","");
        return result;
    }
    String mayContainYourAllergen(){
        String result ="";
        Set<String> results = new HashSet<>();

        for(String a: listOfPossibleAllergens){
            if(AllergyData.allergyMap.containsKey(a))
                if(AllergyData.allergyMap.get(a) == 1)
                    results.add(a);
        }

    result = results.toString();
    result=result.replace("[","");
    result=result.replace("]","");
    return result;
    }
    boolean upcNotFound (){
        return upcNotFound;
    }
    boolean noInternet(){
        return noInternet;
    }
    String getProductName(){
        return productName;
    }
}

