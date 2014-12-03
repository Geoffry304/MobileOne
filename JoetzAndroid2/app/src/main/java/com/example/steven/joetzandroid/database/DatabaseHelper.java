package com.example.steven.joetzandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by Steven on 14/11/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String CREATE_TABLE_ADRES = "create table "+DatabaseContstants.TABLE_ADRES+
            "(" +DatabaseContstants.COLUMN_ADRES_ID+" integer primary key autoincrement, "
                +DatabaseContstants.COLUMN_ADRES_STRAAT+" text not null, "
                +DatabaseContstants.COLUMN_ADRES_NUMMER+" integer not null, "
                +DatabaseContstants.COLUMN_ADRES_POSTC+" integer not null, "
                +DatabaseContstants.COLUMN_ADRES_GEMEENTE+" text not null "+
            ")";
    private static final String CREATE_TABLE_THEMA = "create table "+DatabaseContstants.TABLE_THEMA+
            "(" +DatabaseContstants.COLUMN_THEMA_ID+" integer primary key autoincrement, "
            +DatabaseContstants.COLUMN_THEMA_NAME+" text not null "+
            ")";
    private static final String CREATE_TABLE_LEEFTIJDCAT = "create table "+DatabaseContstants.TABLE_LEEFTIJD_CAT+
            "(" +DatabaseContstants.COLUMN_LEEFTIJD_CAT_ID+" integer primary key autoincrement, "
            +DatabaseContstants.COLUMN_LEEFTIJD_CAT_VAN+" integer not null, "
            +DatabaseContstants.COLUMN_LEEFTIJD_CAT_TOT+" integer not null "+
            ")";

    private static final String CREATE_TABLE_CONTACT = "create table "+DatabaseContstants.TABLE_CONTACT+
            "(" +DatabaseContstants.COLUMN_CONTACT_ID+" integer primary key autoincrement, "
            +DatabaseContstants.COLUMN_CONTACT_EMAIL+" text not null, "
            +DatabaseContstants.COLUMN_CONTACT_TELNR+" text, "
            +DatabaseContstants.COLUMN_CONTACT_INFO+" text, "
            +DatabaseContstants.COLUMN_CONTACT_WEBSITE+" text, "
            +DatabaseContstants.COLUMN_CONTACT_ADRES_ID+" integer not null, "
            +"foreign key("+DatabaseContstants.COLUMN_CONTACT_ADRES_ID+") references "
            +DatabaseContstants.TABLE_ADRES+" ("+DatabaseContstants.COLUMN_ADRES_ID+") "+
            ")";
    private static final String CREATE_TABLE_PERIODE = "create table "+DatabaseContstants.TABLE_PERIODE+
            "(" +DatabaseContstants.COLUMN_PERIODE_ID+" integer primary key autoincrement, "
            +DatabaseContstants.COLUMN_PERIODE_VAN+" text not null, "
            +DatabaseContstants.COLUMN_PERIODE_TOT+" text not null, "
            +DatabaseContstants.COLUMN_PERIODE_V_PERIODE+" integer not null, "
            +"foreign key("+DatabaseContstants.COLUMN_PERIODE_V_PERIODE+") references "
            +DatabaseContstants.TABLE_VAKANTIEPERIODE+" ("+DatabaseContstants.COLUMN_VAKANTIE_PERIODE_ID+") "+
            ")";
    private static final String CREATE_TABLE_VAKANTIE_PERIODE = "create table "+DatabaseContstants.TABLE_VAKANTIEPERIODE+
            "(" +DatabaseContstants.COLUMN_VAKANTIE_PERIODE_ID+" integer primary key autoincrement, "
            +DatabaseContstants.COLUMN_VAKANTIE_PERIODE_NAAM+" text not null "+
            ")";

    private static final String CREATE_TABLE_VAKANTIE_PLAATS = "create table "+DatabaseContstants.TABLE_VAKANTIEPLAATS+
            "(" +DatabaseContstants.COLUMN_VAKANTIE_PLAATS_ID+" integer primary key autoincrement, "
            +DatabaseContstants.COLUMN_VAKANTIE_PLAATS_NAAM+" text not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_PLAATS_CONTACT_ID+ " integer not null, "
            +"foreign key("+DatabaseContstants.COLUMN_VAKANTIE_PLAATS_CONTACT_ID+") references "
            +DatabaseContstants.TABLE_CONTACT+" ("+DatabaseContstants.COLUMN_CONTACT_ID+") "+
            ")";
    private static final String CREATE_TABLE_OUDER = "create table "+DatabaseContstants.TABLE_OUDER+
            "(" +DatabaseContstants.COLUMN_OUDER_ID+" text primary key, "
            +DatabaseContstants.COLUMN_OUDER_A_NAAM+" text not null, "
            +DatabaseContstants.COLUMN_OUDER_V_NAAM+" text not null, "
            +DatabaseContstants.COLUMN_OUDER_CONTACT+" integer, "
            +"foreign key("+DatabaseContstants.COLUMN_OUDER_CONTACT+") references "
            +DatabaseContstants.TABLE_CONTACT+" ("+DatabaseContstants.COLUMN_CONTACT_ID+") "+
            ")";
    private static final String CREATE_TABLE_KIND = "create table "+DatabaseContstants.TABLE_KIND+
            "(" +DatabaseContstants.COLUMN_KIND_ID+" text primary key, "
            +DatabaseContstants.COLUMN_KIND_F_NAME+" text not null, "
            +DatabaseContstants.COLUMN_KIND_L_NAME+" text not null, "
            +DatabaseContstants.COLUMN_KIND_BIRTH+" text not null, "
            +DatabaseContstants.COLUMN_KIND_OUDER_ID+" text not null, "
            +DatabaseContstants.COLUMN_KIND_ADRES + " integer, "
            +"foreign key("+DatabaseContstants.COLUMN_OUDER_ID+") references "
            +DatabaseContstants.TABLE_OUDER+" ("+DatabaseContstants.COLUMN_OUDER_ID+"), "
            +"foreign key("+DatabaseContstants.COLUMN_KIND_ADRES+") references "
            +DatabaseContstants.TABLE_ADRES+" ("+DatabaseContstants.COLUMN_ADRES_ID+") "+
            ")";
    private static final String CREATE_TABLE_PRIJSCAT = "create table "+DatabaseContstants.TABLE_PRIJSCAT+
            "(" +DatabaseContstants.COLUMN_PRIJSCAT_ID+" integer primary key autoincrement, "
            +DatabaseContstants.COLUMN_PRIJSCAT_BASIS+" numeric not null, "
            +DatabaseContstants.COLUMN_PRIJSCAT_JOETZ1+" numeric, "
            +DatabaseContstants.COLUMN_PRIJSCAT_JOETZ2+" numeric "+
            ")";
    private static final String CREATE_INSCHRIJVING = "create table "+DatabaseContstants.TABLE_INSCHRIJVING_VAKANTIE+
            "(" +DatabaseContstants.COLUMN_INSCHRIJVING_KIND_ID+" integer primary key autoincrement, "
            +DatabaseContstants.COLUMN_INSCHRIJVING_KIND_K_ID+" text not null, "
            +DatabaseContstants.COLUMN_INSCHRIJVING_KIND_VAKAN_ID+" text not null, "
            +DatabaseContstants.COLUMN_INSCHRIJVING_KIND_PERIODE_ID+" integer not null, "
            +"foreign key("+DatabaseContstants.COLUMN_INSCHRIJVING_KIND_K_ID+") references "
            +DatabaseContstants.TABLE_KIND+" ("+DatabaseContstants.COLUMN_KIND_ID+"), "
            +"foreign key("+DatabaseContstants.COLUMN_INSCHRIJVING_KIND_VAKAN_ID+") references "
            +DatabaseContstants.TABLE_VAKANTIE+" ("+DatabaseContstants.COLUMN_VAKANTIE_ID+"), "
            +"foreign key("+DatabaseContstants.COLUMN_INSCHRIJVING_KIND_PERIODE_ID+") references "
            +DatabaseContstants.TABLE_PERIODE+" ("+DatabaseContstants.COLUMN_PERIODE_ID+") "+
            ")";
    private static final String CREATE_TABLE_VAKANTIE = "create table "+DatabaseContstants.TABLE_VAKANTIE+
            "(" +DatabaseContstants.COLUMN_VAKANTIE_ID+" text primary key, "
            +DatabaseContstants.COLUMN_VAKANTIE_BUS+" boolean not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_EIGEN+" boolean not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_NAAM+" text not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_FISCAAL+" boolean not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_PERIODE+" integer not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_MAXAANTAL+" integer not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_PLAATS+" integer not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_THEMA+" integer not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_PROMOTEKST+" text, "
            +DatabaseContstants.COLUMN_VAKANTIE_LEEFTIJD+" integer not null, "
            +DatabaseContstants.COLUMN_VAKANTIE_PRIJSCAT+" integer not null, "
            +"foreign key("+DatabaseContstants.COLUMN_VAKANTIE_PERIODE+") references "
            +DatabaseContstants.TABLE_VAKANTIEPERIODE+" ("+DatabaseContstants.COLUMN_VAKANTIE_PERIODE_ID+"), "
            +"foreign key("+DatabaseContstants.COLUMN_VAKANTIE_PLAATS+") references "
            +DatabaseContstants.TABLE_VAKANTIEPLAATS+" ("+DatabaseContstants.COLUMN_VAKANTIE_PLAATS_ID+"), "
            +"foreign key("+DatabaseContstants.COLUMN_VAKANTIE_THEMA+") references "
            +DatabaseContstants.TABLE_THEMA+" ("+DatabaseContstants.COLUMN_THEMA_ID+"), "
            +"foreign key("+DatabaseContstants.COLUMN_VAKANTIE_LEEFTIJD+") references "
            +DatabaseContstants.TABLE_LEEFTIJD_CAT+" ("+DatabaseContstants.COLUMN_LEEFTIJD_CAT_ID+"), "
            +"foreign key("+DatabaseContstants.COLUMN_VAKANTIE_PRIJSCAT+") references "
            +DatabaseContstants.TABLE_PRIJSCAT+" ("+DatabaseContstants.COLUMN_PRIJSCAT_ID+") "+
            ")";

    private static final String CREATE_TABLE_PHOTO = "create table "+DatabaseContstants.TABLE_FOTO+
            "( " +DatabaseContstants.COLUMN_PHOTO_ID+" integer primary key autoincrement, "
            +DatabaseContstants.COLUMN_PHOTO_IMAGE+" blob, "
            +DatabaseContstants.COLUMN_PHOTO_URL+" text, "
            +DatabaseContstants.COLUMN_PHOTO_VAK_ID+" text, "
            +DatabaseContstants.COLUMN_PHOTO_OUDER_ID+" text, "
            +"foreign key("+DatabaseContstants.COLUMN_PHOTO_VAK_ID+") references "
            +DatabaseContstants.TABLE_VAKANTIE+" ("+DatabaseContstants.COLUMN_VAKANTIE_ID+"), "
            +"foreign key("+DatabaseContstants.COLUMN_PHOTO_OUDER_ID+") references "
            +DatabaseContstants.TABLE_OUDER+" ("+DatabaseContstants.COLUMN_OUDER_ID+") "+
            ")";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(CREATE_TABLE_ADRES);
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACT);
        sqLiteDatabase.execSQL(CREATE_TABLE_THEMA);
        sqLiteDatabase.execSQL(CREATE_TABLE_LEEFTIJDCAT);
        sqLiteDatabase.execSQL(CREATE_TABLE_OUDER);
        sqLiteDatabase.execSQL(CREATE_TABLE_KIND);
        sqLiteDatabase.execSQL(CREATE_TABLE_PERIODE);
        sqLiteDatabase.execSQL(CREATE_TABLE_PRIJSCAT);
        sqLiteDatabase.execSQL(CREATE_TABLE_VAKANTIE_PERIODE);
        sqLiteDatabase.execSQL(CREATE_TABLE_VAKANTIE_PLAATS);
        sqLiteDatabase.execSQL(CREATE_TABLE_VAKANTIE);
        sqLiteDatabase.execSQL(CREATE_INSCHRIJVING);
        sqLiteDatabase.execSQL(CREATE_TABLE_PHOTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_ADRES);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_FOTO);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_VAKANTIE);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_INSCHRIJVING_VAKANTIE);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_CONTACT);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_KIND);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_OUDER);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_PERIODE);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_PRIJSCAT);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_THEMA);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_VAKANTIEPLAATS);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_VAKANTIEPERIODE);
        sqLiteDatabase.execSQL("drop table if exists "+DatabaseContstants.TABLE_LEEFTIJD_CAT);

    }
}
