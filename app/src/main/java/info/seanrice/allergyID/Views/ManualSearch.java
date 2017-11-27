package info.seanrice.allergyID.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import info.seanrice.allergyID.R;
import info.seanrice.allergyID.Views.Result;

public class ManualSearch extends AppCompatActivity {

    private EditText upc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_search);

        upc = (EditText) findViewById(R.id.manualUpc);

    }

    public void search(View view){
        Intent intent = new Intent(this, Result.class);
        intent.putExtra("UPC",upc.getText().toString());
        startActivity(intent);
    }
}
