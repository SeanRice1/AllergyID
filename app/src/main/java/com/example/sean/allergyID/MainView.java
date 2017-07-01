package com.example.sean.allergyID;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

    //TODO: optimize buttom feedback ( style possibly)
    //TODO: customize fonts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        startingSplash();
        AllergyData.createArr(this);
        startMainView();
    }

    /* Starts the splashScreen view if its the first time opening the app */
    private void startingSplash(){
        SharedPreferences proceedResult =
                getSharedPreferences("com.example.sean.allergyID.SplashScreen", MODE_PRIVATE);

        if(!proceedResult.contains("acceptedDisclaimer")){
            Intent intent = new Intent(getApplicationContext(),SplashScreen.class);
            startActivity(intent);
        }
    }
    private void startMainView(){
        setContentView(R.layout.activity_main_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            //TODO:add share view
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void scannerButton(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            requestCameraPermission();
        }
        else{
                new AlertDialog.Builder(this)
                        .setTitle("Internet Connection needed")
                        .setMessage("Internet connection is needed to properly use this app ")
                        .setCancelable(true).show();
        }
    }
    public void manualSearchButton(View view){
        Intent intent = new Intent(getApplicationContext(),ManualSearch.class);
        startActivity(intent);
    }
    public void requestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(MainView.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainView.this,
                    new String[]{Manifest.permission.CAMERA}, 0);
        else {
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
                    new AlertDialog.Builder(this)
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
