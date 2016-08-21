package com.example.kim.fishingdoc.weather;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.fishingdoc.R;

import java.util.ArrayList;

/**
 * Created by kim on 2016-08-19.
 */
public class Hour_ListView extends BaseAdapter {

    public ArrayList<Hour_ListData> mlistData = new ArrayList<Hour_ListData>();

    public Hour_ListView(){
    }

    @Override
    public int getCount() {
        return mlistData.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.hour_listview, parent, false);
        }

        ImageView hour_weather = (ImageView)convertView.findViewById(R.id.hour_weather);
        TextView hour_hour = (TextView)convertView.findViewById(R.id.hour_hour);
        TextView hour_temp = (TextView)convertView.findViewById(R.id.hour_temp);
        TextView hour_rain = (TextView)convertView.findViewById(R.id.hour_rain);

        Hour_ListData listData = mlistData.get(position);

        hour_weather.setImageDrawable(listData.getIcon1());
        hour_hour.setText(listData.getText1());
        hour_temp.setText(listData.getText2());
        hour_rain.setText(listData.getText3());

        return convertView;
    }

    public void addItem(String text1,Drawable icon1, String text2, String text3){
        Hour_ListData addInfo = new Hour_ListData();
        addInfo.mtext1 = text1;
        addInfo.micon = icon1;
        addInfo.mtext2 = text2;
        addInfo.mtext3 = text3;

        mlistData.add(addInfo);
    }
}
