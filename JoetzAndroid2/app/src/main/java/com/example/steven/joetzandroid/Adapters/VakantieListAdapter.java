package com.example.steven.joetzandroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Steven on 25/11/14.
 */
public class VakantieListAdapter extends BaseAdapter {

    private ArrayList<Vakantie> vakanties;
    private Context context;

    public VakantieListAdapter(Context context, ArrayList<Vakantie> vakanties)
    {
        this.context = context;
        this.vakanties = vakanties;

    }

    @Override
    public int getCount() {
        return vakanties.size();
    }

    @Override
    public Object getItem(int i) {
        return vakanties.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.vakanie_list_item,null);
        }
        ImageView vakantieLogoView = (ImageView)view.findViewById(R.id.vakantie_item_image);
        TextView vakantieTitleTxt = (TextView)view.findViewById(R.id.vakantie_item_title);
        TextView vakantiePlaatsTxt = (TextView)view.findViewById(R.id.vakantie_item_plaats);
        TextView vakantieLeeftijdTxt = (TextView)view.findViewById(R.id.vakantie_item_leeftijdcat);


       /* Bitmap img = vakanties.get(i).getFotos().get(i).getImage();
        if(img != null)
        {
            vakantieLogoView.setImageBitmap(img);

        }
        else
        {
            //Async task to download the image
        }
        */
        vakantieTitleTxt.setText(vakanties.get(i).getNaam());
        vakantiePlaatsTxt.setText(vakanties.get(i).getVakantiePlaats().getNaam());
        vakantieLeeftijdTxt.setText(vakanties.get(i).getLeeftijdsCategorie().getVanTotLeeftijdString());

        return view;
    }
}
