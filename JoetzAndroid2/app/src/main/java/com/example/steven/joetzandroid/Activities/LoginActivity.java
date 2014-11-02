package com.example.steven.joetzandroid.Activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.steven.joetzandroid.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends Activity implements View.OnClickListener{

    private final String TAG= "LoginActivity";
    private Firebase fireRef;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView txtViewSucces;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        fireRef = new Firebase("https://mobileone.firebaseio.com/");
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        txtViewSucces = (TextView) findViewById(R.id.textViewSucces);
        loginButton = (Button)findViewById(R.id.buttonLogIn);
        loginButton.setOnClickListener(this);
        registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.buttonLogIn: logIn();
                break;
            case R.id.registerButton :
                Log.d(TAG,"registerbutton pressed");
                break;
        }
    }

    public void logIn()
    {
        if(emailEditText.getText().toString() != null && passwordEditText.getText().toString() != null)
        {
            final String email = emailEditText.getText().toString();
            final String password = passwordEditText.getText().toString();
            fireRef.authWithPassword(email,password, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                                 txtViewSucces.setVisibility(View.VISIBLE);
                            txtViewSucces.setText("User : "+authData.getUid()+" is ingelogd");
                            txtViewSucces.setTextColor(Color.GREEN);
                            emailEditText.setText("");
                            passwordEditText.setText("");
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                                txtViewSucces.setVisibility(View.VISIBLE);
                                txtViewSucces.setTextColor(Color.RED);
                                txtViewSucces.setText("User niet ingelogd : "+firebaseError.getMessage()+" "+firebaseError.getDetails());
                        }
                    }


            );
        }

    }
}
