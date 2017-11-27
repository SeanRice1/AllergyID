package info.seanrice.allergyID.Views;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import info.seanrice.allergyID.Data.LocalData;
import info.seanrice.allergyID.Data.LocalDataI;
import info.seanrice.allergyID.Models.AllergyData;
import info.seanrice.allergyID.Presenters.EditAllergiesPresenter;
import info.seanrice.allergyID.R;

public class CustomAdapter extends ArrayAdapter<String> implements AdapterView{

    private EditAllergiesPresenter presenter;
    private Context context;
    private CheckBox checkBox;
    CustomAdapter(Context context, int textViewResourceID, String[] ALLERGY_NAMES) {
        super(context, textViewResourceID, ALLERGY_NAMES);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LocalDataI prefs = new LocalData(context.getApplicationContext(),
                "com.example.sean.FoodAllergyScanner.AllergyData", Context.MODE_PRIVATE);
        presenter = new EditAllergiesPresenter(new AllergyData(prefs), this);
        //inflates the rows specified by allergy_row, sets whether they are checked
        //and their names
        final String[] allergyNames = presenter.getAllergyNames();
        View rowView = inflater.inflate(R.layout.allergy_row, null);
        checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
        checkBox.setText(allergyNames[position]);
        presenter.setCheck(allergyNames[position]);

        //When a checkbox is clicked sharedPreferences and
        //AllergyData values array and specific Allergy object
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateAllergy(allergyNames[position]);
            }
        });
        return rowView;

    }

    public void setCheckBox(boolean show){
        checkBox.setChecked(show);
    }
}
