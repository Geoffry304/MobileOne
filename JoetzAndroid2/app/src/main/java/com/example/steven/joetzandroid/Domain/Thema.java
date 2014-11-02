package com.example.steven.joetzandroid.Domain;

import android.content.Context;

import com.firebase.client.Firebase;

/**
 * Created by Steven on 24/10/14.
 */
public class Thema {



    private String naam;
    private int id;


    public Thema() {
    }

    public Thema(String naam, int id) {
        this.naam = naam;
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
