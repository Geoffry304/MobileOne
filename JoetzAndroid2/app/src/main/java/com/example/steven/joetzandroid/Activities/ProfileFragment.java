package com.example.steven.joetzandroid.Activities;



import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.steven.joetzandroid.Domain.Ouder;
import com.example.steven.joetzandroid.R;


public class ProfileFragment extends android.support.v4.app.Fragment{
    private static final String TAG = "ProfileFragment";
    private Ouder ouder;
    private EditText txtVoornaam;
    private EditText txtNaam;


    public void setOuder(Ouder ouder){
        this.ouder = ouder;
    }

    public Ouder getOuder(){return this.ouder;}

    public ProfileFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,ouder.toString());
        txtNaam = (EditText) getView().findViewById(R.id.profileEditTextNaam);
        txtVoornaam = (EditText) getView().findViewById(R.id.profileEditTextVoornaam);
        txtNaam.setText(ouder.getLastName());
        txtVoornaam.setText(ouder.getFirstName());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


}
