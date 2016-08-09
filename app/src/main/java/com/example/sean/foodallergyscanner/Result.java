package com.example.sean.foodallergyscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView result1 = (TextView)findViewById(R.id.results1);
        TextView result2 = (TextView)findViewById(R.id.results2);

        Intent intent = getIntent();
        FoodInfo foodInfo = new FoodInfo();
        FoodInfo.setUpcCode(intent.getStringExtra("UPC"));
        foodInfo.getFoodInfo();

        result1.setText( "Your product contains the following items that youre" +
                " allergic to: "+ FoodInfo.containsYourAllergen());

        result2.setText( "Your product may contain the following items that youre" +
                " allergic to: "+ FoodInfo.mayContainYourAllergen());

    }
}
