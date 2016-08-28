package com.example.sean.foodallergyscanner;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton editAllergies;
    ImageButton scannerButton;
    ImageButton manualSearchButton;
    ImageButton infoButton;


    //TODO: optimize buttom feedback ( style possibly) dont have multiple xml files
    //TODO: now available as a general allergy scanner?
    //TODO: optimize drawable
    //TODO: add ripple animation to buttons (low priority)
    //TODO: Redo mainActivity UI, and redo app primary colors

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startingSplash();

        manualSearchButton = (ImageButton) findViewById(R.id.manualSearchButton);
        infoButton = (ImageButton) findViewById(R.id.infoButton);
        editAllergies = (ImageButton) findViewById(R.id.editAllergiesBut);
        scannerButton = (ImageButton) findViewById(R.id.scannerButton);

        AllergyData.createArr(this);
    }

    public void startingSplash(){
        SharedPreferences proceedResult = getSharedPreferences("com.example.sean.FoodAllergyScanner.SplashScreen",MODE_PRIVATE);

        //Checks to see if the user accepted the disclaimer previously
        if(!proceedResult.contains("acceptedDisclaimer")){
            Intent intent = new Intent(getApplicationContext(),SplashScreen.class);
            startActivity(intent);
        }

    }

    public void allergySettingsButton(View view){
        Intent intent = new Intent(getApplicationContext(), EditAllergies.class);
        startActivity(intent);
    }

    public void manualSearchButton(View view){
        Intent intent = new Intent(getApplicationContext(),ManualSearch.class);
        startActivity(intent);
    }

    public void infoButton(View view){
        Intent intent = new Intent(getApplicationContext(),Info.class);
        startActivity(intent);
    }
    public void scannerButton(View view) {
        requestCameraPermission();
    }

    public void requestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 0);

        } else {
            //Permission has already been granted
            Intent intent = new Intent(getApplicationContext(), Scanner.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[]
            , int[] grantResults) {

        Log.i("onRequestPermission", Boolean.toString(grantResults[0] == PackageManager.PERMISSION_DENIED));

        switch (requestCode) {
            case 0: {
                //if permission is accepted by the user
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(getApplicationContext(), Scanner.class);
                    startActivity(intent);

                }

            }
            case 1: {
                //if permission is denied by the user
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {

                    AlertDialog accessDenied = new AlertDialog.Builder(this)
                            .setTitle("Access Needed")
                            .setMessage("You need to grant access for the camera in order to use the " +
                                    "product scanner")
                            .setCancelable(false)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestCameraPermission();
                                }
                            }).show();

                }


            }

        }
    }


    }
