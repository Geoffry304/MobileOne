package com.example.steven.joetzandroid.Domain;

import java.util.ArrayList;

/**
 * Created by Steven on 24/10/14.
 */
public class VakantiePeriode {

    private String vakantieNaam;
    private Periode[] periodes;
    private int id;

    public VakantiePeriode()
    {

    }

    public VakantiePeriode(int id,String vakantieNaam, int periodeAantal) {
        this.vakantieNaam = vakantieNaam;
        periodes = new Periode[periodeAantal];
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVakantieNaam() {
        return vakantieNaam;
    }

    public void setVakantieNaam(String vakantieNaam) {
        this.vakantieNaam = vakantieNaam;
    }

    public int getPeriodeAantal() {
        return periodes.length;
    }

    public Periode[] getPeriodes() {
        return periodes;
    }

    public void setPeriodes(Periode []periodes)
    {
        this.periodes = periodes;
    }
}
