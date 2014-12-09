package com.example.steven.joetzandroid.Activities;

import android.app.Activity;
import android.content.Context;
import android.location.Geocoder;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.database.JoetzDB;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapView extends VakantieDetailFragment {

    private GoogleMap googleMap;
    private JoetzDB db;
    private Vakantie vakantie;
    private Context context;

    public MapView() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("vakantieId",vakantie.getId());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_view, container, false);
        if (savedInstanceState !=null)
        {
            this.vakantie = JoetzDB.getDbInstance(context).getVakantie(savedInstanceState.getString("vakantieId"));
        }
        else
        {
            db = JoetzDB.getDbInstance(context);
            db.open();
            vakantie = db.getVakantie("vakantie0");
        }
        if(vakantie != null) {
            createMapView();
            //adress meegeve voor geolocatie
            try {
                addMarker();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return view;
    }
    private void createMapView() {
        try {
            if (null == googleMap) {
                googleMap = ((SupportMapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap) {
                    Context context = null;//moet normaal andere context zijn.
                    Toast.makeText(context,
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }
    private void addMarker() throws IOException {
        Geocoder geocoder =
                new Geocoder(context);
        List<Address> adres = geocoder.getFromLocationName("Rooseveltlaan 64 Erpe", 1);
        adres.get(0).getLatitude();
        //geocoding gebruiken voor omzetten van adress naar gps coordinaten
        // adres vervangen door firebaseadres
        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(adres.get(0).getLatitude(), adres.get(0).getLongitude()))
                            .title("Marker")
                            .draggable(true)
            );
        }
    }
}
