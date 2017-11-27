package info.seanrice.allergyID.Data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sean Rice on 11/24/2017.
 */

public class LocalData implements LocalDataI{

    private SharedPreferences db;

    public LocalData(Context context, String name, int mode){
        db = context.getSharedPreferences(name, mode);
    }

    @Override
    public boolean checkForVal(String val) {
        if(db.contains(val))
            return true;
        else
            return false;
    }

    @Override
    public String getStrVal(String key) {
        return db.getString(key, null);
    }

    @Override
    public void addStrKeyVal(String key, String val) {
        db.edit().putString(key, val).apply();
    }
}
