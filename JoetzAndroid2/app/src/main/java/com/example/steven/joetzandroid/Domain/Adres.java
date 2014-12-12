package com.example.steven.joetzandroid.Domain;

/**
 * Created by Steven on 23/10/14.
 */
public class Adres {

    private String straat;
    private int nummer;
    private int postcode;
    private String gemeente;
    private double longitude;
    private double latitude;
    private int id;

    public Adres(int id,String straat, int nummer, int postcode, String gemeente) {
        this.id = id;
        this.straat = straat;
        this.nummer = nummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }
    public Adres()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStraat() {

        return straat;
    }

    public void setStraat(String straat) {
        if(straat.isEmpty())
        {
            this.straat = "";
        }

        this.straat = straat;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {

           this.nummer = nummer;


    }
/*
Nieuwe regel code :
ifl
else
bla bla
blalbla


 */
    public int getPostcode() {

        return postcode;
    }

    public void setPostcode(int postcode) {

            this.postcode = postcode;


    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return straat+" "+nummer+"\n"+gemeente+" "+postcode;
    }
}
