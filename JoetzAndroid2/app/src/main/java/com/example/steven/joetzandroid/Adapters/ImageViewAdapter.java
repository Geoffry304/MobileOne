package com.example.steven.joetzandroid.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;


import com.example.steven.joetzandroid.Domain.CustomHttpClient;
import com.example.steven.joetzandroid.Domain.Foto;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.database.JoetzDB;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Steven on 4/12/14.
 */
public class ImageViewAdapter extends BaseAdapter {
    private static final String TAG = "ImageViewAdapter";
    private Context context;
    private Vakantie vakantie;

    public ImageViewAdapter(Context context,Vakantie vakantie)
    {
        this.context = context;
        this.vakantie = vakantie;
        Log.d(TAG,"aantal foto's = "+vakantie.getFotos().size());
    }

    @Override
    public int getCount() {
        return vakantie.getFotos().size();
    }

    @Override
    public Object getItem(int i) {
        return vakantie.getFotos().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageView;
        Foto foto = vakantie.getFotos().get(i);
        if(view == null)
        {
            imageView = new ImageView(context);

        }
        else
        {
            imageView = (ImageView)view;
        }
        if(foto.getImage()==null)
        {
            Log.d(TAG,foto.getNaam());
            //new ImageLoaderAsync(vakantie.getFotos().get(i),this,vakantie.getId()).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, vakantie.getFotos().get(i).getNaam());
           Picasso.with(context).load(foto.getNaam()).resize(460,360).centerCrop().into(imageView);


        }
        else
        {
            imageView.setImageBitmap(vakantie.getFotos().get(i).getImage());
        }

        return imageView;
    }
  /*  private class ImageLoaderAsync extends AsyncTask<String,String,Bitmap>
    {
        private static final String TAG = "ImageLoaderAsync";
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
            dialog = ProgressDialog.show(context,"Downloading image","Loading...");
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
                Log.wtf(TAG,"Start binnenhalen van img = "+strings[0]);

                //Bitmap bitmap = Berekeningen.getScaledBitmap(strings[0],1,360,640);
                //Bitmap bitmap = Berekeningen.decodeBitmap(strings[0]);
                //Bitmap bitmap = Berekeningen.getBitmap(strings[0]);
                //Bitmap bitmap = Berekeningen.downloadBitmap(strings[0]);
                Bitmap bitmap = downloadImage(strings[0]);
                return bitmap;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
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
    }
    */
}
