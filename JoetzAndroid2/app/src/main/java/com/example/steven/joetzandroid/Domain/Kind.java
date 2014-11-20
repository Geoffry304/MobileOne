package com.example.steven.joetzandroid.Domain;

import com.example.steven.joetzandroid.Domain.Adres;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Steven on 23/10/14.
 */
public class Kind {

    private String lastName;
    private String fistName;
    private Adres adres;
    private Date birthDate;
    private String rijksRegisternummer;
    private Ouder ouder;
    private String id;

    public Kind() {
    }

    public Kind(String id,String lastName, String fistName, Adres adres, Date birthDate,Ouder ouder) {
        this.lastName = lastName;
        this.fistName = fistName;
        this.adres = adres;
        this.birthDate = birthDate;
        this.ouder = ouder;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRijksRegisternummer() {
        return rijksRegisternummer;
    }

    public void setRijksRegisternummer(String rijksRegisternummer) {
        if(rijksRegisternummer.length()==11 && !rijksRegisternummer.matches("[^-9]"))
        {
            this.rijksRegisternummer = rijksRegisternummer;
        }
        else
        {
            throw new IllegalArgumentException();
        }

    }
    public String getBirthDateString()
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(birthDate);
    }
    public void setOuder(Ouder ouder)
    {
        this.ouder = ouder;
    }
    public Ouder getOuder()
    {
        return this.ouder;
    }
}
