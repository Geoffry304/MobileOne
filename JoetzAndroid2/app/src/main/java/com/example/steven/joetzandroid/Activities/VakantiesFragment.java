package com.example.steven.joetzandroid.Activities;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.steven.joetzandroid.Adapters.VakantieListAdapter;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;

import java.util.ArrayList;

public class VakantiesFragment extends Fragment implements AdapterView.OnItemClickListener{

    private final String TAG = "VakantiesFragment";
    private ArrayList<Vakantie> vakanties;
    private ListView vakantiesListView;

    public VakantiesFragment() {
        // Required empty public constructor
    }

    public void setVakanties(ArrayList<Vakantie>vakanties)
    {
        this.vakanties = vakanties;
        Log.d(TAG,"vakanties size "+vakanties.size());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vakanties, container, false);
        vakantiesListView = (ListView)view.findViewById(R.id.vakanties_list_view);
        VakantieListAdapter vla = new VakantieListAdapter(getActivity(),vakanties);
        vakantiesListView.setAdapter(vla);
        vakantiesListView.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       Intent intent = new Intent(getActivity(),VakantieDetailActivity.class);
        intent.putExtra("vakantieId",vakanties.get(i).getId());
        startActivity(intent);
    }
}
