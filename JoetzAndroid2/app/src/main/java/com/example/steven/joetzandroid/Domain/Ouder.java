package com.example.steven.joetzandroid.Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Steven on 23/10/14.
 */
public class Ouder extends User {

    private String aansluitingsNummer;
    private Ouder partner;
    private ArrayList<Kind> kinderen;
    private Contact contact;
    private static final String ROLE = "10";
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ouder() {
        kinderen = new ArrayList<Kind>();

    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Ouder(String email,String password ,String lastName,String firstName)
    {
        super(email,password ,lastName, firstName);

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

    public HashMap<String,String>ouderToHashMap()
    {
        HashMap<String,String> ouderMap = new HashMap<String, String>();
        ouderMap.put("naam", this.getLastName());
        ouderMap.put("voornaam",this.getFirstName());
        ouderMap.put("email",this.getEmail());
        ouderMap.put("role_value",ROLE);

        return ouderMap;
    }

    public HashMap<String, String > ouderContactToHashMap()
    {
        HashMap<String,String> ouderMap = new HashMap<String, String>();
        if (contact !=null)
        {
            ouderMap.put("straat" , contact.getAdres().getStraat());
            ouderMap.put("nummer",""+contact.getAdres().getNummer());
            ouderMap.put("postcode",""+contact.getAdres().getPostcode());
            ouderMap.put("gemeente",contact.getAdres().getGemeente());
            if(contact.getTelnr()!=null)
             ouderMap.put("telnr",contact.getTelnr());
        }
        return ouderMap;
    }

    @Override
    public String toString() {
        return "Ouder : " +getId();
    }
}
