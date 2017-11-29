package info.seanrice.allergyID.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import info.seanrice.allergyID.Data.LocalDataI;

public class AllergyData implements AllergyDataI{

    private LocalDataI prefs;

    private HashMap<String, Boolean> allergyMap = new HashMap<>(15);
    private final String[] ALLERGY_NAMES = {"Cereals", "Coconut", "Corn", "Egg", "Fish",
            "Gluten", "Lactose", "Milk", "Peanuts", "Sesame seeds", "Shellfish", "Soybean"
            , "Sulfites", "Tree Nuts", "Wheat"};
    private final String PREFS_NAME = "com.example.sean.FoodAllergyScanner.AllergyData";

    public AllergyData(LocalDataI prefs){
        this.prefs = prefs;
        for(int i = 0; i<15; i++){
            allergyMap.put(ALLERGY_NAMES[i], false);
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
                    if(storedVal == 0)
                        allergyMap.put(ALLERGY_NAMES[x], false);
                    else
                        allergyMap.put(ALLERGY_NAMES[x], true);
                }
        }
    }

    //Called by the CustomAdapter when the user changes one of their allergy
    //preferences.
    public void sharedPreferencesUpdater(String allergyName){

        if(!allergyMap.get(allergyName)) {
            allergyMap.put(allergyName,true);
            prefs.addStrKeyVal(allergyName,"1");
        }
        else {
            allergyMap.put(allergyName,false);
            prefs.addStrKeyVal(allergyName,"0");

            }
        }

    @Override
    public ArrayList<String> getAllergies() {
        ArrayList<String> result = new ArrayList<>();
        Set<Map.Entry<String, Boolean>> set = allergyMap.entrySet();
        for(Map.Entry<String, Boolean> entry: set){
            if(entry.getValue())
                result.add(entry.getKey());
        }

        return result;
    }

    public String[] getAllergyNames(){
         return ALLERGY_NAMES;
    }
    public boolean isChecked(String key){
        return allergyMap.get(key);
    }
}
