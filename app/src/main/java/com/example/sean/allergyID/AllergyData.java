package com.example.sean.allergyID;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class AllergyData {

    private static Context context;
    protected static SharedPreferences sharedPreferences;

    public static final String[] allergyNames = {"Cereals", "Coconut", "Corn", "Egg", "Fish",
            "Gluten", "Lactose", "Milk", "Peanuts", "Sesame seeds", "Shellfish", "Soybean"
            , "Sulfites", "Tree Nuts", "Wheat"};
    public static int[] values = new int[15];
    public static AllergyModel[] allergyArr = new AllergyModel[15];
    public static ArrayList<String> currentlyChecked= new ArrayList<>();

    public static void createArr(Context contextInput) {
        context = contextInput;
        sharedPreferencesSetUp();
        createAllergyArray();

        //TODO: IMPORTANT - is this needed??
        for (int x = 0; x < allergyNames.length; x++)
            allergyArr[x] = new AllergyModel(allergyNames[x], values[x]);
    }

    //TODO: use a map here instead of allergyModel (important)
    //populates the local allergyArr with the allergies that were stored in
    //SharedPreferences. This can be done much more efficiently without using the
    //AllergyModel object, and using a map instead. This is an important fix
    public static void createAllergyArray(){
        for(int x = 0; x<allergyNames.length;x++){
            allergyArr[x]= new AllergyModel(allergyNames[x],values[x]);
        }
    }

    //Sets default values for shared preferences if not created yet,
    //else retrieves the stored values and puts the name of each checked
    //allergy into the currentlyChecked list.
    public static void sharedPreferencesSetUp() {
         sharedPreferences = context.getSharedPreferences(
                 "com.example.sean.FoodAllergyScanner.AllergyData", Context.MODE_PRIVATE);

        /* IF shared preferences is empty (by checking if the first allergy is stored) THEN */
        /* set default values                                                               */
        if (!sharedPreferences.contains("Cereals")) {
            for (int x = 0; x < 15; x++) {
                sharedPreferences.edit().putString(allergyNames[x], "0").apply();
                values[x]=0;
            }
        } else {
            //else get stored values
            for (int x = 0; x < 15; x++) {
                values[x]=Integer.parseInt(sharedPreferences.getString(allergyNames[x],"0"));
                if (Integer.parseInt(sharedPreferences.getString(allergyNames[x],"0"))==1)
                    currentlyChecked.add(allergyNames[x]);
            }
        }
    }

    //Called by the CustomAdapter when the user changes one of their allergy
    //preferences.
    public static void sharedPreferencesUpdater(int position){
        if(values[position]==0) {
            values[position] = 1;
            sharedPreferences.edit().putString(allergyNames[position],"1").apply();
        }
        else {
            values[position] = 0;
            sharedPreferences.edit().putString(allergyNames[position],"0").apply();

            //removes the name of the allergy from the currently check list
            while(currentlyChecked.contains(allergyNames[position])) {
                //TODO: figure out why there are multiple (low priority)
                currentlyChecked.remove(allergyNames[position]);
                //inputs for currentlyChecked
            }

        }

    }
}
