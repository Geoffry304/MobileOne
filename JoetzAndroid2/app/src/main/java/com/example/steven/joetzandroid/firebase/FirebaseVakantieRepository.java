package com.example.steven.joetzandroid.firebase;

import android.util.Log;

import com.example.steven.joetzandroid.Domain.Vakantie;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Steven on 13/11/14.
 */
public class FirebaseVakantieRepository {

    private ArrayList<Vakantie>vakanties;
    private Firebase ref = new Firebase("https://mobileone.firebaseio.com/vakanties");
    private final String TAG = "FirebaseVakantieRepository";

    public FirebaseVakantieRepository()
    {
        vakanties = new ArrayList<Vakantie>();
        fillVakanties();
    }

    public ArrayList<Vakantie> getVakanties()
    {
        return vakanties;
    }

    public void fillVakanties()
    {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    String id = child.getName();
                    Map<String,Object>childMap = (Map<String,Object>)child.getValue();
                    Vakantie vakantie = FirebaseToObjectsFactory.geeftVakantie(childMap,id);
                    vakanties.add(vakantie);
                    Log.d(TAG,vakantie.getId()+" werd toegevoegd!");
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG,"Firebase Error : "+firebaseError.getMessage());

            }
        });
    }
}
