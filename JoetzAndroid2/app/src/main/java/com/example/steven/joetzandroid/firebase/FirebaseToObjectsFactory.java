package com.example.steven.joetzandroid.firebase;

import android.util.Log;

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
        leeftijdsCategorie.setTotGeboorteDatum(Integer.valueOf((String)map.get("tot")));
        leeftijdsCategorie.setVanGeboorteDatum(Integer.valueOf((String)map.get("van")));
        return leeftijdsCategorie;

    }
    public static Adres geefAdres(Map<String,Object>map)
    {
        Adres adres = new Adres();
        adres.setStraat((String)map.get("straat"));
        adres.setNummer(Integer.valueOf((String)map.get("nr")));
        adres.setGemeente((String)map.get("gemeente"));
        adres.setPostcode(Integer.valueOf((String)map.get("postcode")));
        return adres;
    }
    public static Contact geefContact(Map<String,Object>map)
    {
        Contact contact = new Contact();
        contact.setEmail((String)map.get("email"));
        contact.setInfo((String) map.get("info"));
        contact.setWebsite((String) map.get("website"));
        contact.setTelnr((String)map.get("telNr"));
        contact.setAdres(FirebaseToObjectsFactory.geefAdres((Map<String, Object>) map.get("adres")));
        return contact;
    }

    public static VakantiePlaats geefVakantiePlaats(Map<String,Object>map)
    {
        VakantiePlaats plaats = new VakantiePlaats();
        plaats.setNaam((String)map.get("plaatsnaam"));
        plaats.setContact(FirebaseToObjectsFactory.geefContact((Map<String,Object>)map.get("contact")));
        return plaats;

    }
    public static Vakantie geeftVakantie(Map<String,Object>map,String id)
    {
        Vakantie vakantie = new Vakantie(id);

        if(map.get("busVervoer").equals("true"))
        {
            vakantie.setBusvervoer(true);
        }
        if(map.get("eigenVervoer").equals("true"))
        {
            vakantie.setEigenVervoer(true);
        }
        if(map.get("fiscaalAftrek").equals("true"))
        {
            vakantie.setFiscaalAftrek(true);
        }
     vakantie.setMaxAantalLid(Integer.valueOf((String)map.get("maxAantalld")));
       Log.d("MAXLEDEN",vakantie.getMaxAantalLid()+"");
        vakantie.setNaam((String)map.get("naam"));
        vakantie.setPromoTekst((String)map.get("promoTekst"));
        vakantie.getThema().setNaam((String)map.get("thema"));
        vakantie.setLeeftijdsCategorie(FirebaseToObjectsFactory.geefLeeftijdscategorie((Map<String,Object>)map.get("leeftijdscategorie")));
        Log.d("LEEFTIJDVAK",vakantie.getLeeftijdsCategorie().getTotLeeftijd()+"");
        Log.d("LEEFTIJDVAK",vakantie.getLeeftijdsCategorie().getVanLeeftijd()+"");
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
