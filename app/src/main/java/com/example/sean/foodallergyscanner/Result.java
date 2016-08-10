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
        TextView title = (TextView) findViewById(R.id.title);

        result1.setAlpha(0f);
        result2.setAlpha(0f);



        Intent intent = getIntent();
        FoodInfo foodInfo = new FoodInfo();
        foodInfo.setUpcCode(intent.getStringExtra("UPC"));
        foodInfo.getFoodInfo();

     if(!foodInfo.upcNotFound()) {

        title.setText(foodInfo.getProductName());

         if(!foodInfo.containsYourAllergen().isEmpty()) {
             String text ="Your product contains the following items that youre" +
                                     " allergic to: " + foodInfo.containsYourAllergen();
             result1.setText(text);
             result1.animate().alpha(1f).setDuration(1000).start();
         }

         if(!foodInfo.mayContainYourAllergen().isEmpty()) {
             String text1 = "Your product may contain the following items that youre" +
                     " allergic to: " + foodInfo.mayContainYourAllergen();
             result2.setText(text1);
             result2.animate().alpha(1f).setDuration(1000).start();
         }
     }
        else
         title.setText("Product not found");


    }
}
