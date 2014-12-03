package com.example.steven.joetzandroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steven.joetzandroid.Domain.Foto;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.database.JoetzDB;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Steven on 25/11/14.
 */
public class VakantieListAdapter extends BaseAdapter {
    private static final String TAG = "VakantieListAdapter";
    private ArrayList<Vakantie> vakanties;
    private Context context;
    private ImageView vakantieLogoView;
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
        vakantieLogoView = (ImageView)view.findViewById(R.id.vakantie_item_image);
        TextView vakantieTitleTxt = (TextView)view.findViewById(R.id.vakantie_item_title);
        TextView vakantiePlaatsTxt = (TextView)view.findViewById(R.id.vakantie_item_plaats);
        TextView vakantieLeeftijdTxt = (TextView)view.findViewById(R.id.vakantie_item_leeftijdcat);


        int size = vakanties.get(i).getFotos().size()-1;
        Foto foto = vakanties.get(i).getFotos().get(size);
        Log.d(TAG,"Size "+size);
        if(foto.getImage() != null)
        {

            vakantieLogoView.setImageBitmap(vakanties.get(i).getFotos().get(size).getImage());


        }
        else if(size > -1)
        {
            new ImageLoaderAsync(foto,this,vakanties.get(i).getId()).execute(foto.getNaam());
        }


        vakantieTitleTxt.setText(vakanties.get(i).getNaam());
        vakantiePlaatsTxt.setText(vakanties.get(i).getVakantiePlaats().getNaam());
        vakantieLeeftijdTxt.setText(vakanties.get(i).getLeeftijdsCategorie().getVanTotLeeftijdString());

        return view;
    }

    private class ImageLoaderAsync extends AsyncTask<String,String,Bitmap>
    {
        private BaseAdapter adapter;
        private Foto foto;
        private String vakantieId;

        public ImageLoaderAsync(Foto foto, BaseAdapter baseAdapter,String vakantieId)
        {
            this.vakantieId = vakantieId;
            this.foto = foto;
           this.adapter = baseAdapter;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null)
            {
                foto.setImage(bitmap);
                //vakantieLogoView.setImageBitmap(bitmap);

                adapter.notifyDataSetChanged();
                JoetzDB.getDbInstance(context).updateFoto(foto,vakantieId);
            }

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try
            {
                URL url = new URL(strings[0]);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                return bitmap;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
