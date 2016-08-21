package com.example.kim.fishingdoc.weather.mFragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.weather.Hour_ListData;
import com.example.kim.fishingdoc.weather.Hour_ListView;


/**
 * Created by kim on 2016-08-18.
 */
public class HourFragment extends Fragment {

    private ListView mlist;
    private Hour_ListView mlistview;

    private Handler mHandler;
    private ProgressDialog mProgressDialog;

    public HourFragment(){

    }
    public static HourFragment newInstance(int SectionNumber) {
        HourFragment fragment = new HourFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", SectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.hour_fragment, container, false);

        mlistview = new Hour_ListView();
        mlist = (ListView)rootview.findViewById(R.id.hour_list);
        mlist.setAdapter(mlistview);

        //list의 아이콘와 내용
        mlistview.addItem("0시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"27ºC","10%");
        mlistview.addItem("3시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"26ºC","10%");
        mlistview.addItem("6시", ContextCompat.getDrawable(getActivity(), R.drawable.cloudy),"26ºC","25%");
        mlistview.addItem("9시", ContextCompat.getDrawable(getActivity(), R.drawable.cloud),"30ºC","59%");
        mlistview.addItem("12시", ContextCompat.getDrawable(getActivity(), R.drawable.rainy),"31ºC","98%");
        mlistview.addItem("15시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"33ºC","20%");
        mlistview.addItem("18시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"30ºC","20%");
        mlistview.addItem("21시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"28ºC","15%");

        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hour_ListData mData = mlistview.mlistData.get(i);
                Toast.makeText(getActivity(),mData.mtext1,Toast.LENGTH_SHORT).show();
            }
        });

        //로딩
        mHandler = new Handler();
        getActivity().runOnUiThread(new Runnable(){

            @Override
            public void run() {
                mProgressDialog = ProgressDialog.show(getActivity(),"","잠시만 기다려 주세요.", true);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            if(mProgressDialog!=null&&mProgressDialog.isShowing()){
                                mProgressDialog.dismiss();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },2500);
            }
        });
        return rootview;
    }
}
