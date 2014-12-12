package com.example.steven.joetzandroid.Activities;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steven.joetzandroid.Domain.BitmapTransformation;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.database.JoetzDB;
import com.squareup.picasso.Picasso;


public class VakantieAlgemeenFragment extends VakantieDetailFragment {

    private Context context;
    private TextView titelLbl;
    private TextView promotekstLbl;
    private ImageView foto;
    private JoetzDB db;

    private static final String TAG = "Algemeen";

    public VakantieAlgemeenFragment() {
        // Required empty public constructor
        //setaTag(TAG);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
<<<<<<< HEAD
=======

>>>>>>> Steven
        this.context = activity;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString("vakantieId",vakantie.getId());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState !=null)
        {
            this.vakantie = JoetzDB.getDbInstance(context).getVakantie(savedInstanceState.getString("vakantieId"));
        }

        View view = inflater.inflate(R.layout.fragment_vakantie_algemeen, container, false);

        if(vakantie != null)
        {
            titelLbl = (TextView)view.findViewById(R.id.titlelbl);
            promotekstLbl= (TextView)view.findViewById(R.id.promotekstlbl);
            foto = (ImageView)view.findViewById(R.id.vakantie_algemeen_imageview);

            fillUpImage();
            titelLbl.setText( vakantie.getNaam());
            promotekstLbl.setText( vakantie.getPromoTekst());

        }

        return view;
    }


    @Override
    public String getaTag() {
        return TAG;
    }

    private void fillUpImage()
    {
        int max_width = 1024;
        int max_height = 768;

        int size = (int)Math.ceil(Math.sqrt(max_height*max_width));
        Log.d(TAG, "width " + max_width + "height " + max_height + " size " + size);

        Picasso.with(context)
                .load(vakantie.getFotos().get(0).getNaam())
                .transform(new BitmapTransformation(max_width,max_height))
                .skipMemoryCache()
                .resize(size,size)
                .centerInside()
                .into(foto);
    }
}
