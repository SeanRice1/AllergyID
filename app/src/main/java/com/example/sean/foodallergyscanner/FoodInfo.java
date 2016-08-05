package com.example.sean.foodallergyscanner;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class FoodInfo {

    private static final String API_KEY ="2mdxcrznzd43t3wcc75nch7y";
    private static String upcCode = "076840100477";//Example upc code


    public static void setUpcCode(String code){
        upcCode = code;
    }
    public String makeApiRequestUrl(){
        return "http://api.foodessentials.com/labelarray?u="+upcCode+"&sid=9fe4492b-492e-4336-86b8-7d278e02aa51&n=2&s=0&f=json&api_key="+API_KEY;
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
    public  void getFoodInfo(){

        CallApi call = new CallApi();
        String results=null;
        try {
            results=call.execute(makeApiRequestUrl()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(results);
    }
}
