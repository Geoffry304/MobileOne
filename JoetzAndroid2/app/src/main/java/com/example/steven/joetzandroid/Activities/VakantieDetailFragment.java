package com.example.steven.joetzandroid.Activities;

import android.support.v4.app.Fragment;

import com.example.steven.joetzandroid.Domain.Vakantie;

/**
 * Created by Steven on 3/12/14.
 */
//Overerven verplicht voor alle fragmenten die de details van een vakantie weergeven
public abstract class VakantieDetailFragment extends Fragment {

    protected Vakantie vakantie;
    private static String aTag;


    public void setVakantie(Vakantie vakantie){
        this.vakantie = vakantie;
    }
    public String getaTag()
    {
        return aTag;
    }

    public void setaTag(String tag)
    {
        aTag = tag;
    }

}
