package info.seanrice.allergyID.Views;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import info.seanrice.allergyID.Data.LocalData;
import info.seanrice.allergyID.Data.LocalDataI;
import info.seanrice.allergyID.Presenters.HomePresenter;
import info.seanrice.allergyID.R;

public class MainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeView {

    //TODO: optimize button feedback (style possibly)
    //TODO: customize fonts
    private HomePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalDataI prefs = new LocalData(this);
        presenter = new HomePresenter(this, prefs);
        checkSplash();
        //AllergyData.setupAllergyMap(this);

        setTitle("");
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
            startActivity(new Intent(this, EditAllergies.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, Info.class));
        } else if (id == R.id.nav_share) {
            //TODO:add share view
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void scannerButton() {
        presenter.scannerButton();
    }
    public void manualSearchButton(){
        presenter.manualSearchButton();
    }
    public void requestCamera(){
        ActivityCompat.requestPermissions(MainView.this,
                new String[]{Manifest.permission.CAMERA}, 0);
    }
    public void checkSplash(){
        presenter.checkSplash();
    }
    public void toScanner(){
        startActivity(new Intent(this, Scanner.class));
    }
    public void toMSearch(){
        startActivity(new Intent(this, ManualSearch.class));
    }
    public void toSplash(){
        startActivity(new Intent(this, SplashScreen.class));
    }

    @Override
    public boolean checkCamera() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }

    @Override
    public boolean checkInternet() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()
                == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()
                        == NetworkInfo.State.CONNECTED)
            return true;
        else
            return false;
    }

    public void alertNoInternet(){
        new AlertDialog.Builder(this)
                .setTitle("Internet Connection needed")
                .setMessage("Internet connection is needed to properly use this app ")
                .setCancelable(true).show();
    }
    public void cameraComplain(){
        new AlertDialog.Builder(this)
                .setTitle("Access Needed")
                .setMessage("You need to grant access for the camera in order to use the " +
                        "product scanner")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

}
