package com.example.steven.joetzandroid.Domain;

import com.example.steven.joetzandroid.Domain.Adres;

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

    public Kind() {
    }

    public Kind(String lastName, String fistName, Adres adres, Date birthDate) {
        this.lastName = lastName;
        this.fistName = fistName;
        this.adres = adres;
        this.birthDate = birthDate;
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
}
