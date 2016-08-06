package com.example.sean.foodallergyscanner;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<AllergyModel>{

    private AllergyModel[] resource;
    private Context context;

    public CustomAdapter(Context context, int textViewResourceID, AllergyModel[] resource) {
        super(context, textViewResourceID, resource);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.allergy_row, null);

        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
        checkBox.setText(resource[position].getName());
        checkBox.setChecked(resource[position].isChecked());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resource[position].switchedCheckedValue();
                AllergyData.sharedPreferencesUpdater(position);
            }
        });

        return rowView;
    }
}
