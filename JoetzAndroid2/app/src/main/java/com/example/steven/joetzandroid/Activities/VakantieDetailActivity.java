package com.example.steven.joetzandroid.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.steven.joetzandroid.Domain.Ouder;
import com.example.steven.joetzandroid.Domain.Vakantie;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.database.JoetzDB;
import com.example.steven.joetzandroid.firebase.FirebaseAuth;
import com.example.steven.joetzandroid.firebase.FirebaseProfile;


import java.util.HashMap;
import java.util.Map;

public class VakantieDetailActivity extends FragmentActivity implements ActionBar.TabListener {

    private Vakantie vakantie;
    private Map<String,VakantieDetailFragment> fragmentMap;
    private JoetzDB db;

    public VakantieDetailActivity(Vakantie vakantie)
    {
        if (vakantie != null)
        {
            this.vakantie = vakantie;
        }

        this.fragmentMap = new HashMap<String, VakantieDetailFragment>();

    }
    public VakantieDetailActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vakantie_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("vakantieId");
        this.db = JoetzDB.getDbInstance(this);
        vakantie = db.getVakantie(id);
        createTabbarVakantie();

        VakantieFotoGalery fotoGalery = new VakantieFotoGalery();
        fotoGalery.setVakantie(vakantie);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame_vakantie_details,fotoGalery).addToBackStack(null).commit();

    }

    private ActionBar actionbar;
    public void createTabbarVakantie()
    {
        actionbar = this.getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        String[]items = getResources().getStringArray(R.array.tab_bar_items);

        for(int i = 0;i<items.length;i++)
        {
            actionbar.addTab(actionbar.newTab().setText(items[i]).setTabListener(this));

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vakantie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_login:
                if (FirebaseAuth.getUser() != null) {
                    ProfileFragment f = new ProfileFragment();
                    FirebaseProfile ouderProfile = new FirebaseProfile(new FirebaseAuth());
                    Ouder o = ouderProfile.getOuder();
                    f.setOuder(o);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, f).commit();

                    break;

                } else {
                    LoginFragment f = new LoginFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, f).commit();

                    break;
                }
        }
                return super.onOptionsItemSelected(item);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

     }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

}
