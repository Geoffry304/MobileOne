package com.example.steven.joetzandroid.Activities;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.database.JoetzDB;


public class VakantieAlgemeenFragment extends VakantieDetailFragment {



    private TextView titelLbl;
    private TextView promotekstLbl;
    private ImageView foto;
    private JoetzDB db;
    private static final String TAG = "Algemeen";

    public VakantieAlgemeenFragment() {
        // Required empty public constructor
        setaTag(TAG);

    }

    @Override
    public void onAttach(Activity activity) {
        db = JoetzDB.getDbInstance(activity);
        db.open();
        vakantie = db.getVakantie("vakantie0");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vakantie_algemeen, container, false);

        if(vakantie != null)
        {
            titelLbl = (TextView)view.findViewById(R.id.titlelbl);
            promotekstLbl= (TextView)view.findViewById(R.id.promotekstlbl);
            foto = (ImageView)view.findViewById(R.id.imageView1);
            titelLbl.setText(titelLbl.toString() + vakantie.getTitel());
            promotekstLbl.setText(promotekstLbl.toString() + vakantie.getPromoTekst());
        }

        return view;
    }


    @Override
    public String getaTag() {
        return TAG;
    }
}
