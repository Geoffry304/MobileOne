package com.example.steven.joetzandroid.Domain;

/**
 * Created by Steven on 24/10/14.
 */
public class Foto {

    private String naam;
    private int id;
    private String message;

    public Foto(String naam, int id, String message) {
        this.naam = naam;
        this.id = id;
        this.message = message;
    }

    public Foto() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
