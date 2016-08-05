package com.example.sean.foodallergyscanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class EditAllergies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_allergies);

        ListView lv = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.allergy_row,AllergyData.allergyArr);

        lv.setAdapter(customAdapter);

    }
}
