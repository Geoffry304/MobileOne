package com.example.steven.joetzandroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steven.joetzandroid.Domain.NavDrawerItem;
import com.example.steven.joetzandroid.R;

import java.util.ArrayList;

/**
 * Created by Steven on 4/11/14.
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }


    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return navDrawerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_list_item,null);
        }
        ImageView imgIcon = (ImageView)view.findViewById(R.id.list_item_image);
        TextView txtTitle = (TextView)view.findViewById(R.id.list_item_textview);

        imgIcon.setImageResource(navDrawerItems.get(i).getIcon());
        txtTitle.setText(navDrawerItems.get(i).getTitle());

        return view;
    }
}
