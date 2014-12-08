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
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.steven.joetzandroid.Adapters.FlipperViewAdapter;
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
    private AdapterViewFlipper flipper;
    private RelativeLayout layout;
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

    //private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vakantie_fotos, container, false);
        layout = (RelativeLayout)v.findViewById(R.id.vakantie_fotos_top_layout);
        flipper = (AdapterViewFlipper)v.findViewById(R.id.foto_flipper);
        FlipperViewAdapter fva = new FlipperViewAdapter(context,vakantie.getFotos(),this.layout);
        flipper.setAdapter(fva);
        flipper.startFlipping();
        final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
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

                    flipper.showNext();
                    Log.d(TAG,"move right");
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

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


    @Override
    public void onPause() {
        super.onPause();
        flipper.stopFlipping();
    }
}
