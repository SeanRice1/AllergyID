package info.seanrice.allergyID.Data;

import android.content.Context;

/**
 * Created by Sean Rice on 11/24/2017.
 */

public interface LocalDataI {
    boolean checkForVal(String val);
    String getStrVal(String key);
    void addStrKeyVal(String key, String val);
}
