package info.seanrice.allergyID.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import info.seanrice.allergyID.R;


public class SplashScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView splashText = (TextView) findViewById(R.id.splashText);
        Button proceedButton = (Button)findViewById(R.id.proceedButton);

        splashText.setText(Html.fromHtml(getString(R.string.disclaimer)));

        final SharedPreferences proceedResult = getApplicationContext().getSharedPreferences
                ("info.seanrice.allergyID.Views.SplashScreen",MODE_PRIVATE);


    if(proceedButton != null) {
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Writes in shared preferences that the user accepted the disclaimer
                proceedResult.edit().putBoolean("acceptedDisclaimer", true).apply();
                Intent intent = new Intent(getApplicationContext(), MainView.class);
                startActivity(intent);
            }
        });

    }

    }


}
