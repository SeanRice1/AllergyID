package info.seanrice.allergyID.Presenters;

import info.seanrice.allergyID.Models.AllergyData;
import info.seanrice.allergyID.Views.AdapterView;

/**
 * Created by Sean Rice on 11/25/2017.
 */

public class EditAllergiesPresenter {

    private AllergyData allergyData;
    private AdapterView view;

    public EditAllergiesPresenter(AllergyData allergyData){
        this.allergyData = allergyData;
    }
    public EditAllergiesPresenter(AllergyData allergyData, AdapterView view){
        this.allergyData = allergyData;
        this.view = view;
    }
    public String[] getAllergyNames(){
        return allergyData.getAllergyNames();
    }

    public void setCheck(String allergyName){
        view.setCheckBox(allergyData.isChecked(allergyName));
    }

    public void updateAllergy(String allergyName){
        allergyData.sharedPreferencesUpdater(allergyName);
    }
}
