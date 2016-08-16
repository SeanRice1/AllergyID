package com.example.sean.foodallergyscanner;


import android.app.Activity;
import android.content.Intent;
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
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FoodInfo extends Activity {
    //TODO: add more allergies (low priority)

    private static final String API_KEY ="2mdxcrznzd43t3wcc75nch7y";
    private  String upcCode = "";
    private  ArrayList<String> listOfPresentAllergens = new ArrayList<>();
    private  ArrayList<String> listOfPossibleAllergens = new ArrayList<>();
    private String productName ="";
    private boolean upcNotFound = false;
    private boolean noInternet = false;

    public void setUpcCode(String code){
        upcCode = code;
    }
    public String makeApiRequestUrl(){

        return "http://api.foodessentials.com/label?u="+ upcCode
                +"&sid=af520b23-4799-4a54-bc94-488484fa8ac0&appid=demoApp_01&f=json&long=38.6300&lat=90.2000&api_key="+API_KEY;
    }

    class CallApi extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... requestUrl) {
            try {
                URL url = new URL(requestUrl[0]);


                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
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

                return builder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e){
                noInternet = true;
                finish();
                e.printStackTrace();
            }
            
            return null;
        }
    }
    public void getFoodInfo(){
        CallApi call = new CallApi();

        String results=null;
        try {
            results=call.execute(makeApiRequestUrl()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!noInternet()) {
            parseInputJSON(results);
        }

    }
    public void parseInputJSON(String input){

        try {
            JSONObject obj = new JSONObject(input);

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

    public  String containsYourAllergen(){
        //TODO:Make more efficient
        String result="";
        HashSet<String> results = new HashSet<>();

        for(String a: listOfPresentAllergens) {
            Log.i("listofpresent", a);
            for (String b : AllergyData.currentlyChecked) {
                Log.i("currentlyChecked", b);
                if (a.equals(b)) {
                    results.add(a);
                }
            }
        }

        result = results.toString();
        result=result.replace("[","");
        result=result.replace("]","");
        return result;
    }
    public  String mayContainYourAllergen(){
        //TODO:Make more efficient
        String result ="";
        HashSet<String> results = new HashSet<>();

        for(String a: listOfPossibleAllergens){
            for( String b: AllergyData.currentlyChecked){
                if(a.equals(b))
                    results.add(a);
            }
        }

    result = results.toString();
    result=result.replace("[","");
    result=result.replace("]","");
    return result;
    }
    public boolean upcNotFound (){
        return upcNotFound;
    }
    public boolean noInternet(){
        return noInternet;
    }
    public String getProductName(){
        return productName;
    }
}

