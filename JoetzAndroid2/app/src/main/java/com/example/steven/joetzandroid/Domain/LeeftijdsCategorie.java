package com.example.steven.joetzandroid.Domain;

import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Steven on 24/10/14.
 */
public class LeeftijdsCategorie {
    private int yearNow;
    private int vanGeboorteDatum;
    private int totGeboorteDatum;
    private int id;

    public LeeftijdsCategorie(int id,int vanGeboorteDatum, int totGeboorteDatum) {
        yearNow = Calendar.getInstance().get(Calendar.YEAR);
        this.vanGeboorteDatum = vanGeboorteDatum;
        this.totGeboorteDatum = totGeboorteDatum;
        this.id = id;
    }

    public LeeftijdsCategorie() {
       Calendar c = Calendar.getInstance();
        yearNow = c.get(Calendar.YEAR);
        Log.d("LEEFTIJD",yearNow+"");
    }

    public int getVanGeboorteDatum() {

        return vanGeboorteDatum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVanGeboorteDatum(int vanGeboorteDatum) {
        if(vanGeboorteDatum > 1900 && vanGeboorteDatum < yearNow)
        this.vanGeboorteDatum = vanGeboorteDatum;
    }

    public int getTotGeboorteDatum() {
        return totGeboorteDatum;
    }

    public void setTotGeboorteDatum(int totGeboorteDatum) {

        this.totGeboorteDatum = totGeboorteDatum;
    }
    public int getVanLeeftijd()
    {
        return yearNow - vanGeboorteDatum;
    }
    public int getTotLeeftijd()
    {
        return yearNow - totGeboorteDatum;
    }
}
