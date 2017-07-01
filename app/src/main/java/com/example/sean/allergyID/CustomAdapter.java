package com.example.sean.allergyID;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

public class CustomAdapter extends ArrayAdapter<String>{

    private Context context;

    public CustomAdapter(Context context, int textViewResourceID, String[] allergyNames) {
        super(context, textViewResourceID, allergyNames);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflates the rows specified by allergy_row, and sets whether they are checked
        //and their names
        View rowView = inflater.inflate(R.layout.allergy_row, null);
        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
        checkBox.setText(AllergyData.allergyNames[position]);

        if(AllergyData.allergyMap.get(AllergyData.allergyNames[position]) == 1)
            checkBox.setChecked(true);
        else
            checkBox.setChecked(false);

        //When a checkbox is clicked sharedPreferences and
        //AllergyData values array and specific Allergy object
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AllergyData.allergyMap.get(AllergyData.allergyNames[position]) == 1)
                    AllergyData.allergyMap.put(AllergyData.allergyNames[position], 0);
                else
                    AllergyData.allergyMap.put(AllergyData.allergyNames[position], 1);

                AllergyData.sharedPreferencesUpdater(position);
            }
        });
        return rowView;

    }
}
