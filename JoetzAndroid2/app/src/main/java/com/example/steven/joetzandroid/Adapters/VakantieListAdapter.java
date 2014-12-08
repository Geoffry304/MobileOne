package com.example.steven.joetzandroid.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
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


import com.example.steven.joetzandroid.Domain.CustomHttpClient;
import com.example.steven.joetzandroid.Domain.Foto;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.database.JoetzDB;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

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
        Foto foto = vakanties.get(i).getFotos().get(2);
        Log.d(TAG,"Size "+size);
        if(foto.getImage() != null)
        {


            vakantieLogoView.setImageBitmap(foto.getImage());


        }
        else if(size > -1)
        {
           // new ImageLoaderAsync(foto,this,vakanties.get(i).getId()).execute(foto.getNaam());
            //Target t = (Target)foto.getImage();

            Picasso.with(context).load(foto.getNaam()).resize(80,50).centerCrop().into(vakantieLogoView);

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

        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(context,"Download image","Loading ...");
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
           dialog.dismiss();

            if(bitmap != null)
            {


                foto.setImage(bitmap);
                JoetzDB.getDbInstance(context).updateFoto(foto, vakantieId);
                //vakantieLogoView.setImageBitmap(foto.getImage());
                adapter.notifyDataSetChanged();

            }

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try
            {

               // Bitmap bitmap = Berekeningen.getScaledBitmap(strings[0],1,360,640);
                Bitmap bitmap = Picasso.with(context).load(foto.getNaam()).get();
                return bitmap;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
/*
        private Bitmap downloadImage(String... urls) {
            HttpClient httpClient = CustomHttpClient.getHttpClient();
            try {
                HttpGet request = new HttpGet(urls[0]);
                HttpParams params = new BasicHttpParams();
                HttpConnectionParams.setSoTimeout(params, 60000); // 1 minute
                request.setParams(params);

                //publishProgress(25);

                HttpResponse response = httpClient.execute(request);

                //publishProgress(50);

                byte[] image = EntityUtils.toByteArray(response.getEntity());

                //publishProgress(75);

                Bitmap mBitmap = BitmapFactory.decodeByteArray(image, 0,
                        image.length);

                //publishProgress(100);

                return mBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        */
    }

}
