package com.example.steven.joetzandroid.Domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Steven on 24/10/14.
 */
public class Periode {

    private int id;
    private Date van;
    private Date tot;

    public Periode(int id, Date van, Date tot) {
        this.id = id;
        this.van = van;
        this.tot = tot;
    }

    public Periode(Date van, Date tot) {
        this.van = van;
        this.tot = tot;
    }

    public Periode() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVan() {
        return van;
    }

    public void setVan(String van) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.van = df.parse(van);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getTot() {
        return tot;
    }

    public void setTot(String tot) {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.tot = df.parse(tot);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTotString()
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(tot);
    }
    public String getVanString()
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(van);
    }

    @Override
    public String toString() {
        return "van = " + van +
                ", tot = " + tot;
    }
}
