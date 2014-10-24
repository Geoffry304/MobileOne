package com.example.steven.joetzandroid.Domain;

/**
 * Created by Steven on 24/10/14.
 */
public class PrijsCategorie {

    private double basisPrijs;
    private double joetzSterPrijsCat1;
    private double joetzSterPrijsCat2;
    //info over de prijs kan in de prijsCategorie bijgeplaatst worden
    private String info;

    public PrijsCategorie() {
    }

    public PrijsCategorie(double basisPrijs, double joetzSterPrijsCat1, double joetzSterPrijsCat2) {
        this.basisPrijs = basisPrijs;
        this.joetzSterPrijsCat1 = joetzSterPrijsCat1;
        this.joetzSterPrijsCat2 = joetzSterPrijsCat2;
    }

    public double getBasisPrijs() {
        return basisPrijs;
    }

    public void setBasisPrijs(double basisPrijs) {
        this.basisPrijs = basisPrijs;
    }

    public double getJoetzSterPrijsCat1() {
        return joetzSterPrijsCat1;
    }

    public void setJoetzSterPrijsCat1(double joetzSterPrijsCat1) {
        this.joetzSterPrijsCat1 = joetzSterPrijsCat1;
    }

    public double getJoetzSterPrijsCat2() {
        return joetzSterPrijsCat2;
    }

    public void setJoetzSterPrijsCat2(double joetzSterPrijsCat2) {
        this.joetzSterPrijsCat2 = joetzSterPrijsCat2;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
