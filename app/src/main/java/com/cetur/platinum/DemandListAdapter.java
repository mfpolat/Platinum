package com.cetur.platinum;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cetur.model.Demand;

import java.util.ArrayList;

/**
 * Created by Fatih on 4.8.2015.
 */
public class DemandListAdapter extends BaseAdapter {


    private Context mcContext;
    private ArrayList<Demand> demands;
    private ViewHolder mHolder;

    public DemandListAdapter(Context context,ArrayList<Demand> demandArrayList){
        mcContext = context;
        demands = demandArrayList;
    }
    @Override
    public int getCount() {
        return demands.size();
    }

    @Override
    public Object getItem(int i) {
        return demands.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            mHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)mcContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.demand_list_item,null);
            mHolder.demandListItemNumberTV =(TextView)view.findViewById(R.id.demandListItemNumberTV);
            mHolder.demandListItemTV =(TextView)view.findViewById(R.id.demandListItemTV);
            view.setTag(mHolder);
        }
        else{
            mHolder = (ViewHolder)view.getTag();
        }
        mHolder.demandListItemNumberTV.setText(String.valueOf(i +1));
        Typeface face = Typeface.createFromAsset(mcContext.getAssets(), "fonts/Helvetica.ttf");
        mHolder.demandListItemNumberTV.setTypeface(face);
        mHolder.demandListItemTV.setTypeface(face);
        return view;
    }

    private class ViewHolder{
        TextView demandListItemNumberTV,demandListItemTV;
    }
}
