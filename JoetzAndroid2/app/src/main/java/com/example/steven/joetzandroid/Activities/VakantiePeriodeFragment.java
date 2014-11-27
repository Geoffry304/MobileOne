package com.example.steven.joetzandroid.Activities;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.steven.joetzandroid.Adapters.VakantiePeriodeAdapter;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.database.JoetzDB;

import java.util.ArrayList;

public class VakantiePeriodeFragment extends Fragment implements AdapterView.OnItemClickListener{
    private final String TAG = "VakantiePeriodeFragment";
    private ArrayList<String> periodes;
    private ListView periodeListView;
    private JoetzDB db;


    public VakantiePeriodeFragment() {
        // Required empty public constructor


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG,"In onAttach "+activity.getLocalClassName());
        db = JoetzDB.getDbInstance(activity);
        db.open();
        periodes = db.getAllVakantiePeriodes();
        Log.d(TAG,"periodes size : "+periodes.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vakantie_periode,container, false);

        periodeListView = (ListView)view.findViewById(R.id.vakantie_periode_list);
        VakantiePeriodeAdapter adapter = new VakantiePeriodeAdapter(this.getActivity(),periodes);
        periodeListView.setAdapter(adapter);
        periodeListView.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        VakantiesFragment fragment = new VakantiesFragment();
        ArrayList<Vakantie> vakanties = db.getVakantiesByVakantiePeriode(periodes.get(i));
        Log.d(TAG,"vakanties size : "+vakanties.size());
        fragment.setVakanties(vakanties);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame_container,fragment).addToBackStack(null).commit();

    }
}
