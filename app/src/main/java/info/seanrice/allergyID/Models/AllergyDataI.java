package info.seanrice.allergyID.Models;

import java.util.ArrayList;

/**
 * Created by SeanRice on 11/29/17.
 */

public interface AllergyDataI {
    void sharedPreferencesUpdater(String allergyName);
    ArrayList<String> getAllergies();
    String[] getAllergyNames();
    boolean isChecked(String key);
}
