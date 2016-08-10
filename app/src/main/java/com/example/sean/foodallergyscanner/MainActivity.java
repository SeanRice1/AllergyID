package com.example.sean.foodallergyscanner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AllergyData.createArr(this);

        //temporary for AllergySettings creation
        Button editAllergies = (Button) findViewById(R.id.editAllergiesBut);
        editAllergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditAllergies.class);
                startActivity(intent);
            }
        });


        //temporary for scanner
        Button scanner = (Button) findViewById(R.id.scanButton);
        scanner.setOnClickListener(new View.OnClickListener() {
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
