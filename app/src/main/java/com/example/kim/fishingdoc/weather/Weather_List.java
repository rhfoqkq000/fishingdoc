package com.example.kim.fishingdoc.weather;

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

import com.example.kim.fishingdoc.Json;
import com.example.kim.fishingdoc.Json2;
import com.example.kim.fishingdoc.Json3;
import com.example.kim.fishingdoc.R;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Ryu on 2016-08-19.
 */
public class Weather_List extends Fragment {

    private Weather_ListView madapter;
    private ListView mlist;
    final String url = "http://45.76.96.142:3000/crawler/";
    public Json json;
    public Json2 json2;
    public Json3 json3;
    String locEncode;
    String year;
    String month;
    String day;
    String hour;
    String min;
    String sec;

    HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
    HashMap<String, ArrayList<String>> hash2 = new HashMap<String, ArrayList<String>>();
    HashMap<String, ArrayList<String>> hash3 = new HashMap<String, ArrayList<String>>();

    ArrayList<String> dataList = new ArrayList<String>();
    ArrayList<String> heightList = new ArrayList<String>();
    ArrayList<String> lunaList = new ArrayList<String>();
    ArrayList<String> dateList = new ArrayList<String>();
    ArrayList<String> moonRiseList = new ArrayList<String>();
    ArrayList<String> moonIngList = new ArrayList<String>();
    ArrayList<String> moonSetList = new ArrayList<String>();

    ArrayList<String> latitude = new ArrayList<String>();
    ArrayList<String> longitude = new ArrayList<String>();

    ArrayList<String> sido_kr = new ArrayList<String>();
    ArrayList<String> sido_en = new ArrayList<String>();
    ArrayList<String> location = new ArrayList<String>();

    //oncreate rootview /바깥 getactivity
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weather_list, container, false);

        String date = getDateString();
//        Log.i("date : ", date);
        String date2 = date.split(" ")[0];
        String date3 = date.split(" ")[1];
        year = date2.split("-")[0];
        month = date2.split("-")[1];
        day = date2.split("-")[2];
//        Log.e("day : ", day);
        hour = date3.split(":")[0];
        min = date3.split(":")[1];
        sec = date3.split(":")[2];

        try{
            json = new Json();
            hash = json.execute(url+"getCido").get();
            Log.i("activity에서 hash떳냐!!", ""+hash);
            latitude = hash.get("latitude");
            longitude = hash.get("longitude");
            sido_kr = hash.get("sido_kr");
            sido_en = hash.get("sido_en");
            location = hash.get("location");

            madapter = new Weather_ListView();
            mlist = (ListView) rootView.findViewById(R.id.listView);
//            Log.i("list 생성!!", "");
        }catch(Exception e){
            e.printStackTrace();
        }

//
//        try{
//            json = new Json();
//            json.execute(url+"getCido");
//            Log.i("hash떴냐", ""+hash);
//
//            madapter = new Weather_ListView();
//            mlist = (ListView) getActivity().findViewById(R.id.listView);
////                Log.i("list 생성!!", "");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        mlist.setAdapter(madapter);
//
//        for(int i=0; i<hash.get("sido_kr").size(); i++){
//            madapter.addItem(ContextCompat.getDrawable(getContext(),R.drawable.wave1), hash.get("sido_kr").get(i));
//
//            mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Weather_ListData mData = madapter.mlistDataWeather.get(position);
//                    Log.i("누른거 : ", "" + mData.mtext);
//                    String en = json.getsido(mData.mtext).toString();
//                    String loc = json.getlocation(mData.mtext).toString();
//                    Log.i("eng : " + en, " / loc : " + loc);
//
//                    try {
//                        locEncode = URLEncoder.encode(loc, "UTF-8");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    String add = url+"sidoMoon/sidocode/"+locEncode+"/"+year+"/"+month;
//                    Log.i("url : ", add);
//
//                    json2 = new Json2();
//                    json2.execute(url + en + "/" + year + month);
//
//                    json3 = new Json3();
//                    json3.execute(add);
//
//                    Handler handler = new Handler();
//                    new Handler().postDelayed(new Runnable() {// 1 초 후에 실행
//
//                        @Override
//                        public void run() {
//                            String luna = json2.getLuna(day).toString();
//                            String height = json2.getHeight(day).toString();
//                            String moonRise = json3.getRise(day).toString();
//                            String moonIng = json3.getIng(day).toString();
//                            String moonSet = json3.getSet(day).toString();
//                            Log.e("오늘 음력 : ", luna);
//                            Log.e("오늘 간만 : ", height);
//                            Log.e("오늘 월출 : ", moonRise);
//                            Log.e("오늘 남중 : ", moonIng);
//                            Log.e("오늘 월몰 : ", moonSet);
//                        }
//                    }, 2000);
//
//                    Log.i("위도", "" + json.latitude.get(position));
//                    Log.i("경도", "" + json.longitude.get(position));
//
//                    Fragment weatherfragment =  new WeatherFragment();
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_id, weatherfragment).commit();
//                }
//            });
        json = new Json();
        json.execute(url+"getCido");
//        Log.i("실행!!", "");

        madapter = new Weather_ListView();
        mlist = (ListView) rootView.findViewById(R.id.listView);
//        Log.i("list 생성!!", "");

                mlist.setAdapter(madapter);
//                Log.i("두번째 execute list : ", "" + hash.get("sido_kr"));
                for (int i = 0; i < hash.get("sido_kr").size(); i++) {
                    madapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.wave1), hash.get("sido_kr").get(i));
                }



        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Weather_ListData mData = madapter.mlistDataWeather.get(i);
//                Log.i("누른거 : ", "" + mData.mtext);
                String en = getsido(mData.mtext).toString();
                String loc = getlocation(mData.mtext).toString();
//                Log.i("eng : " + en, " / loc : " + loc);

                try {
                    locEncode = URLEncoder.encode(loc, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String add = url+"sidoMoon/sidocode/"+locEncode+"/"+year+"/"+month;
//                Log.i("url : ", add);

                try{
                    json2 = new Json2();
                    hash2 = json2.execute(url + en + "/" + year + month).get();
                    dataList = hash2.get("date");
                    heightList = hash2.get("height");
                    lunaList = hash2.get("luna");
                }catch(Exception e){
                    e.printStackTrace();
                }

                try{
                    json3 = new Json3();
                    hash3 = json3.execute(add).get();

                    String luna = getLuna(day).toString();
                    String height = getHeight(day).toString();
                    String moonRise = getRise(day).toString();
                    String moonIng = getIng(day).toString();
                    String moonSet = getSet(day).toString();
//                    Log.e("오늘 음력 : ", luna);
//                    Log.e("오늘 간만 : ", height);
//                    Log.e("오늘 월출 : ", moonRise);
//                    Log.e("오늘 남중 : ", moonIng);
//                    Log.e("오늘 월몰 : ", moonSet);
                }catch(Exception e){
                    e.printStackTrace();
                }





//                Log.i("위도", "" + latitude.get(i));
//                Log.i("경도", "" + longitude.get(i));
//                Log.i("음력", "" + json2.luna.get(i));
//                Log.i("월출", "" + json3.moonRise.get(i));
                json2 = new Json2();
                json2.execute(url + en + "/" + year + month);

                json3 = new Json3();
                json3.execute(add);

                Handler handler = new Handler();
                new Handler().postDelayed(new Runnable() {// 1 초 후에 실행

                    @Override
                    public void run() {
                        String luna = getLuna(day).toString();
                        String height = getHeight(day).toString();
                        String moonRise = getRise(day).toString();
                        String moonIng = getIng(day).toString();
                        String moonSet = getSet(day).toString();
//                        Log.e("오늘 음력 : ", luna);
//                        Log.e("오늘 간만 : ", height);
//                        Log.e("오늘 월출 : ", moonRise);
//                        Log.e("오늘 남중 : ", moonIng);
//                        Log.e("오늘 월몰 : ", moonSet);
                    }
                }, 2000);

//                Log.i("위도", "" + json.latitude.get(i));
//                Log.i("경도", "" + json.longitude.get(i));
//                Log.i("음력", "" + json2.luna.get(i));
//                Log.i("월출", "" + json3.moonRise.get(i));

                Fragment weatherfragment =  new WeatherFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_id, weatherfragment).commit();
//                getActivity().getSupportFragmentManager().beginTransaction().add(getActivity().getWindow().getDecorView().
//                        findViewById(android.R.id.content).getId(),fragment)
//                        .commit();
//                Intent myIntent = new Intent(getContext(), WeatherFragment.class);
//                myIntent.putExtra("lat", gPHP2.latitude.get(i));
//                myIntent.putExtra("long", gPHP2.longitude.get(i));
//                myIntent.putExtra("fact", gPHP2.townFact.get(i));
//                myIntent.putExtra("name", gPHP2.townName.get(i));
//                myIntent.putExtra("good", gPHP2.townGood.get(i));
//                myIntent.putExtra("bad", gPHP2.townBad.get(i));
//                startActivity(myIntent);
            }
        });
        return rootView;
    }

    public String getDateString()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String str_date = df.format(new Date());

        return str_date;
    }




    public String getHeight(String day) {
        int num = 0;
        for (int i = 0; i < dataList.size(); i++) {
            if (day.equals(dataList.get(i))) {
                num = i;
            }
        }
        return heightList.get(num);
    }

    public String getLuna(String day) {
        int num = 0;
        for (int i = 0; i < dataList.size(); i++) {
            if (day.equals(dataList.get(i))) {
                num = i;
            }
        }
        return lunaList.get(num);
    }




    public String getRise(String day) {
        int num = 0;
        for (int i = 0; i < dateList.size(); i++) {
            if (day.equals(dateList.get(i))) {
                num = i;
            }
        }
        return moonRiseList.get(num);
    }

    public String getIng(String day) {
        int num = 0;
        for (int i = 0; i < dateList.size(); i++) {
            if (day.equals(dateList.get(i))) {
                num = i;
            }
        }
        return moonIngList.get(num);
    }

    public String getSet(String day) {
        int num = 0;
        for (int i = 0; i < dateList.size(); i++) {
            if (day.equals(dateList.get(i))) {
                num = i;
            }
        }
        return moonSetList.get(num);
    }

    public String getsido(String sido) {
        int sidoNum = 0;
        for (int i = 0; i < sido_kr.size(); i++) {
            if (sido == sido_kr.get(i)) {
                sidoNum = i;
            }
        }
        return sido_en.get(sidoNum);
    }

    public String getlocation(String sido) {
        int locNum = 0;
        for (int i = 0; i < sido_kr.size(); i++) {
            if (sido == sido_kr.get(i)) {
                locNum = i;
            }
        }
        return location.get(locNum);
    }
}
