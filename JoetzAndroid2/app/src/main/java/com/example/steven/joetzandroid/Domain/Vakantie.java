package com.example.steven.joetzandroid.Domain;

import java.util.ArrayList;

/**
 * Created by Steven on 13/11/14.
 */
public class Vakantie {

    private VakantiePeriode vakantiePeriode;
    private VakantiePlaats vakantiePlaats;
    private Thema thema;
    private boolean busvervoer;
    private boolean eigenVervoer;
    private boolean fiscaalAftrek;
    private LeeftijdsCategorie leeftijdsCategorie;
    private int maxAantalLid;
    private String promoTekst;
    private ArrayList<Kind>inschijvingen;
    private ArrayList<Foto>fotos;
    private String naam;
    private PrijsCategorie  prijsCategorie;
    private String id;

    public Vakantie() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if(naam == null)
        {
            this.naam = "";
        }
        this.naam = naam;
    }

    public Vakantie(String id)
    {
        this.id = id;
        busvervoer = false;
        eigenVervoer = false;
        fiscaalAftrek = false;
        thema = new Thema();
    }

    public PrijsCategorie getPrijsCategorie() {
        return prijsCategorie;
    }

    public void setPrijsCategorie(PrijsCategorie prijsCategorie) {
        this.prijsCategorie = prijsCategorie;
    }

    public VakantiePeriode getVakantiePeriode() {
        return vakantiePeriode;
    }

    public void setVakantiePeriode(VakantiePeriode vakantiePeriode) {
        this.vakantiePeriode = vakantiePeriode;
    }

    public VakantiePlaats getVakantiePlaats() {
        return vakantiePlaats;
    }

    public void setVakantiePlaats(VakantiePlaats vakantiePlaats) {
        this.vakantiePlaats = vakantiePlaats;
    }

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema thema) {
        this.thema = thema;
    }

    public boolean isBusvervoer() {
        return busvervoer;
    }

    public void setBusvervoer(boolean busvervoer) {
        this.busvervoer = busvervoer;
    }

    public boolean isEigenVervoer() {
        return eigenVervoer;
    }

    public void setEigenVervoer(boolean eigenVervoer) {
        this.eigenVervoer = eigenVervoer;
    }

    public boolean isFiscaalAftrek() {
        return fiscaalAftrek;
    }

    public void setFiscaalAftrek(boolean fiscaalAftrek) {
        this.fiscaalAftrek = fiscaalAftrek;
    }

    public LeeftijdsCategorie getLeeftijdsCategorie() {
        return leeftijdsCategorie;
    }

    public void setLeeftijdsCategorie(LeeftijdsCategorie leeftijdsCategorie) {
        this.leeftijdsCategorie = leeftijdsCategorie;
    }

    public int getMaxAantalLid() {
        return maxAantalLid;
    }

    public void setMaxAantalLid(int maxAantalLid) {
        this.maxAantalLid = maxAantalLid;
    }

    public String getPromoTekst() {
        return promoTekst;
    }

    public void setPromoTekst(String promoTekst) {
        this.promoTekst = promoTekst;
    }

    public ArrayList<Kind> getInschijvingen() {
        return inschijvingen;
    }

    public void setInschijvingen(ArrayList<Kind> inschijvingen) {
        this.inschijvingen = inschijvingen;
    }

    public ArrayList<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<Foto> fotos) {
        this.fotos = fotos;
    }
}
