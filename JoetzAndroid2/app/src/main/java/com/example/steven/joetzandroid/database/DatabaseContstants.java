package com.example.steven.joetzandroid.database;

import android.os.AsyncTask;

/**
 * Created by Steven on 14/11/14.
 */
public class DatabaseContstants {

    public static final String DATABASE_NAME = "joetz_db";
    public static final int DATABASE_VERSION = 3;

    //tables
    public static final String TABLE_OUDER = "ouder";
    public static final String TABLE_KIND = "kind";
    public static final String TABLE_VAKANTIE = "vakantie";
    public static final String TABLE_INSCHRIJVING_VAKANTIE = "kind_inschrijving";
    public static final String TABLE_PERIODE = "periode";
    public static final String TABLE_CONTACT = "contact";
    public static final String TABLE_ADRES = "adres";
    public static final String TABLE_PRIJSCAT = "prijscategorie";
    public static final String TABLE_THEMA = "thema";
    public static final String TABLE_FOTO = "foto";
    public static final String TABLE_VAKANTIEPLAATS = "vakantie_plaats";
    public static final String TABLE_VAKANTIEPERIODE = "vakantie_periode";
    public static final String TABLE_LEEFTIJD_CAT = "leeftijd_cat";



    //columns ouder
    public static final String COLUMN_OUDER_ID = "ouder_id";
    public static final String COLUMN_OUDER_A_NAAM = "l_name";
    public static final String COLUMN_OUDER_V_NAAM = "f_name";
    public static final String COLUMN_OUDER_CONTACT = "contact_id";
    //columns kind
    public static final String COLUMN_KIND_ID = "kind_id";
    public static final String COLUMN_KIND_L_NAME = "l_name";
    public static final String COLUMN_KIND_F_NAME = "f_name";
    public static final String COLUMN_KIND_BIRTH = "birthdate";
    public static final String COLUMN_KIND_ADRES = "adres_id";
    public static final String COLUMN_KIND_OUDER_ID = "ouder_id";
    //columns adres
    public static final String COLUMN_ADRES_ID = "adres_id";
    public static final String COLUMN_ADRES_STRAAT = "straat";
    public static final String COLUMN_ADRES_NUMMER = "nummer";
    public static final String COLUMN_ADRES_POSTC = "postcode";
    public static final String COLUMN_ADRES_GEMEENTE = "gemeente";
    //columns thema
    public static final String COLUMN_THEMA_ID = "thema_id";
    public static final String COLUMN_THEMA_NAME = "thema_name";
    //columns periode
    public static final String COLUMN_PERIODE_ID = "periode_id";
    public static final String COLUMN_PERIODE_VAN = "periode_van";
    public static final String COLUMN_PERIODE_TOT = "periode_tot";
    public static final String COLUMN_PERIODE_V_PERIODE = "vakantie_periode_id";
    //columns vakantie_periode
    public static final String COLUMN_VAKANTIE_PERIODE_ID = "v_p_id";
    public static final String COLUMN_VAKANTIE_PERIODE_NAAM = "v_p_naam";
    //columns contact
    public static final String COLUMN_CONTACT_ID = "contact_id";
    public static final String COLUMN_CONTACT_TELNR = "contact_telnr";
    public static final String COLUMN_CONTACT_EMAIL = "contact_email";
    public static final String COLUMN_CONTACT_WEBSITE = "contact_website";
    public static final String COLUMN_CONTACT_INFO = "contact_info";
    public static final String COLUMN_CONTACT_ADRES_ID = "contact_adres_id";
    //columns vakantieplaats
    public static final String COLUMN_VAKANTIE_PLAATS_ID = "v_p_id";
    public static final String COLUMN_VAKANTIE_PLAATS_NAAM = "v_p_naam";
    public static final String COLUMN_VAKANTIE_PLAATS_CONTACT_ID = "v_p_contact_id";
    //columns prijscat
    public static final String COLUMN_PRIJSCAT_ID = "prijscat_id";
    public static final String COLUMN_PRIJSCAT_BASIS = "prijscat_basis";
    public static final String COLUMN_PRIJSCAT_JOETZ1 = "prijscat_joetz_1";
    public static final String COLUMN_PRIJSCAT_JOETZ2 = "prijscat_joetz_2";
    //columns inschrijving_kind
    public static final String COLUMN_INSCHRIJVING_KIND_ID = "inschrijving_id";
    public static final String COLUMN_INSCHRIJVING_KIND_K_ID = "kind_id";
    public static final String COLUMN_INSCHRIJVING_KIND_VAKAN_ID = "vakantie_id";
    public static final String COLUMN_INSCHRIJVING_KIND_PERIODE_ID = "periode_id";
    //columns vakantie
    public static final String COLUMN_VAKANTIE_ID = "vakantie_id";
    public static final String COLUMN_VAKANTIE_BUS = "busvervoer";
    public static final String COLUMN_VAKANTIE_EIGEN = "eigenvervoer";
    public static final String COLUMN_VAKANTIE_NAAM = "vakantie_naam";
    public static final String COLUMN_VAKANTIE_THEMA = "thema_id";
    public static final String COLUMN_VAKANTIE_MAXAANTAL = "max_aantal_leden";
    public static final String COLUMN_VAKANTIE_PROMOTEKST = "promotekst";
    public static final String COLUMN_VAKANTIE_PERIODE = "v_period_id";
    public static final String COLUMN_VAKANTIE_PLAATS = "v_plaats_id";
    public static final String COLUMN_VAKANTIE_FISCAAL = "fiscaal_aftr";
    public static final String COLUMN_VAKANTIE_LEEFTIJD = "leeftijd_id";
    public static final String COLUMN_VAKANTIE_PRIJSCAT = "prijscat_id";
    //columns leeftijd_cat
    public static final String COLUMN_LEEFTIJD_CAT_ID = "l_cat_id";
    public static final String COLUMN_LEEFTIJD_CAT_VAN = "l_cat_van";
    public static final String COLUMN_LEEFTIJD_CAT_TOT = "l_cat_tot";
    //columns foto
    public static final String COLUMN_PHOTO_ID = "photo_id";
    public static final String COLUMN_PHOTO_IMAGE = "photo_image";
    public static final String COLUMN_PHOTO_VAK_ID = "photo_v_id";
    public static final String COLUMN_PHOTO_OUDER_ID = "photo_ouder_id";
    public static final String COLUMN_PHOTO_URL = "photo_url";








}
