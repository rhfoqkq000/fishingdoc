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
 * Created by Ryu on 2016-08-18.
 */
public class Weather_ListView extends BaseAdapter {

    public ArrayList<Weather_ListData> mlistDataWeather = new ArrayList<>();

    public Weather_ListView(){
    }

    public class ViewHolder{
        public ImageView micon;
        public TextView mtext;
    }

    @Override
    public int getCount() {
        return mlistDataWeather.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistDataWeather.get(position);
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
            convertView = inflater.inflate(R.layout.listview, parent, false);
        }

        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.mimage);
        TextView textView = (TextView)convertView.findViewById(R.id.mtext);

        Weather_ListData weatherListData = mlistDataWeather.get(position);

        iconImageView.setImageDrawable(weatherListData.getIcon());
        textView.setText(weatherListData.getText());

        return convertView;
    }

    public void addItem(Drawable icon, String text){
        Weather_ListData addInfo = new Weather_ListData();
        addInfo.micon = icon;
        addInfo.mtext = text;

        mlistDataWeather.add(addInfo);
    }
}
