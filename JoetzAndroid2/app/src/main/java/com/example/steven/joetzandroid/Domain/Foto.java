package com.example.steven.joetzandroid.Domain;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.BaseAdapter;

import com.example.steven.joetzandroid.database.JoetzDB;
import com.shaded.fasterxml.jackson.databind.deser.Deserializers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Steven on 24/10/14.
 */
public class Foto {

    private String naam;
    private int id;
    private Bitmap image;
    private String message;

    public Foto(String naam, int id, String message) {
        this.naam = naam;
        this.id = id;
        this.message = message;
    }

    public Foto() {
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void loadImage(String vakantieId,JoetzDB db)
    {
        new ImageLoadTask(vakantieId,db).execute(getNaam());
    }
    private Foto returnSelf()
    {
        return this;
    }

     private class ImageLoadTask extends AsyncTask<String,String,Bitmap> {

         private static final String TAG = "Imageloadtask";

         private String vakantieId;
         private JoetzDB db;
         public ImageLoadTask(String vakantieId,JoetzDB db)
         {
           this.vakantieId = vakantieId;
             this.db = db;
         }

         @Override
         protected void onPreExecute() {
             Log.i(TAG,"Loading image "+getNaam());

         }

         @Override
         protected void onPostExecute(Bitmap bitmap) {

            if (bitmap != null)
            {
                Log.i(TAG,"Successfully loaded "+naam+" image");
                setImage(bitmap);
                db.updateFoto(returnSelf(),vakantieId);

            }
             else
            {
                Log.e(TAG,"Shit");
            }
         }



         @Override
         protected Bitmap doInBackground(String... strings) {
             Log.d(TAG,"Attempting to load image URL : "+getNaam());
             try
             {
                 URL url = new URL(getNaam());
                 Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                 return bitmap;


             } catch (Exception e) {
                 e.printStackTrace();
                 return null;
             }

         }
     }

}