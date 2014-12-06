package com.example.steven.joetzandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.steven.joetzandroid.Domain.Adres;
import com.example.steven.joetzandroid.Domain.Contact;
import com.example.steven.joetzandroid.Domain.Foto;
import com.example.steven.joetzandroid.Domain.Kind;
import com.example.steven.joetzandroid.Domain.LeeftijdsCategorie;
import com.example.steven.joetzandroid.Domain.Ouder;
import com.example.steven.joetzandroid.Domain.Periode;
import com.example.steven.joetzandroid.Domain.PrijsCategorie;
import com.example.steven.joetzandroid.Domain.Thema;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.Domain.VakantiePeriode;
import com.example.steven.joetzandroid.Domain.VakantiePlaats;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Steven on 20/11/14.
 */
public class JoetzDB {

    private static final String TAG = "JoetzDB.class";
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    private static JoetzDB instance;

    public static JoetzDB getDbInstance(Context context)
    {
        if (instance == null)
        {
            instance = new JoetzDB(context);
            //instance.open();
        }
        return instance;
    }
    private JoetzDB(Context context) {
        helper  = new DatabaseHelper(context,DatabaseContstants.DATABASE_NAME,null,DatabaseContstants.DATABASE_VERSION);

    }

    public void close()
    {
        helper.close();
    }

    public void open(){
        try{

           database = helper.getWritableDatabase();

        }catch (SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            database = helper.getReadableDatabase();
        }
    }

    public long addAdres(Adres adres){
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_ADRES_STRAAT,adres.getStraat());
            val.put(DatabaseContstants.COLUMN_ADRES_NUMMER,adres.getNummer());
            val.put(DatabaseContstants.COLUMN_ADRES_POSTC,adres.getPostcode());
            val.put(DatabaseContstants.COLUMN_ADRES_GEMEENTE,adres.getGemeente());
            long a_id = database.insert(DatabaseContstants.TABLE_ADRES,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }

    }
    public long addThema(Thema thema)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_THEMA_NAME,thema.getNaam());
            long a_id = database.insert(DatabaseContstants.TABLE_THEMA,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }
    public long addLeeftijdCat(LeeftijdsCategorie categorie)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_LEEFTIJD_CAT_VAN, categorie.getVanGeboorteDatum());
            val.put(DatabaseContstants.COLUMN_LEEFTIJD_CAT_TOT,categorie.getTotGeboorteDatum());
            long a_id = database.insert(DatabaseContstants.TABLE_LEEFTIJD_CAT,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }
    public long addPrijsCat(PrijsCategorie categorie)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_PRIJSCAT_BASIS, categorie.getBasisPrijs());
            val.put(DatabaseContstants.COLUMN_PRIJSCAT_JOETZ1,categorie.getJoetzSterPrijsCat1());
            val.put(DatabaseContstants.COLUMN_PRIJSCAT_JOETZ2, categorie.getJoetzSterPrijsCat2());
            long a_id = database.insert(DatabaseContstants.TABLE_PRIJSCAT,null,val);

            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }
    public long addContact(Contact contact)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_CONTACT_INFO, contact.getInfo());
            val.put(DatabaseContstants.COLUMN_CONTACT_EMAIL, contact.getEmail());
            val.put(DatabaseContstants.COLUMN_CONTACT_TELNR, contact.getTelnr());
            val.put(DatabaseContstants.COLUMN_CONTACT_WEBSITE,contact.getWebsite());
            long adres_id = addAdres(contact.getAdres());
            val.put(DatabaseContstants.COLUMN_CONTACT_ADRES_ID,adres_id);
            long a_id = database.insert(DatabaseContstants.TABLE_CONTACT,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }
    public long addVakantiePlaats(VakantiePlaats plaats)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_VAKANTIE_PLAATS_NAAM, plaats.getNaam());
            long co_id = addContact(plaats.getContact());
            val.put(DatabaseContstants.COLUMN_VAKANTIE_PLAATS_CONTACT_ID, co_id);
            long a_id = database.insert(DatabaseContstants.TABLE_VAKANTIEPLAATS,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }
    public long addVakantiePeriode(VakantiePeriode periode)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_VAKANTIE_PERIODE_NAAM, periode.getVakantieNaam());
            long a_id = database.insert(DatabaseContstants.TABLE_VAKANTIEPERIODE,null,val);
            if(periode.getPeriodes()!=null)
            {
                for(Periode p : periode.getPeriodes())
                {
                    addPeriode(p,a_id);
                }
            }
            Log.d(TAG,"Periode: "+a_id+" naam : "+periode.getVakantieNaam());
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }
    public long addPeriode(Periode periode,long vakantiePeriodeId)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_PERIODE_TOT,periode.getTotString());
            val.put(DatabaseContstants.COLUMN_PERIODE_VAN,periode.getVanString());
            val.put(DatabaseContstants.COLUMN_PERIODE_V_PERIODE,vakantiePeriodeId);
            long a_id = database.insert(DatabaseContstants.TABLE_PERIODE,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }

    public long addVakantie(Vakantie vakantie)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_VAKANTIE_ID,vakantie.getId());
            val.put(DatabaseContstants.COLUMN_VAKANTIE_BUS,vakantie.isBusvervoer());
            val.put(DatabaseContstants.COLUMN_VAKANTIE_EIGEN, vakantie.isEigenVervoer());
            val.put(DatabaseContstants.COLUMN_VAKANTIE_FISCAAL, vakantie.isFiscaalAftrek());
            val.put(DatabaseContstants.COLUMN_VAKANTIE_MAXAANTAL, vakantie.getMaxAantalLid());
            val.put(DatabaseContstants.COLUMN_VAKANTIE_NAAM, vakantie.getNaam());
            val.put(DatabaseContstants.COLUMN_VAKANTIE_PROMOTEKST, vakantie.getPromoTekst());
            val.put(DatabaseContstants.COLUMN_VAKANTIE_THEMA,addThema(vakantie.getThema()));
            val.put(DatabaseContstants.COLUMN_VAKANTIE_PLAATS, addVakantiePlaats(vakantie.getVakantiePlaats()));
            val.put(DatabaseContstants.COLUMN_VAKANTIE_PERIODE, addVakantiePeriode(vakantie.getVakantiePeriode()));
            val.put(DatabaseContstants.COLUMN_VAKANTIE_PRIJSCAT, addPrijsCat(vakantie.getPrijsCategorie()));
            val.put(DatabaseContstants.COLUMN_VAKANTIE_LEEFTIJD, addLeeftijdCat(vakantie.getLeeftijdsCategorie()));
            for (Foto f : vakantie.getFotos())
            {
                addPhotoToVakantie(f,vakantie.getId());
            }
            //photo aanmaken

            long a_id = database.insert(DatabaseContstants.TABLE_VAKANTIE,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }
    public long addOuder(Ouder ouder)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_OUDER_A_NAAM, ouder.getLastName());
            val.put(DatabaseContstants.COLUMN_OUDER_V_NAAM, ouder.getFirstName());
            val.put(DatabaseContstants.COLUMN_OUDER_CONTACT, addContact(ouder.getContact()));
            val.put(DatabaseContstants.COLUMN_OUDER_ID, ouder.getId());
            //kinderen aanmaken, foto aanmaken
            for(Kind k : ouder.getKinderen())
            {
                addKind(k,ouder.getId());
            }
            long a_id = database.insert(DatabaseContstants.TABLE_OUDER,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }

    }
    public long addKind(Kind kind, String ouderId)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_KIND_ID, kind.getId());
            val.put(DatabaseContstants.COLUMN_KIND_F_NAME, kind.getFistName());
            val.put(DatabaseContstants.COLUMN_KIND_L_NAME, kind.getLastName());
            val.put(DatabaseContstants.COLUMN_KIND_BIRTH, kind.getBirthDateString());
            val.put(DatabaseContstants.COLUMN_KIND_ADRES, addAdres(kind.getAdres()));
            val.put(DatabaseContstants.COLUMN_OUDER_ID, ouderId);
            long a_id = database.insert(DatabaseContstants.TABLE_KIND,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }
    // addPhoto

    public Thema getThema(int themaId)
    {
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_THEMA+" WHERE "+DatabaseContstants.COLUMN_THEMA_ID+
                " = "+themaId;

        Log.e(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery,null);
        if(c != null) {
            c.moveToFirst();
        }
            Thema t = new Thema();
            t.setId(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_THEMA_ID)));
            t.setNaam(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_THEMA_NAME)));

        return t;

    }
    public Adres getAdres(int adresId)
    {
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_ADRES+" WHERE "+DatabaseContstants.COLUMN_ADRES_ID+
                " = "+adresId;

        Log.e(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery,null);
        if(c != null) {
            c.moveToFirst();
        }
        Adres a = new Adres();
        a.setId(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_ADRES_ID)));
        a.setStraat(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_ADRES_STRAAT)));
        a.setNummer(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_ADRES_STRAAT)));
        a.setPostcode(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_ADRES_NUMMER)));
        a.setGemeente(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_ADRES_GEMEENTE)));

        return a;
    }
    public LeeftijdsCategorie getLeeftijdsCategorie(int lcId)
    {
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_LEEFTIJD_CAT+" WHERE "+DatabaseContstants.COLUMN_LEEFTIJD_CAT_ID+
                " = "+lcId;

        Log.e(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery,null);
        if(c != null) {
            c.moveToFirst();
        }
        LeeftijdsCategorie l = new LeeftijdsCategorie();
        l.setId(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_LEEFTIJD_CAT_ID)));
        l.setVanGeboorteDatum(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_LEEFTIJD_CAT_VAN)));
        l.setTotGeboorteDatum(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_LEEFTIJD_CAT_TOT)));

        return l;
    }

    public Contact getContact(int contactId)
    {
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_CONTACT+" WHERE "+DatabaseContstants.COLUMN_CONTACT_ID+
                " = "+contactId;

        Log.e(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery,null);
        if(c != null) {
            c.moveToFirst();
        }
        Contact contact = new Contact();
        contact.setId(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_CONTACT_ID)));
        contact.setWebsite(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_CONTACT_WEBSITE)));
        contact.setEmail(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_CONTACT_EMAIL)));
        contact.setTelnr(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_CONTACT_TELNR)));
        contact.setInfo(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_CONTACT_INFO)));
        contact.setAdres(getAdres(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_CONTACT_ADRES_ID))));

        return contact;
    }
    public ArrayList<String> getAllVakantiePeriodes()
    {
        ArrayList<String> namen = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT "+DatabaseContstants.COLUMN_VAKANTIE_PERIODE_NAAM+" FROM "+DatabaseContstants.TABLE_VAKANTIEPERIODE;
        Cursor c = database.rawQuery(selectQuery,null);

        if(c .moveToFirst())
        {
            do {
                String s = c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_PERIODE_NAAM));
                namen.add(s);
            }while (c.moveToNext());
        }
                return namen;
    }
    public VakantiePeriode getVakantiePeriode(int vakantiePeriodeId)
    {
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_VAKANTIEPERIODE+" WHERE "+DatabaseContstants.COLUMN_VAKANTIE_PERIODE_ID+
                " = "+vakantiePeriodeId;

        Log.e(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery,null);
        if(c != null) {
            c.moveToFirst();
        }
        VakantiePeriode v = new VakantiePeriode();
        v.setId(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_PERIODE_ID)));
        v.setVakantieNaam(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_PERIODE_NAAM)));
        ArrayList<Periode>periodesList = getPeriodesFromVakantiePeriode(vakantiePeriodeId);
        Periode[]periodesArray = periodesList.toArray(new Periode[periodesList.size()]);
        v.setPeriodes(periodesArray);

        return v;
    }
    public ArrayList<Periode> getPeriodesFromVakantiePeriode(int vakantiePeriode)
    {
        ArrayList<Periode> periodes = new ArrayList<Periode>();
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_PERIODE+" WHERE "+DatabaseContstants.COLUMN_PERIODE_V_PERIODE+" = "+vakantiePeriode;

        Cursor c = database.rawQuery(selectQuery,null);

        if(c.moveToFirst())
        {
            do {
                Periode p = new Periode();
                p.setId(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_PERIODE_ID)));
                p.setVan(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_PERIODE_VAN)));
                p.setTot(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_PERIODE_TOT)));
                periodes.add(p);
            }while (c.moveToNext());
        }
        return periodes;
    }
    public PrijsCategorie getPrijsCategorie(int prijsCatId)
    {
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_PRIJSCAT+" WHERE "+DatabaseContstants.COLUMN_PRIJSCAT_ID+
                " = "+prijsCatId;

        Log.e(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery,null);
        if(c != null) {
            c.moveToFirst();
        }
        PrijsCategorie p = new PrijsCategorie();
        p.setId(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_PRIJSCAT_ID)));
        p.setBasisPrijs(c.getDouble(c.getColumnIndex(DatabaseContstants.COLUMN_PRIJSCAT_BASIS)));
        p.setJoetzSterPrijsCat1(c.getDouble(c.getColumnIndex(DatabaseContstants.COLUMN_PRIJSCAT_JOETZ1)));
        p.setJoetzSterPrijsCat2(c.getDouble(c.getColumnIndex(DatabaseContstants.COLUMN_PRIJSCAT_JOETZ2)));

        return p;
    }

    public VakantiePlaats getVakantiePlaats(int vakantiePlaatsId)
    {
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_VAKANTIEPLAATS+" WHERE "+DatabaseContstants.COLUMN_VAKANTIE_PLAATS_ID+
                " = "+vakantiePlaatsId;

        Log.e(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery,null);
        if(c != null) {
            c.moveToFirst();
        }
        VakantiePlaats vp = new VakantiePlaats();
        vp.setId(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_PLAATS_ID)));
        vp.setNaam(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_PLAATS_NAAM)));
        vp.setContact(getContact(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_PLAATS_CONTACT_ID))));

        return vp;

    }

    public Vakantie getVakantie(String vakantieId)
    {
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_VAKANTIE+" WHERE "+DatabaseContstants.COLUMN_VAKANTIE_ID+
                " = '"+vakantieId+"'";

        Log.e(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery,null);
        if(c != null) {
            c.moveToFirst();
        }
        Vakantie v = new Vakantie();
        v.setId(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_ID)));
        v.setNaam(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_NAAM)));
        boolean bus = c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_BUS))!=0;
        v.setBusvervoer(bus);
        boolean eigen = c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_EIGEN))!=0;
        v.setEigenVervoer(eigen);
        v.setPromoTekst(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_PROMOTEKST)));
        boolean fisc = c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_FISCAAL))!=0;
        v.setFiscaalAftrek(fisc);
        v.setLeeftijdsCategorie(getLeeftijdsCategorie(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_LEEFTIJD))));
        v.setMaxAantalLid(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_MAXAANTAL)));
        v.setVakantiePlaats(getVakantiePlaats(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_PLAATS))));
        v.setThema(getThema(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_THEMA))));
        v.setVakantiePeriode(getVakantiePeriode(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_PERIODE))));
        v.setFotos(getFotosFromVakantie(vakantieId));
        return v;

    }

    public ArrayList<Foto> getFotosFromVakantie(String vakantieId)
    {
        ArrayList<Foto> fotos = new ArrayList<Foto>();
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_FOTO+" WHERE "+DatabaseContstants.COLUMN_PHOTO_VAK_ID+" = '"+vakantieId+"'";
        Log.d(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery,null);
        if(c!=null)
        {

        if(c.moveToFirst())
        {
            do {
                Foto f = new Foto();
                f.setId(c.getInt(c.getColumnIndex(DatabaseContstants.COLUMN_PHOTO_ID)));
                f.setNaam(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_PHOTO_URL)));
                f.setImage(getImage(c.getBlob(c.getColumnIndex(DatabaseContstants.COLUMN_PHOTO_IMAGE))));
                fotos.add(f);
            }while (c.moveToNext());
        }

        }
        return fotos;
    }

    public Bitmap getImage(byte[]b)
    {
        if (b !=null)
        {
            return BitmapFactory.decodeByteArray(b,0, b.length);
        }

        return null;
    }
    public ArrayList<Vakantie> getVakantiesByVakantiePeriode(String periode)
    {
        ArrayList<Vakantie> vakanties = new ArrayList<Vakantie>();
        String selectQuery = "SELECT v."+DatabaseContstants.COLUMN_VAKANTIE_ID+" FROM "+DatabaseContstants.TABLE_VAKANTIE+" AS v JOIN "
                +DatabaseContstants.TABLE_VAKANTIEPERIODE+" AS p"
                +" ON v."+DatabaseContstants.COLUMN_VAKANTIE_PERIODE+" = p."+DatabaseContstants.COLUMN_VAKANTIE_PERIODE_ID
                +" WHERE p."+DatabaseContstants.COLUMN_VAKANTIE_PERIODE_NAAM+" = '"+periode+"'";
        Cursor c = database.rawQuery(selectQuery,null);
        if(c.moveToFirst())
        {
            do{
                Vakantie v = getVakantie(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_ID)));
                v.setFotos(getFotosFromVakantie(v.getId()));
                vakanties.add(v);
            }while(c.moveToNext());
        }
        return vakanties;

    }
    public ArrayList<Vakantie> getAllVakanties()
    {
        ArrayList<Vakantie> vakanties = new ArrayList<Vakantie>();
        String selectQuery = "SELECT * FROM "+DatabaseContstants.TABLE_VAKANTIE;
        Cursor c = database.rawQuery(selectQuery,null);
        if(c.moveToFirst())
        {
            do{
                Vakantie v = getVakantie(c.getString(c.getColumnIndex(DatabaseContstants.COLUMN_VAKANTIE_ID)));
                v.setFotos(getFotosFromVakantie(v.getId()));
                vakanties.add(v);
            }while(c.moveToNext());
        }
        return vakanties;
    }

    public long addPhotoToVakantie(Foto f,String vakantieId)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_PHOTO_URL, f.getNaam());
            val.put(DatabaseContstants.COLUMN_PHOTO_VAK_ID, vakantieId);
            if (f.getImage()!=null)
            {
                val.put(DatabaseContstants.COLUMN_PHOTO_IMAGE,convertFoto(f.getImage()));
            }

            long a_id = database.insert(DatabaseContstants.TABLE_FOTO,null,val);
            return a_id;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }

    public byte[] convertFoto(Bitmap f)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        f.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[]photo = baos.toByteArray();
        return photo;
    }
    public boolean updateFoto(Foto f,String vakantieId)
    {
        try{
            ContentValues val = new ContentValues();
            val.put(DatabaseContstants.COLUMN_PHOTO_URL, f.getNaam());
            val.put(DatabaseContstants.COLUMN_PHOTO_VAK_ID, vakantieId);
            val.put(DatabaseContstants.COLUMN_PHOTO_IMAGE,convertFoto(f.getImage()));
            long a_id = database.update(DatabaseContstants.TABLE_FOTO, val, DatabaseContstants.COLUMN_PHOTO_ID+"="+f.getId(), null);
            Log.e(TAG,"Foto updated : "+a_id);
            return a_id>0;
        }catch(SQLiteException e)
        {
            Log.e(TAG,e.getMessage());
            return false;
        }

    }
}
