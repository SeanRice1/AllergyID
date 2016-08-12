package com.example.sean.foodallergyscanner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton editAllergies;
    ImageButton scannerButton;
    ImageButton manualSearchButton;
    ImageButton infoButton;
    //TODO: now available as a general allergy scanner?
    //TODO: optimize drawable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manualSearchButton = (ImageButton) findViewById(R.id.manualSearchButton);
        infoButton = (ImageButton) findViewById(R.id.infoButton);
        editAllergies = (ImageButton) findViewById(R.id.editAllergiesBut);
        scannerButton = (ImageButton) findViewById(R.id.scannerButton);

        AllergyData.createArr(this);
        //temporary for AllergySettings creation
        editAllergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditAllergies.class);
                startActivity(intent);
            }
        });


        //temporary for scanner
        scannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //TODO: create scenarios where user rejects permission
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},0);

                    }
                else{
                        Intent intent = new Intent(getApplicationContext(),Scanner.class);
                        startActivity(intent);
                    }


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[]
            ,int[] grantResults){

        Log.i("onRequestPermission","on call before anything");

        switch (requestCode){
            case 0: {
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Log.i("onRequestPermission","before createScanner");
                    Intent intent = new Intent(getApplicationContext(),Scanner.class);
                    startActivity(intent);

                }
            }


        }

    }
}
