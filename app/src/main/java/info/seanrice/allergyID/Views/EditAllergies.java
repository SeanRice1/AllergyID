package info.seanrice.allergyID.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import info.seanrice.allergyID.Data.LocalData;
import info.seanrice.allergyID.Data.LocalDataI;
import info.seanrice.allergyID.Models.AllergyData;
import info.seanrice.allergyID.Presenters.EditAllergiesPresenter;
import info.seanrice.allergyID.R;

public class EditAllergies extends AppCompatActivity {

    private EditAllergiesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_allergies);
        LocalDataI prefs = new LocalData(getApplicationContext(),
                "com.example.sean.FoodAllergyScanner.AllergyData", Context.MODE_PRIVATE);
        presenter = new EditAllergiesPresenter(prefs);
        ListView lv = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.allergy_row,
                presenter.getAllergyNames());

        lv.setAdapter(customAdapter);

    }
}
