package com.example.steven.joetzandroid.Domain;

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

    public LeeftijdsCategorie(int vanGeboorteDatum, int totGeboorteDatum) {
        yearNow = Calendar.getInstance().get(Calendar.YEAR);
        this.vanGeboorteDatum = vanGeboorteDatum;
        this.totGeboorteDatum = totGeboorteDatum;
    }

    public LeeftijdsCategorie() {
        yearNow = Calendar.getInstance().get(Calendar.YEAR);
    }

    public int getVanGeboorteDatum() {

        return vanGeboorteDatum;
    }

    public void setVanGeboorteDatum(int vanGeboorteDatum) {
        if(vanGeboorteDatum > 1900 && vanGeboorteDatum < yearNow)
        this.vanGeboorteDatum = vanGeboorteDatum;
    }

    public int getTotGeboorteDatum() {
        return totGeboorteDatum;
    }

    public void setTotGeboorteDatum(int totGeboorteDatum) {
        if(totGeboorteDatum > 1900 && totGeboorteDatum < yearNow)
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
