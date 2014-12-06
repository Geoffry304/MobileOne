package com.example.steven.joetzandroid.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.steven.joetzandroid.Domain.BitmapTransformation;
import com.example.steven.joetzandroid.Domain.Foto;
import com.example.steven.joetzandroid.database.JoetzDB;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Steven on 4/12/14.
 */
public class FlipperViewAdapter extends BaseAdapter{
    private static final String TAG = "FlipperViewAdapter";

    private Context context;
    private JoetzDB db;
    private ArrayList<Foto>fotos;
    private RelativeLayout layout;

    public FlipperViewAdapter(Context context,ArrayList<Foto>fotos,RelativeLayout layout) {

        this.context = context;
        db = JoetzDB.getDbInstance(context);
        this.fotos = fotos;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return fotos.size();
    }

    @Override
    public Object getItem(int i) {
        return fotos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView img = null;
        Foto f = fotos.get(i);
        if(view == null)
        {
            img = new ImageView(context);

            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(8,8,8,8);
        }
        else
        {
            img = (ImageView)view;
        }
        if(f.getImage()==null)
        {
            int max_width = 1024;
            int max_height = 768;
                int size = (int)Math.ceil(Math.sqrt(max_width*max_height));


            Log.d(TAG,"width "+max_width+" height "+max_height+" size "+size);
                Picasso.with(context)
                        .load(f.getNaam())
                        .transform(new BitmapTransformation(max_width,max_height))
                        .skipMemoryCache()
                        .resize(size, size)
                        .centerInside()
                        .into(img);


            //new ImageLoaderAsync(vakantie.getFotos().get(i),this,vakantie.getId()).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, vakantie.getFotos().get(i).getNaam());


        }
        else
        {
            img.setImageBitmap(fotos.get(i).getImage());
        }


        return img;
    }
}
