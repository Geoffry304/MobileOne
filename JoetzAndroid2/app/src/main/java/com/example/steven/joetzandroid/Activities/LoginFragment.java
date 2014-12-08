package com.example.steven.joetzandroid.Activities;



import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.firebase.FirebaseAuth;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.TimerTask;

public class LoginFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView txtViewSucces;
    private Button loginButton;
    private Button registerButton;
    private final String TAG = "LoginFragment";
    private FirebaseAuth fire;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onFacebookSessionStateChange(session, state, exception);
        }
    };

    private UiLifecycleHelper uiHelper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fire = new FirebaseAuth();
        uiHelper = new UiLifecycleHelper(getActivity(),callback);
        uiHelper.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        LoginButton fbLogin = (LoginButton) view.findViewById(R.id.fbLogin);
        fbLogin.setFragment(this);
        fbLogin.setText("aanmelden met facebook");
        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        loginButton = (Button) view.findViewById(R.id.buttonLogIn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });

        registerButton = (Button) view.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registreer();
            }
        });

        return view;
    }
    private void onFacebookSessionStateChange(final Session session, SessionState state, Exception exception) {

        fire.loginWithFacebook(session, state, exception);
    }


    public void logIn()
    {
        if (!emailEditTextEmpty() && !passwordEditTextEmpty())
        {
            String email = getEmailText();
            String password = getPasswordText();
            if(fire.logIn(email,password))
            {
                Log.d(TAG,"Success");
                //Toast.makeText(getActivity().getApplicationContext(),"AuthId : " +authData.getUid(),Toast.LENGTH_LONG).show();
            }
            else
            {
                Log.d(TAG,"Not success");
            }
        }


    }
    public void registreer()
    {
        RegisterFragment f = new RegisterFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_container, f).commit();
    }

    public EditText getEmailEditText() {
        return emailEditText;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public EditText getPasswordEditText() {
        return passwordEditText;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public TextView getTxtViewSucces() {
        return txtViewSucces;
    }

    public boolean emailEditTextEmpty(){
        return emailEditText.getText().toString().isEmpty();
    }
    public boolean passwordEditTextEmpty(){
        return passwordEditText.getText().toString().isEmpty();
    }
    public String getEmailText()
    {
        return emailEditText.getText().toString();
    }
    public String getPasswordText()
    {
        return passwordEditText.getText().toString();
    }


    @Override
    public void onResume() {
        super.onResume();
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            this.onFacebookSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

}
