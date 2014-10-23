package com.example.steven.joetzandroid.Domain;

/**
 * Created by Steven on 23/10/14.
 */
public class Contact {

    private Adres adres;
    private String telnr;
    private String email;
    private String website;
    private String info;

    public Contact(Adres adres, String telnr, String email) {
        this.adres = adres;
        this.telnr = telnr;
        this.email = email;
    }

    public Contact(Adres adres, String telnr, String email, String website) {
        this.adres = adres;
        this.telnr = telnr;
        this.email = email;
        this.website = website;

    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getTelnr() {
        return telnr;
    }

    public void setTelnr(String telnr) {
        this.telnr = telnr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
