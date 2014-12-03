package com.example.steven.joetzandroid.Domain;

import android.content.Context;
import android.util.Log;

import com.example.steven.joetzandroid.database.JoetzDB;

import java.util.ArrayList;

/**
 * Created by Steven on 25/11/14.
 */
public class CreateDummyData {
    private static final String TAG = "CreateDummyData";
    private JoetzDB db;

    public CreateDummyData(Context context)
    {
        db = JoetzDB.getDbInstance(context);
        db.open();
    }

    public void createVakanties()
    {
        Vakantie vakantie1 = new Vakantie();
        vakantie1.setNaam("Krokusvakantie aan zee");
        VakantiePlaats vplaats1 = new VakantiePlaats();
        vplaats1.setNaam("De Barkentijn");
        Contact contact1 = new Contact();
        contact1.setInfo("Verveling krijgt geen kans tijdens de krokusvakantie want we trekken er met z'n allen op uit! We logeren in het vakantiecentrum 'De Barkentijn' te Nieuwpoort");
        contact1.setTelnr("056/274700");
        Adres adres1 = new Adres();
        adres1.setGemeente("Nieuwpoort");
        adres1.setStraat("Albert I laan");
        adres1.setNummer(126);
        adres1.setPostcode(8620);
        contact1.setAdres(adres1);
        contact1.setWebsite("http://www.debarkentijn.be");
        contact1.setEmail("info@debarkentijn.be");
        vplaats1.setContact(contact1);
        vakantie1.setVakantiePlaats(vplaats1);

        vakantie1.setMaxAantalLid(20);
        vakantie1.setFiscaalAftrek(true);
        vakantie1.setBusvervoer(true);
        vakantie1.setEigenVervoer(true);
        PrijsCategorie prijsCategorie = new PrijsCategorie();
        prijsCategorie.setBasisPrijs(165);
        prijsCategorie.setJoetzSterPrijsCat2(105);
        prijsCategorie.setJoetzSterPrijsCat1(135);

        vakantie1.setPrijsCategorie(prijsCategorie);
        LeeftijdsCategorie leeftijdsCategorie = new LeeftijdsCategorie();
        leeftijdsCategorie.setVanGeboorteDatum(2009);
        leeftijdsCategorie.setTotGeboorteDatum(1998);

        vakantie1.setLeeftijdsCategorie(leeftijdsCategorie);
        vakantie1.setPromoTekst("Vijf dagen lang spelen we de leukste spelletjes, voor klein en groot. Samen met je vakantievriendjes beleef je het ene avontuur na het andere. Plezier gegarandeerd!!");

        Thema t = new Thema();
        t.setNaam("Aan zee");
        vakantie1.setThema(t);




        String[]periodes = {"Krokus 15","Pasen 15","Zomer 15","Herfst 15","Kerst 15 - 16"};






        for( int i = 0; i < 10 ; i++)
        {
            Foto f = new Foto();

            VakantiePeriode periode = new VakantiePeriode();

            if(i == 0)
            {
                f.setNaam("http://stevendc.hostoi.com/images/1.jpg");
                //f.loadImage();
                vakantie1.addFoto(f);
                periode.setVakantieNaam(periodes[i]);
            }
            else if(i>0 && i<3)
            {
                f.setNaam("http://stevendc.hostoi.com/images/"+i+".jpg");
                //f.loadImage();
                vakantie1.addFoto(f);
                periode.setVakantieNaam(periodes[1]);
            }
            else if(i>3 && i<6)
            {
                f.setNaam("http://stevendc.hostoi.com/images/"+i+".jpg");
                //f.loadImage();
                vakantie1.addFoto(f);
                periode.setVakantieNaam(periodes[2]);
            }
            else if (i == 8)
            {
                periode.setVakantieNaam(periodes[3]);
            }
            else
            {
                periode.setVakantieNaam(periodes[4]);
            }




            vakantie1.setVakantiePeriode(periode);

            vakantie1.setId("vakantie"+i);
            Log.d(TAG, vakantie1.toString());
            db.addVakantie(vakantie1);

        }


    }

    public void translateFoto()
    {
       for(Vakantie v : getVakantiesFromDb())
       {
         int size = v.getFotos().size() -1;
           Foto f = v.getFotos().get(size);
           if (f.getImage() == null)
           {
               f.loadImage(v.getId(),db);
           }
       }
    }
    public ArrayList<Vakantie>getVakantiesFromDb()
    {
        return db.getAllVakanties();
    }
}
