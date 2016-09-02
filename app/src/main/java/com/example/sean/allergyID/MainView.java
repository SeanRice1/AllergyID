package com.example.sean.allergyID;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //TODO: AllergyID
    //TODO: optimize buttom feedback ( style possibly) dont have multiple xml files and vibration
    //TODO: add ripple animation to buttons (low priority)
    //TODO: redo app primary colors, customize fonts


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("");

        startingSplash();
        AllergyData.createArr(this);

        setContentView(R.layout.activity_main_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void startingSplash(){
        SharedPreferences proceedResult = getSharedPreferences("com.example.sean.FoodAllergyScanner.SplashScreen",MODE_PRIVATE);

        //Checks to see if the user accepted the disclaimer previously
        if(!proceedResult.contains("acceptedDisclaimer")){
            Intent intent = new Intent(getApplicationContext(),SplashScreen.class);
            startActivity(intent);
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handles navigation view item clicks
        int id = item.getItemId();

        if (id == R.id.nav_edit) {
            Intent intent = new Intent(getApplicationContext(),EditAllergies.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(getApplicationContext(),Info.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            //TODO:do something with this
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void scannerButton(View view) {
        requestCameraPermission();
    }

    public void manualSearchButton(View view){
        Intent intent = new Intent(getApplicationContext(),ManualSearch.class);
        startActivity(intent);
    }
    public void requestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(MainView.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainView.this, new String[]{Manifest.permission.CAMERA}, 0);

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
