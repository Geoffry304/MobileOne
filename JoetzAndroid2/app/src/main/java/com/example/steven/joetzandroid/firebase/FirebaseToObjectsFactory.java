package com.example.steven.joetzandroid.firebase;

import com.example.steven.joetzandroid.Domain.Adres;
import com.example.steven.joetzandroid.Domain.Contact;
import com.example.steven.joetzandroid.Domain.LeeftijdsCategorie;
import com.example.steven.joetzandroid.Domain.Periode;
import com.example.steven.joetzandroid.Domain.Thema;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.Domain.VakantiePlaats;

import java.util.Map;

/**
 * Created by Steven on 13/11/14.
 */
public class FirebaseToObjectsFactory {

    public static LeeftijdsCategorie geefLeeftijdscategorie(Map<String,Object>map)
    {
        LeeftijdsCategorie leeftijdsCategorie = new LeeftijdsCategorie();
        leeftijdsCategorie.setTotGeboorteDatum(Integer.valueOf(map.get("tot").toString()));
        leeftijdsCategorie.setVanGeboorteDatum(Integer.valueOf(map.get("van").toString()));
        return leeftijdsCategorie;

    }
    public static Adres geefAdres(Map<String,Object>map)
    {
        Adres adres = new Adres();
        adres.setStraat(map.get("straat").toString());
        adres.setNummer(Integer.valueOf(map.get("nr").toString()));
        adres.setGemeente(map.get("gemeente").toString());
        adres.setPostcode(Integer.valueOf(map.get("postcode").toString()));
        return adres;
    }
    public static Contact geefContact(Map<String,Object>map)
    {
        Contact contact = new Contact();
        contact.setEmail(map.get("email").toString());
        contact.setInfo(map.get("info").toString());
        contact.setWebsite(map.get("website").toString());
        contact.setAdres(FirebaseToObjectsFactory.geefAdres((Map<String, Object>) map.get("adres")));
        return contact;
    }

    public static VakantiePlaats geefVakantiePlaats(Map<String,Object>map)
    {
        VakantiePlaats plaats = new VakantiePlaats();
        plaats.setNaam(map.get("plaatsnaam").toString());
        plaats.setContact(FirebaseToObjectsFactory.geefContact((Map<String,Object>)map.get("contact")));
        return plaats;

    }
    public static Vakantie geeftVakantie(Map<String,Object>map,String id)
    {
        Vakantie vakantie = new Vakantie(id);
        if(map.get("busVervoer").toString()=="true")
        {
            vakantie.setBusvervoer(true);
        }
        if(map.get("eigenVervoer").toString() == "true")
        {
            vakantie.setEigenVervoer(true);
        }
        if(map.get("fiscaalAftrek").toString()=="true")
        {
            vakantie.setFiscaalAftrek(true);
        }
        vakantie.setMaxAantalLid(Integer.valueOf(map.get("maxAantalld").toString()));
        vakantie.setNaam(map.get("naam").toString());
        vakantie.setPromoTekst(map.get("promoTekst").toString());
        vakantie.getThema().setNaam(map.get("thema").toString());
        vakantie.setLeeftijdsCategorie(FirebaseToObjectsFactory.geefLeeftijdscategorie((Map<String,Object>)map.get("leeftijdscategorie")));
        vakantie.setVakantiePlaats(FirebaseToObjectsFactory.geefVakantiePlaats((Map<String,Object>)map.get("plaats")));
        //moet nog opgevuld worden met methodes voor de ArrayListen van periodes enz!!!!
        //vakantie.setVakantiePeriode(FirebaseToObjectsFactory.);

        return vakantie;

    }

/*
    public static Periode geefPeriode (Map<String, Object>map)
    {
        Periode periode = new Periode();

    }
*/
}
