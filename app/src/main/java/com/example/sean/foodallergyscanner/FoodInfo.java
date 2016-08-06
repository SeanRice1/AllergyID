package com.example.sean.foodallergyscanner;


import android.os.AsyncTask;

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
import java.util.concurrent.ExecutionException;

public class FoodInfo {

    private static final String API_KEY ="2mdxcrznzd43t3wcc75nch7y";
    private static String upcCode = "050200559105";//Example upc code
    private static ArrayList<String> listOfPresentAllergens = new ArrayList<>();
    private static ArrayList<String> listOfPossibleAllergens = new ArrayList<>();

    public static void setUpcCode(String code){
        upcCode = code;
    }
    public String makeApiRequestUrl(){
        return "http://api.foodessentials.com/label?u="+ upcCode +"&sid=af520b23-4799-4a54-bc94-488484fa8ac0&appid=demoApp_01&f=json&long=38.6300&lat=90.2000&api_key="+API_KEY;
    }

    class CallApi extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... requestUrl) {

            try {
                URL url = new URL(requestUrl[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder builder = new StringBuilder();
                String read;

                while((read = reader.readLine())!= null){
                    builder.append(read);
                    builder.append(System.lineSeparator());
                }
                return builder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e){
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
        parseInputJSON(results);


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

        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }
}
