package com.example.steven.joetzandroid.Activities;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.steven.joetzandroid.Adapters.ImageViewAdapter;
import com.example.steven.joetzandroid.Domain.BitmapTransformation;
import com.example.steven.joetzandroid.Domain.Foto;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;
import com.squareup.picasso.Picasso;

import org.lucasr.twowayview.TwoWayView;


public class VakantieFotoGalery extends VakantieDetailFragment {
    private Context context;
    private Button slideShowButton;
    private ImageView img;
    private static final String TAG = "VakantieFotoGalery";
    public VakantieFotoGalery() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vakantie_foto_galery, container, false);
        //GridView gridView = (GridView)v.findViewById(R.id.images_grid);
        TwoWayView horizontalListView = (TwoWayView)v.findViewById(R.id.foto_gallery_foto_list);

        slideShowButton = (Button)v.findViewById(R.id.foto_gallery_button);
        img = (ImageView)v.findViewById(R.id.foto_gallery_image_view);
        Foto f = vakantie.getFotos().get(0);
        if(f.getImage() != null)
        {
            img.setImageBitmap(f.getImage());
        }
        else
        {
            int max_width = 1024;
            int max_height = 768;

            int size = (int)Math.ceil(Math.sqrt(max_height*max_width));
            Log.d(TAG,"width "+max_width+"height "+max_height+" size "+size);

            Picasso.with(context)
                    .load(f.getNaam())
                    .transform(new BitmapTransformation(max_width,max_height))
                    .skipMemoryCache()
                    .resize(size,size)
                    .centerInside()
                    .into(img);
        }

        ImageViewAdapter adapter = new ImageViewAdapter(context,vakantie);
        horizontalListView.setAdapter(adapter);
       // gridView.setAdapter(adapter);
        horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Foto foto = vakantie.getFotos().get(i);
                if (foto.getImage() != null) {
                    img.setImageBitmap(foto.getImage());
                } else {
                    int max_width = 1024;
                    int max_height = 768;

                    int size = (int)Math.ceil(Math.sqrt(max_height*max_width));
                    Picasso.with(context)
                            .load(foto.getNaam())
                            .transform(new BitmapTransformation(max_width,max_height))
                            .skipMemoryCache()
                            .resize(size, size)
                            .centerInside()
                            .into(img);
                }
            }
        });

        slideShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                VakantieFotosFragment fotosFragment = new VakantieFotosFragment();
                fotosFragment.setVakantie(vakantie);
                manager.beginTransaction().addToBackStack(null).replace(R.id.frame_vakantie_details,fotosFragment).commit();
            }
        });

          return v;
    }


}
