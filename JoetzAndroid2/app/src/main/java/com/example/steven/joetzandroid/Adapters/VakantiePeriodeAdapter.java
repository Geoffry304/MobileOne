package com.example.steven.joetzandroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.steven.joetzandroid.Domain.VakantiePeriode;
import com.example.steven.joetzandroid.R;

import java.util.ArrayList;

/**
 * Created by Steven on 25/11/14.
 */
public class VakantiePeriodeAdapter extends BaseAdapter{

    private ArrayList<String> periodes;
    private Context context;

    public VakantiePeriodeAdapter(Context context, ArrayList<String>periodes)
    {
        this.periodes = periodes;
        this.context = context;
    }



    @Override
    public int getCount() {
        return periodes.size();
    }

    @Override
    public Object getItem(int i) {
        return periodes.get(i);
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
            view = inflater.inflate(R.layout.vakantie_periode_list_item,null);
        }
        TextView periodeTxt = (TextView)view.findViewById(R.id.vakantie_periode_textview);
        periodeTxt.setText(periodes.get(i));


        return view;
    }


}
