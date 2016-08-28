package com.example.sean.foodallergyscanner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Result extends AppCompatActivity {
//TODO: get better result icons
    //TODO:creating two intents to this activity??
    TextView result1;
    TextView result2;
    TextView title;
    ImageView headerImg;
    LinearLayout resultsLayout;
    FoodInfo foodInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result1 = (TextView)findViewById(R.id.results1);
        result2 = (TextView)findViewById(R.id.results2);
        title = (TextView) findViewById(R.id.title);
        headerImg = (ImageView) findViewById(R.id.headerImg);
        resultsLayout = (LinearLayout) findViewById(R.id.resultsLayout);
        Vibrator vibrate = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        vibrate.vibrate(100);

        result1.setAlpha(0f);
        result2.setAlpha(0f);

        getFoodInfo();
        setText();
    }

    public void getFoodInfo(){
        Intent intent = getIntent();
        foodInfo = new FoodInfo();
        Log.i("UPC","The UPC code is: " + intent.getStringExtra("UPC"));
        foodInfo.setUpcCode(intent.getStringExtra("UPC"));
        foodInfo.getFoodInfo();

    }

    public void setText(){
        if(!foodInfo.upcNotFound() && !foodInfo.noInternet()) {

            title.setText(foodInfo.getProductName());

            if(foodInfo.containsYourAllergen().isEmpty() && foodInfo.mayContainYourAllergen().isEmpty()){
                String text2 = foodInfo.getProductName()+ " may be safe";
                result1.setText(text2);
                result1.animate().alpha(1f).setDuration(1000).start();
                headerImg.setImageResource(R.drawable.ic_check_black_24dp);
                resultsLayout.setBackgroundColor(Color.parseColor("#FF007D19"));
            }

            if(!foodInfo.mayContainYourAllergen().isEmpty()) {
                String text1 = foodInfo.getProductName()+" may contain the following items that you're" +
                        " allergic to: " + foodInfo.mayContainYourAllergen();
                result2.setText(text1);
                result2.animate().alpha(1f).setDuration(1000).start();
                headerImg.setImageResource(R.drawable.ic_close_black_24dp);
                resultsLayout.setBackgroundColor(Color.parseColor("#FFC34E00"));
            }

            if(!foodInfo.containsYourAllergen().isEmpty()) {
                String text =foodInfo.getProductName()+ " contains the following items that you're" +
                        " allergic to: " + foodInfo.containsYourAllergen();
                result1.setText(text);
                result1.animate().alpha(1f).setDuration(1000).start();
                headerImg.setImageResource(R.drawable.ic_close_black_24dp);
                resultsLayout.setBackgroundColor(Color.parseColor("#FFAA0000"));
            }
        }
        else if(!foodInfo.noInternet()){
            title.setText("Product not found");
            resultsLayout.setBackgroundColor(Color.parseColor("#ffff4444"));
            headerImg.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_36dp);
        }
        else if (foodInfo.noInternet()){
            title.setText("Please connect to the internet");
            resultsLayout.setBackgroundColor(Color.parseColor("#ffff4444"));
            headerImg.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_36dp);
        }
    }
}
