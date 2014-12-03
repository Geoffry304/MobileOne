package com.example.steven.joetzandroid.Activities;

import android.support.v4.app.Fragment;

import com.example.steven.joetzandroid.Domain.Vakantie;

/**
 * Created by Steven on 3/12/14.
 */
//Overerven verplicht voor alle fragmenten die de details van een vakantie weergeven
public abstract class VakantieDetailFragment extends Fragment {

    protected Vakantie vakantie;

    public void setVakantie(Vakantie vakantie){
        this.vakantie = vakantie;
    }
}
