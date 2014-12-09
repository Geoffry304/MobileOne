package com.example.steven.joetzandroid.Activities;



import android.app.Activity;
import android.content.Context;
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

    private Context context;
    private TextView titelLbl;
    private TextView promotekstLbl;
    private ImageView foto;
    private JoetzDB db;
    public Vakantie vakantie;
    private static final String TAG = "Algemeen";

    public VakantieAlgemeenFragment() {
        // Required empty public constructor
        //setaTag(TAG);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
<<<<<<< HEAD
        this.context = activity;
=======
>>>>>>> Steven
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("vakantieId",vakantie.getId());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        View view = inflater.inflate(R.layout.fragment_vakantie_algemeen, container, false);

        if(vakantie != null)
        {
            titelLbl = (TextView)view.findViewById(R.id.titlelbl);
            promotekstLbl= (TextView)view.findViewById(R.id.promotekstlbl);
            foto = (ImageView)view.findViewById(R.id.imageView1);
            titelLbl.setText("Titel: "+ vakantie.getNaam());
            promotekstLbl.setText("PromoTekst: " + vakantie.getPromoTekst());
        }

        return view;
    }


    @Override
    public String getaTag() {
        return TAG;
    }
}
