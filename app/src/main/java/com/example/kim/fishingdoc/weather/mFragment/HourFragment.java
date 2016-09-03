package com.example.kim.fishingdoc.weather.mFragment;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.weather.Hour_ListData;
import com.example.kim.fishingdoc.weather.Hour_ListView;

import java.util.ArrayList;


/**
 * Created by kim on 2016-08-18.
 */
public class HourFragment extends Fragment {

    private ListView mlist;
    private Hour_ListView mlistview;

    private Handler mHandler;
    private ProgressDialog mProgressDialog;

    public static ArrayList<String> hourList2 = new ArrayList<String>();

    public static ArrayList<String> hourList;
    public static ArrayList<String> dayList;
    public static ArrayList<String> tempList;
    public static ArrayList<String> wfkorList;
    public static ArrayList<String> popList;
    public static ArrayList<String> wsList;
    public static ArrayList<String> wdList;
    public static ArrayList<String> rehList;

    int count = 0;

    public HourFragment(){

    }
    public static HourFragment newInstance(int SectionNumber) {
        HourFragment fragment = new HourFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", SectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {

            hourList = bundle.getStringArrayList("hourXml_go1");
            dayList = bundle.getStringArrayList("dayXml_go1");
            tempList = bundle.getStringArrayList("tempXml_go1");
            wfkorList = bundle.getStringArrayList("wfkorXml_go1");
            popList = bundle.getStringArrayList("popXml_go1");
            wsList = bundle.getStringArrayList("wsXml_go1");
            wdList = bundle.getStringArrayList("wdXml_go1");
            rehList = bundle.getStringArrayList("rehXml_go1");

            Log.e("Main->hour 받은 거 ", "" + hourList + "/" + dayList);
        }

        for (int i=0; i<dayList.size(); i++){
            if (dayList.get(i).equals("0")){
                count++;
            }

        }
        //Log.e("count ",""+count);

        for (int i=0; i<hourList.size(); i++){
   //         Log.e("바꿩", "" + String.format("%02d", Integer.parseInt(hourList.get(i))));
            String change = String.format("%02d", Integer.parseInt(hourList.get(i)));
            Log.e("change ",""+change);
            hourList2.add(i, change);
        }
        Log.e("hour수정 ",""+hourList2);

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.hour_fragment, container, false);

        mlistview = new Hour_ListView();
        mlist = (ListView)rootview.findViewById(R.id.hour_list);
        mlist.setAdapter(mlistview);

        mlistview.addItem("시간", Drawable.createFromPath("날씨"), "기온", "강수확률");

        for (int i=0; i<dayList.size(); i++){
            if (wfkorList.get(i).equals("맑음")){
                mlistview.addItem(hourList2.get(i)+"시", ContextCompat.getDrawable(getActivity(), R.drawable.b_sunny), tempList.get(i)+"ºC",popList.get(i)+"%");
            }else if (wfkorList.get(i).equals("구름 조금")) {
                mlistview.addItem(hourList2.get(i)+"시", ContextCompat.getDrawable(getActivity(), R.drawable.b_cloudy1), tempList.get(i)+"ºC", popList.get(i)+"%");
            } else if (wfkorList.get(i).equals("구름 많음")) {
                mlistview.addItem(hourList2.get(i)+"시", ContextCompat.getDrawable(getActivity(), R.drawable.b_cloudy2), tempList.get(i)+"ºC", popList.get(i)+"%");
            } else if (wfkorList.get(i).equals("흐림")) {
                mlistview.addItem(hourList2.get(i)+"시", ContextCompat.getDrawable(getActivity(), R.drawable.b_cloudy3), tempList.get(i)+"ºC", popList.get(i)+"%");
            } else if (wfkorList.get(i).equals("비")) {
                mlistview.addItem(hourList2.get(i)+"시", ContextCompat.getDrawable(getActivity(), R.drawable.b_rainy), tempList.get(i)+"ºC", popList.get(i)+"%");
            } else if (wfkorList.get(i).equals("눈/비")) {
                mlistview.addItem(hourList2.get(i)+"시", ContextCompat.getDrawable(getActivity(), R.drawable.b_rainsnow), tempList.get(i)+"ºC", popList.get(i)+"%");
            } else if (wfkorList.get(i).equals("눈")) {
                mlistview.addItem(hourList2.get(i), ContextCompat.getDrawable(getActivity(), R.drawable.b_snowy), tempList.get(i)+"ºC", popList.get(i)+"%");
            }
        }


        //list의 아이콘와 내용
//        mlistview.addItem("0시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"27ºC","10%");
//        mlistview.addItem("3시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"26ºC","10%");
//        mlistview.addItem("6시", ContextCompat.getDrawable(getActivity(), R.drawable.cloudy),"26ºC","25%");
//        mlistview.addItem("9시", ContextCompat.getDrawable(getActivity(), R.drawable.cloud),"30ºC","59%");
//        mlistview.addItem("12시", ContextCompat.getDrawable(getActivity(), R.drawable.rain),"31ºC","98%");
//        mlistview.addItem("15시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"33ºC","20%");
//        mlistview.addItem("18시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"30ºC","20%");
//        mlistview.addItem("21시", ContextCompat.getDrawable(getActivity(), R.drawable.sun),"28ºC","15%");

        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hour_ListData mData = mlistview.mlistData.get(i);
                Toast.makeText(getActivity(),mData.mtext1,Toast.LENGTH_SHORT).show();
            }
        });

        //로딩
//        mHandler = new Handler();
//        getActivity().runOnUiThread(new Runnable(){
//
//            @Override
//            public void run() {
//                mProgressDialog = ProgressDialog.show(getActivity(),"","잠시만 기다려 주세요.", true);
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        try{
//                            if(mProgressDialog!=null&&mProgressDialog.isShowing()){
//                                mProgressDialog.dismiss();
//                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                },2500);
//            }
//        });
        return rootview;
    }
}
