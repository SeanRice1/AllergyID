package com.example.sean.allergyID;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

public class AllergyData {

    private static Context context;
    protected static SharedPreferences sharedPreferences;

    public static HashMap<String, Integer> allergyMap = new HashMap<>(15);
    public static final String[] allergyNames = {"Cereals", "Coconut", "Corn", "Egg", "Fish",
            "Gluten", "Lactose", "Milk", "Peanuts", "Sesame seeds", "Shellfish", "Soybean"
            , "Sulfites", "Tree Nuts", "Wheat"};
    public static ArrayList<String> currentlyChecked= new ArrayList<>();

    public static void createArr(Context contextInput) {
        context = contextInput;
        for(int i = 0; i<15; i++){
            allergyMap.put(allergyNames[i], 0);
        }
        sharedPreferencesSetUp();
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
            }
        } else {
            //else get stored values
            for (int x = 0; x < 15; x++) {
                int storedVal = Integer.parseInt(sharedPreferences.getString(allergyNames[x],"0"));
                allergyMap.put(allergyNames[x], storedVal);

                if (Integer.parseInt(sharedPreferences.getString(allergyNames[x],"0"))==1)
                    currentlyChecked.add(allergyNames[x]);
            }
        }
    }

    //Called by the CustomAdapter when the user changes one of their allergy
    //preferences.
    public static void sharedPreferencesUpdater(int position){
        if(allergyMap.get(allergyNames[position])==0) {
            allergyMap.put(allergyNames[position],1);
            sharedPreferences.edit().putString(allergyNames[position],"1").apply();
        }
        else {
            allergyMap.put(allergyNames[position],0);
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
