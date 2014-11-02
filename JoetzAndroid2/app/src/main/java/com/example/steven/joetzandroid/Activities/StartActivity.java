package com.example.steven.joetzandroid.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steven.joetzandroid.R;


public class StartActivity extends Activity {

    private final String TAG = "StartActitivty";
    private ImageView imgJoetz;
    private TextView beschrijvingTxtView;
    private ImageView binnenlandView;
    private ImageView inleefView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        imgJoetz = (ImageView)findViewById(R.id.imageView);
        beschrijvingTxtView = (TextView)findViewById(R.id.beschrijvingTxtView);

        beschrijvingTxtView.setText("Vakanties voor kinderen en jongeren. Provincie West-Vlaanderen");

        binnenlandView = (ImageView)findViewById(R.id.binnelandImage);
        inleefView = (ImageView)findViewById(R.id.inleefImage);
    }

    public void binnenLandClicked(View view)
    {
        Log.d(TAG,"BinnenlandClicked");
    }
    public void inleefClicked(View view)
    {
        Log.d(TAG,"inleefClicked");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
