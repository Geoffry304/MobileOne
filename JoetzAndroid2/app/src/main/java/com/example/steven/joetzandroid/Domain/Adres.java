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

    public Adres(String straat, int nummer, int postcode, String gemeente) {
        this.straat = straat;
        this.nummer = nummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        if(nummer > 0)
        {
           this.nummer = nummer;
        }

    }
//test invalid
    public int getPostcode() {

        return postcode;
    }

    public void setPostcode(int postcode) {
        if(postcode<10000&&postcode>999)
        {
            this.postcode = postcode;
        }

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
}
