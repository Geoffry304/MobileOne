package com.example.steven.joetzandroid.Domain;

/**
 * Created by Steven on 24/10/14.
 */
public class VakantiePlaats {

    private String naam;
    private Contact contact;
    private int id;

    public VakantiePlaats(int id,String naam, Contact contact) {
        this.naam = naam;
        this.contact = contact;
        this.id = id;
    }

    public VakantiePlaats() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
