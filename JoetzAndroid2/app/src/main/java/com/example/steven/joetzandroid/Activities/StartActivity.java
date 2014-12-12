package com.example.steven.joetzandroid.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.steven.joetzandroid.Adapters.NavDrawerListAdapter;
import com.example.steven.joetzandroid.Domain.CreateDummyData;
import com.example.steven.joetzandroid.Domain.NavDrawerItem;
import com.example.steven.joetzandroid.Domain.Ouder;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.firebase.FirebaseAuth;
import com.example.steven.joetzandroid.firebase.FirebaseMessages;
import com.example.steven.joetzandroid.firebase.FirebaseProfile;
import com.example.steven.joetzandroid.firebase.FirebaseVakantieRepository;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StartActivity extends FragmentActivity implements AdapterView.OnItemClickListener{

    private final String TAG = "StartActitivty";

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerList;
    private LinearLayout drawerLinear;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    //hasmap die de fragmenten bevat met als key de string die in de lijst staat, later gewoon Fragment openen uit hashmap
    private HashMap<String, Fragment> fragmentHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_main);
        fragmentHashMap = new HashMap<String, Fragment>();
        mTitle = mDrawerTitle = getTitle();

        //createTabbarVakantie();
        //setActionBarOff();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        drawerLinear = (LinearLayout)findViewById(R.id.drawer_linear);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.list_slidermenu);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0],navMenuIcons.getResourceId(0,-1)));
        fragmentHashMap.put(navMenuTitles[0], new About());
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        fragmentHashMap.put(navMenuTitles[1], new VakantiePeriodeFragment());
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3],navMenuIcons.getResourceId(3,-1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4],navMenuIcons.getResourceId(4,-1)));
        fragmentHashMap.put(navMenuTitles[4],new CommunityFragment());
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5],navMenuIcons.getResourceId(5,-1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6],navMenuIcons.getResourceId(6,-1)));
        fragmentHashMap.put(navMenuTitles[6],new VakantieAlgemeenFragment());

        //navDrawerItems.add(new NavDrawerItem(navMenuTitles[7],navMenuIcons.getResourceId(7,-1)));

        navMenuIcons.recycle();

        adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(this);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name){
            public void onDrawerClosed(View view){
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
            public void onDrawerOpenend(View view)
            {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }

        };
        drawerLayout.setDrawerListener(drawerToggle);



        if(savedInstanceState == null)
        {

            if(getIntent().getExtras()!=null)
            {
                Bundle extras = getIntent().getExtras();
                String vakantiePeriodeNaam = extras.getString("vakantiePeriodeId");
                VakantiesFragment fragment = new VakantiesFragment();
                fragment.setVakantiePeriodeId(vakantiePeriodeNaam);
                getSupportFragmentManager().beginTransaction().add(R.id.frame_container,fragment).addToBackStack(null).commit();

            }
            else
            {
                About about = new About();
                getSupportFragmentManager().beginTransaction().add(R.id.frame_container, about).addToBackStack(null).commit();
            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item))
        {
            Log.d(TAG,"drawerToggle onOptionItemSelected");
            return true;
        }
        switch (item.getItemId())
        {
            case R.id.action_login:
                if(FirebaseAuth.getUser()!=null)
                {
                    ProfileFragment f = new ProfileFragment();
                    FirebaseProfile ouderProfile = new FirebaseProfile(new FirebaseAuth());
                    Ouder o = ouderProfile.getOuder();
                    f.setOuder(o);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container,f).commit();
                    drawerLayout.closeDrawers();
                    break;

                }
                else {
                    LoginFragment f = new LoginFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, f).commit();
                    drawerLayout.closeDrawers();
                    break;
                }

        }
        return super.onOptionsItemSelected(item);
    }

    //na het aanroepen van invalidateOptionsMenu()
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerLinear);
        menu.findItem(R.id.action_login).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        getActionBar().setTitle(mTitle);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String item = navDrawerItems.get(i).getTitle();
        if(fragmentHashMap.containsKey(item))
        {
            Fragment f = fragmentHashMap.get(item);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,f).addToBackStack(f.getTag()).commit();
            drawerLayout.closeDrawers();
        }
    }
}
