package info.seanrice.allergyID.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import info.seanrice.allergyID.R;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ImageView gitHubImage = (ImageView)findViewById(R.id.gitHubLogo);
        ImageView linkedInImage = (ImageView)findViewById(R.id.linkedInImage);
        
        TextView textView = (TextView) findViewById(R.id.info_text);

         textView.setText(Html.fromHtml(getString(R.string.about_text)));

        gitHubImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGithub();
            }
        });

        linkedInImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLinkedin();
            }
        });
    }
    public void goToGithub(){
        Intent intentToLaunch = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.github.com/seanrice1/FoodAllergyScanner"));
        startActivity(intentToLaunch);
        finish();
    }
    public void goToLinkedin(){
        Intent intentToLaunch = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/sean-rice"));
        startActivity(intentToLaunch);
        finish();
    }
}
