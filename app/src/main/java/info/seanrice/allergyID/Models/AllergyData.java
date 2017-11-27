package info.seanrice.allergyID.Models;

import java.util.HashMap;

import info.seanrice.allergyID.Data.LocalDataI;

public class AllergyData {

    private LocalDataI prefs;

    private HashMap<String, Integer> allergyMap = new HashMap<>(15);
    private final String[] ALLERGY_NAMES = {"Cereals", "Coconut", "Corn", "Egg", "Fish",
            "Gluten", "Lactose", "Milk", "Peanuts", "Sesame seeds", "Shellfish", "Soybean"
            , "Sulfites", "Tree Nuts", "Wheat"};
    private final String PREFS_NAME = "com.example.sean.FoodAllergyScanner.AllergyData";

    public AllergyData(LocalDataI prefs){
        this.prefs = prefs;
        for(int i = 0; i<15; i++){
            allergyMap.put(ALLERGY_NAMES[i], 0);
        }
        sharedPreferencesSetUp();
    }

    private void sharedPreferencesSetUp() {
        /* IF shared preferences is empty (by checking if the first allergy is stored) THEN */
        /* set default values                                                               */
        if (!prefs.checkForVal("Cereals")) {

                for (int x = 0; x < ALLERGY_NAMES.length; x++) {
                    prefs.addStrKeyVal(ALLERGY_NAMES[x], "0");
                }

        } else {
                //else get stored values
                for (int x = 0; x < ALLERGY_NAMES.length; x++) {
                    int storedVal = Integer.parseInt(prefs.getStrVal(ALLERGY_NAMES[x]));
                    allergyMap.put(ALLERGY_NAMES[x], storedVal);
                }
        }
    }

    //Called by the CustomAdapter when the user changes one of their allergy
    //preferences.
    public void sharedPreferencesUpdater(String allergyName){

        if(allergyMap.get(allergyName)==0) {
            allergyMap.put(allergyName,1);
            prefs.addStrKeyVal(allergyName,"1");
        }
        else {
            allergyMap.put(allergyName,0);
            prefs.addStrKeyVal(allergyName,"0");

            }
        }

    public String[] getAllergyNames(){
         return ALLERGY_NAMES;
    }
    public boolean isChecked(String key){
        if(allergyMap.get(key) == 0)
            return false;
        else
            return true;
    }
}
