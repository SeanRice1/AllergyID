package com.example.sean.foodallergyscanner;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AllergyData {

    private static Context context;
    protected static SharedPreferences sharedPreferences;

    //temporary for AllergySettings creation
    public static final String[] names = {"Cereals", "Coconut", "Corn", "Egg", "Fish",
            "Gluten", "Lactose", "Milk", "Peanuts", "Sesame seeds", "Shellfish", "Soybean"
            , "Sulfites", "Tree Nuts", "Wheat"};
    public static int[] values = new int[15];


    public static AllergyModel[] allergyArr = new AllergyModel[15];

    public static void createArr(Context contextInput) {
        context = contextInput;
        sharedPreferencesSetUp();
        createAllergyArray();


        for (int x = 0; x < names.length; x++)
            allergyArr[x] = new AllergyModel(names[x], values[x]);

    }

    /**
     * Create an Allergy array with the info stored
     * in SharedPreferences/ default info
     */
    public static void createAllergyArray(){
        for(int x = 0; x<names.length;x++){
            allergyArr[x]= new AllergyModel(names[x],values[x]);
        }
    }
    public static void sharedPreferencesSetUp() {
         sharedPreferences = context.getSharedPreferences("com.example.sean.FoodAllergyScanner.AllergyData", Context.MODE_PRIVATE);

        //If shared preferences is empty, set default values
        if (!sharedPreferences.contains("Cereals")) {
            for (int x = 0; x < 15; x++) {
                sharedPreferences.edit().putString(names[x], "0").commit();
                values[x]=0;
            }
        } else {
            //else get stored values
            for (int x = 0; x < 15; x++) {
                values[x]=Integer.parseInt(sharedPreferences.getString(names[x],"0"));
            }

        }
    }

    public static void sharedPreferencesUpdater(int position){
        if(values[position]==0) {
            values[position] = 1;
            sharedPreferences.edit().putString(names[position],"1").commit();
        }
        else {
            values[position] = 0;
            sharedPreferences.edit().putString(names[position],"0").commit();
        }

    }
}
