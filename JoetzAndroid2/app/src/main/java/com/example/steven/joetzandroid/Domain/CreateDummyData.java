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




        String[]periodes = {"Krokus 15","Pasen 15","Zomer 15","Herfst 15","Kerst 14 - 15"};
        Periode[]periodesDatumsPasen= {new Periode("01/04/2015","08/04/2015"),new Periode("09/04/2015","15/04/2015")};
        Periode[]periodesDatumsKerst= {new Periode("20/12/2014","28/12/2014"),new Periode("29/12/2014","04/12/2015")};
        Periode[]periodesDatumsHerfst= {new Periode("13/10/2015","30/10/2015")};
        Periode[]periodesDatumsKrokus= {new Periode("09/02/2015","16/02/2015")};
        Periode[]periodesDatumsZomer= {new Periode("01/07/2015","08/07/2015"),new Periode("09/07/2015","15/07/2015"),new Periode("16/07/2015","24/07/2015"),
                new Periode("25/07/2015","02/08/2015"),new Periode("03/08/2015","11/08/2015"),new Periode("12/08/2015","20/08/2015"),new Periode("21/08/2015","29/08/2015")};
        String urlFoto = "http://stevendc.hostoi.com/images/";
        ArrayList<Foto> fotos = new ArrayList<Foto>();

        for(int i = 1;i<6;i++)
        {

                Foto f = new Foto();
                f.setNaam(urlFoto+i+".jpg");
                Log.d(TAG,"Foto :  "+f.getNaam());
                fotos.add(f);

        }



        for( int i = 0; i < 10 ; i++)
        {


            VakantiePeriode periode = new VakantiePeriode();

            if(i == 0)
            {

                periode.setVakantieNaam(periodes[i]);
                periode.setPeriodes(periodesDatumsKrokus);
            }
            else if(i>0 && i<3)
            {

                periode.setVakantieNaam(periodes[1]);
                periode.setPeriodes(periodesDatumsPasen);
            }
            else if(i>3 && i<6)
            {

                //f.loadImage();

                periode.setVakantieNaam(periodes[2]);
                periode.setPeriodes(periodesDatumsZomer);
            }
            else if (i == 8)
            {
                periode.setVakantieNaam(periodes[3]);
                periode.setPeriodes(periodesDatumsHerfst);
            }
            else
            {
                periode.setVakantieNaam(periodes[4]);
                periode.setPeriodes(periodesDatumsKerst);
            }



            vakantie1.setFotos(fotos);
            vakantie1.setVakantiePeriode(periode);

            vakantie1.setId("vakantie"+i);
            Log.d(TAG, vakantie1.toString());
            db.addVakantie(vakantie1);

        }


    }


    public ArrayList<Vakantie>getVakantiesFromDb()
    {
        return db.getAllVakanties();
    }


}
