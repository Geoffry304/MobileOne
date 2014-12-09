package com.example.steven.joetzandroid.Activities;

import android.app.Activity;
import android.content.Context;
import android.location.Geocoder;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.database.JoetzDB;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapView#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MapView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GoogleMap googleMap;
    private JoetzDB db;
    private Vakantie vakantie;
    private Context context = null;//moet normaal andere context zijn.

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapView.
     */
    // TODO: Rename and change types and number of parameters
    public static MapView newInstance(String param1, String param2) {
        MapView fragment = new MapView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public MapView() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        db = JoetzDB.getDbInstance(activity);
        db.open();
        vakantie = db.getVakantie("vakantie0");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_view, container, false);
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
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
