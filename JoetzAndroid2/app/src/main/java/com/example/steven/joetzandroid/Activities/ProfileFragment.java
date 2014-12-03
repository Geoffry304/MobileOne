package com.example.steven.joetzandroid.Activities;



import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.steven.joetzandroid.Domain.Ouder;
import com.example.steven.joetzandroid.R;


public class ProfileFragment extends android.support.v4.app.Fragment{
    private static final String TAG = "ProfileFragment";
    private Ouder ouder;

    public void setOuder(Ouder ouder){
        this.ouder = ouder;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,ouder.toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


}
