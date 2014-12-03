package com.example.steven.joetzandroid.Activities;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.steven.joetzandroid.Domain.Foto;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class VakantieFotosFragment extends VakantieDetailFragment{
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private  static final String TAG = "VakantieFotosFragment";
    public Vakantie vakantie;
    public Context context;
    private ViewFlipper flipper;
    private ArrayList<ImageView>imageViews;
    public VakantieFotosFragment() {
        // Required empty public constructor
        imageViews = new ArrayList<ImageView>();
    }
    public void setVakantie(Vakantie vakantie)
    {
        this.vakantie = vakantie;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity.getApplicationContext();
    }

    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vakantie_fotos, container, false);
        flipper = (ViewFlipper)v.findViewById(R.id.foto_flipper);

        new ImageLoader().execute(vakantie.getNaam());
        flipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detector.onTouchEvent(motionEvent);

                return true;
            }
        });

        return v;
    }

    public class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {

                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    flipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.left_in));
                    flipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.left_out));
                    flipper.showNext();
                    Log.d(TAG,"move right");
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    flipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.right_in));
                    flipper.setOutAnimation(AnimationUtils.loadAnimation(context,R.anim.right_out));
                    flipper.showPrevious();
                    Log.d(TAG,"move left");
                    return true;
                }



            }catch (Exception e)
            {
                e.printStackTrace();
            }


            return false;
        }
    }

    public class ImageLoader extends AsyncTask<String,String,Bitmap>
    {
        private static final String TAG = "ImageLoader";

        private Foto f;
        public ImageLoader(){

        }

        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            Log.d(TAG,"progress dialog started");
           /* dialog = new ProgressDialog(context);
            dialog.setMessage("Downloading ... ");
            dialog.show();*/

        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap bitmap = null;
            try {
                  for (Foto f: vakantie.getFotos())
                  {
                      ImageView v = new ImageView(context);
                      if(f.getImage() == null)
                      {

                          URL url = new URL(f.getNaam());
                          Bitmap bitmap1 = BitmapFactory.decodeStream(url.openStream());
                          f.setImage(bitmap1);
                          v.setImageBitmap(bitmap1);
                          imageViews.add(v);
                          //flipper.addView(v);
                      }
                      else
                      {
                          v.setImageBitmap(f.getImage());
                      }

                  }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //dialog.dismiss();
            for(ImageView v : imageViews)
            {
                flipper.addView(v);
            }

        }
    }

}
