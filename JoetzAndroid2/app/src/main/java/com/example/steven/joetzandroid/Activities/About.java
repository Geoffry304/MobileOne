package com.example.steven.joetzandroid.Activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.steven.joetzandroid.R;


public class About extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    //elke keer as ge terug opstart krijgde normaal diezelfde fout als daarjuist dan moede exact zelfde doen
    // dependencies verwijderen en den terug zetten
    //fragement about is verdwene

    //bizar? nja probeer het nog eens te pushen op github misschien doet hij het nu wel en anders kopieer ik u file he
    //zo raar, op github staat dak een maand geleden laatste keer gepusht heb, ma ik heb deze morgen nog ma gepushd


}
