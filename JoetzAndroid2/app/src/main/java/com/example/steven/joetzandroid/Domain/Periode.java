package com.example.steven.joetzandroid.Domain;

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

    public void setVan(Date van) {
        this.van = van;
    }

    public Date getTot() {
        return tot;
    }

    public void setTot(Date tot) {
        this.tot = tot;
    }

    @Override
    public String toString() {
        return "van = " + van +
                ", tot = " + tot;
    }
}
