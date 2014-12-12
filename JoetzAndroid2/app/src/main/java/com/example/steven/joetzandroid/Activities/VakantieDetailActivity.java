package com.example.steven.joetzandroid.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.util.Log;
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
    private static final String TAG = "VakantieDetailActivity";
    private Vakantie vakantie;
    private Map<String,VakantieDetailFragment> fragmentMap;
    private JoetzDB db;

    public VakantieDetailActivity(Vakantie vakantie)
    {
        if (vakantie != null)
        {
            this.vakantie = vakantie;
        }



    }
    public VakantieDetailActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vakantie_detail);
        this.fragmentMap = new HashMap<String, VakantieDetailFragment>();
        Intent intent = getIntent();
        String id = intent.getStringExtra("vakantieId");
        this.db = JoetzDB.getDbInstance(this);
        vakantie = db.getVakantie(id);
        createTabbarVakantie();


       /* VakantieAlgemeenFragment f = new VakantieAlgemeenFragment();
        f.setVakantie(vakantie);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame_vakantie_details,f).addToBackStack(null).commit();
*/
    }

    private ActionBar actionbar;
    public void createTabbarVakantie()
    {
        actionbar = this.getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        String[]items = getResources().getStringArray(R.array.tab_bar_items);
        fillHashMap(items);
        for(int i = 0;i<items.length;i++)
        {
            actionbar.addTab(actionbar.newTab().setText(items[i]).setTabListener(this));

        }

        actionbar.setDisplayHomeAsUpEnabled(true);


    }
    public void fillHashMap(String[]items)
    {
        //fragmenten toevoegen

<<<<<<< HEAD
        VakantieDetailFragment[]fragmenten = {new VakantieAlgemeenFragment(), new VakantieLocatieFragment(), new VakantieFotoGalery(), new VakantieDetailPeriodesFragment()};
        //VakantieDetailFragment[]fragmenten = {new VakantieFotoGalery(),new VakantieAlgemeenFragment(),new VakantieDetailPeriodesFragment(),new VakantieDetailInschrijving()};
=======
        VakantieDetailFragment[]fragmenten = {new VakantieAlgemeenFragment(), new VakantieLocatieFragment(), new VakantieFotoGalery(), new VakantieDetailPeriodesFragment(),new VakantieDetailInschrijving()};
>>>>>>> Steven

        for(String item : items)
        {
            for(VakantieDetailFragment fag : fragmenten)
            {
                if(fag.getaTag().equals(item))
                {
                    fragmentMap.put(item,fag);
                    Log.d(TAG,"Key : "+item+" Fragment : "+fag.getaTag());
                }

            }

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

            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.putExtra("vakantiePeriodeId",vakantie.getVakantiePeriode().getVakantieNaam());
                NavUtils.navigateUpTo(this,upIntent);
                break;
            case R.id.action_login:
                if (FirebaseAuth.getUser() != null) {
                    ProfileFragment f = new ProfileFragment();
                    FirebaseProfile ouderProfile = new FirebaseProfile(new FirebaseAuth());
                    Ouder o = ouderProfile.getOuder();
                    f.setOuder(o);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_vakantie_details, f).commit();

                    break;

                } else {
                    LoginFragment f = new LoginFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_vakantie_details, f).commit();

                    break;
                }
        }
                return super.onOptionsItemSelected(item);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        String key = tab.getText().toString();
        if(fragmentMap.containsKey(key))
        {
            VakantieDetailFragment fragment = fragmentMap.get(key);
            FragmentManager manager = getSupportFragmentManager();
            fragment.setVakantie(this.vakantie);
            manager.beginTransaction().replace(R.id.frame_vakantie_details,fragment).addToBackStack(null).commit();
        }

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

     }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

}
