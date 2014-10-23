package com.example.steven.joetzandroid.Domain;

import java.util.ArrayList;

/**
 * Created by Steven on 23/10/14.
 */
public class Ouder extends User {

    private String aansluitingsNummer;
    private Ouder partner;
    private ArrayList<Kind> kinderen;

    public Ouder() {
        kinderen = new ArrayList<Kind>();
    }

    public Ouder(String email, String password, String lastName, String firstName, String aansluitingsNummer,ArrayList<Kind>kinderen, Ouder partner) {
        super(email, password, lastName, firstName);
        this.aansluitingsNummer = aansluitingsNummer;
        this.kinderen = kinderen;
        this.partner = partner;
    }

    public void addKind(Kind kind)
    {
        if(!kinderen.contains(kind))
        {
            kinderen.add(kind);
        }
    }

    public boolean removeKind(Kind kind)
    {
        try
        {
            kinderen.remove(kind);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public void setPartner(Ouder o)
    {
            this.partner = o;

    }

    public String getAansluitingsNummer() {
        return aansluitingsNummer;
    }

    public Ouder getPartner() {
        return partner;
    }

    public ArrayList<Kind> getKinderen() {
        return kinderen;
    }

    public void setAansluitingsNummer(String aansluitingsNummer) {
        this.aansluitingsNummer = aansluitingsNummer;
    }

    public void setKinderen(ArrayList<Kind> kinderen) {
        this.kinderen = kinderen;
    }
}
