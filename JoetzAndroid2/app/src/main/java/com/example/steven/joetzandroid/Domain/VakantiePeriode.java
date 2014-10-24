package com.example.steven.joetzandroid.Domain;

import java.util.ArrayList;

/**
 * Created by Steven on 24/10/14.
 */
public class VakantiePeriode {

    private String vakantieNaam;
    private final int periodeAantal;
    private Periode[] periodes;

    public VakantiePeriode(String vakantieNaam, int periodeAantal) {
        this.vakantieNaam = vakantieNaam;
        this.periodeAantal = periodeAantal;
        periodes = new Periode[periodeAantal];
    }

    public String getVakantieNaam() {
        return vakantieNaam;
    }

    public void setVakantieNaam(String vakantieNaam) {
        this.vakantieNaam = vakantieNaam;
    }

    public int getPeriodeAantal() {
        return periodeAantal;
    }

    public Periode[] getPeriodes() {
        return periodes;
    }

    public void setPeriodes(Periode []periodes)
    {
        if(periodes.length == periodeAantal)
        this.periodes = periodes;
    }
}
