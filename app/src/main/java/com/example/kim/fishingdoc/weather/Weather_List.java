package com.example.kim.fishingdoc.weather;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kim.fishingdoc.MySQLiteOpenHelper;
import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.XmlParser1.FishXMLParser;
import com.example.kim.fishingdoc.retrofit.Moon_IN;
import com.example.kim.fishingdoc.retrofit.RealTime_IN;
import com.example.kim.fishingdoc.retrofit.Repo;
import com.example.kim.fishingdoc.retrofit.Tide_IN;
import com.example.kim.fishingdoc.retrofit.Wid_IN;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Ryu on 2016-08-19.
 */
public class Weather_List extends Fragment {

    private long mStartTime, mEndTime;
    private FishXMLParser mXMLParser;

    private Weather_ListView madapter;
    private ListView mlist;
    final String url = "http://45.32.61.201:3000/";

    String year; //전역
    String month; //전역
    String day; //전역
    String hour; //전역
    String min; //전역
    String sec; //전역

    HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();



    ArrayList<String> sido_kr = new ArrayList<String>(); //전역
    ArrayList<String> sido_en = new ArrayList<String>(); //전역
    ArrayList<String> location = new ArrayList<String>(); //전역

    SQLiteDatabase db; //전역
    MySQLiteOpenHelper helper; //전역

    String krEncode;

    //oncreate rootview /바깥 getactivity
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.e("onCreateView", "" + "");

        View rootView = inflater.inflate(R.layout.weather_list, container, false);

        final String[] time = new String[1];
        final String[] tide_level = new String[1];
        final ArrayList<String> tide_detail = new ArrayList<String>();
        final ArrayList<String> info = new ArrayList<String>();

        final ArrayList<String> moonDateList = new ArrayList<String>();
        final ArrayList<String> moonRiseList = new ArrayList<String>();
        final ArrayList<String> moonIngList = new ArrayList<String>();
        final ArrayList<String> moonSetList = new ArrayList<String>();

        final ArrayList<String> tideDateList = new ArrayList<String>();
        final ArrayList<String> tideLunaList = new ArrayList<String>();
        final ArrayList<ArrayList<String>> tideHeightList = new ArrayList<ArrayList<String>>();

        final ArrayList<String> tideHeightFirst = new ArrayList<String>();
        final ArrayList<String> tideHeightSecond = new ArrayList<String>();
        final ArrayList<String> tideHeightThird = new ArrayList<String>();
        final ArrayList<String> tideHeightFourth = new ArrayList<String>();

        final ArrayList<String> hourXml = new ArrayList<String>();
        final ArrayList<String> dayXml = new ArrayList<String>();
        final ArrayList<String> tempXml = new ArrayList<String>();
        final ArrayList<String> wfkorXml = new ArrayList<String>();
        final ArrayList<String> popXml = new ArrayList<String>();
        final ArrayList<String> wsXml = new ArrayList<String>();
        final ArrayList<String> wdXml = new ArrayList<String>();
        final ArrayList<String> rehXml = new ArrayList<String>();

        String date = getDateString();
        String date2 = date.split(" ")[0];
        String date3 = date.split(" ")[1];
        year = date2.split("-")[0];
        month = date2.split("-")[1];
        day = date2.split("-")[2];
        hour = date3.split(":")[0];
        min = date3.split(":")[1];
        sec = date3.split(":")[2];

        Cursor cursor;
        helper = new MySQLiteOpenHelper(getContext());
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM testT order by sido_kr", null);
        Log.i("while 전 ", "" + cursor.toString());
        while (cursor.moveToNext()) {
            Log.e("while 안 ", "" + cursor.getString(1) + "/" + cursor.getString(2) + "/" + cursor.getString(5) + "/" + cursor.getString(6) + "/" + cursor.getString(7));
            sido_kr.add(cursor.getString(1));
            sido_en.add(cursor.getString(2));
            location.add(cursor.getString(5));
        }
        cursor.close();
        db.close();

        madapter = new Weather_ListView();
        mlist = (ListView) rootView.findViewById(R.id.listView);

        mlist.setAdapter(madapter);
        for (int i = 0; i < sido_kr.size(); i++) {
            madapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.wave1), sido_kr.get(i));
//            madapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.wave1), hash.get("sido_kr").get(i));
//            hash.get("sido_kr").size()
        }

        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Weather_ListData mData = madapter.mlistDataWeather.get(i);
                final String kr = mData.mtext;
                Log.e("누른거 : ", "" + kr);
                try {
                    krEncode = URLEncoder.encode(kr, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String en = getsido(mData.mtext).toString();
                Log.e("누른거 : ", "" + en);
                final String loc = getlocation(mData.mtext).toString();

//                String locEncode = null;
//                String krEncode = null;
//
//                try {
//                    locEncode = URLEncoder.encode(loc, "UTF-8");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


//                mStartTime = System.currentTimeMillis();
//                //http://45.32.61.201:3000/weather/hour?sido_name=%EB%B0%B1%EB%A0%B9%EB%8F%84
//                mXMLParser = new FishXMLParser(url + "weather/hour?sido_name=" + krEncode, mHandler);
//                Thread thread = new Thread(mXMLParser);
//                thread.start();


                Retrofit client = new Retrofit.Builder().baseUrl("http://45.32.61.201:3000/")
                        .addConverterFactory(GsonConverterFactory.create()).build();

                RealTime_IN real_service = client.create(RealTime_IN.class);
                final Tide_IN tide_service = client.create(Tide_IN.class);
                final Moon_IN moon_service = client.create(Moon_IN.class);
                final Wid_IN wid_service = client.create(Wid_IN.class);


                final Call<Repo> realtime_call = real_service.repo(String.valueOf(kr));

                realtime_call.enqueue(new Callback<Repo>() {
                    @Override
                    public void onResponse(Call<Repo> call, Response<Repo> response) {
                        if (response.isSuccessful()) {
                            Repo repo = response.body();
                            if (repo.getJowi_result().equals("ok")) {
                                for (int i = 0; i < repo.getSection_gen().getInfo().size(); i++) {
                                    info.add(i, repo.getSection_gen().getInfo().get(i));
                                }
                                for (int i = 0; i < repo.getSection_jowi().getJowi_detail().size(); i++) {
                                    tide_detail.add(i, repo.getSection_jowi().getJowi_detail().get(i));
                                }
                                tide_level[0] = repo.getSection_jowi().getJowi();
                                time[0] = repo.getSection_jowi().getTime();

                            } else {
                                time[0] = null;
                                Log.i("실시간 안되는 지역 ", "" + time);
                            }
                            Call<Repo> moon_call = moon_service.repo(String.valueOf(loc), String.valueOf(year), String.valueOf(month));

                            moon_call.enqueue(new Callback<Repo>() {
                                @Override
                                public void onResponse(Call<Repo> call, Response<Repo> response) {
                                    if (response.isSuccessful()) {
                                        Repo repo = response.body();
                                        for (int i = 0; i < repo.getMoon_body().size(); i++) {
                                            moonDateList.add(i, repo.getMoon_body().get(i).getDate());
                                            moonRiseList.add(i, repo.getMoon_body().get(i).getMoonRise());
                                            moonIngList.add(i, repo.getMoon_body().get(i).getMoonIng());
                                            moonSetList.add(i, repo.getMoon_body().get(i).getMoonSet());
                                        }
                                        Log.e("Moon List ", "" + moonDateList + "/" + moonSetList);
                                        Log.e("realtime", "" + repo.getMoon_body().get(1).getMoonIng());
                                        Log.e("2", "2");
                                        Call<Repo> tide_call = tide_service.repo(String.valueOf(en), String.valueOf(year + month));
                                        tide_call.enqueue(new Callback<Repo>() {
                                            @Override
                                            public void onResponse(Call<Repo> call, Response<Repo> response) {
                                                if (response.isSuccessful()) {
                                                    Repo repo = response.body();
                                                    for (int i = 1; i < repo.getTide_body().size(); i++) {
                                                        tideDateList.add(i - 1, repo.getTide_body().get(i).getDate());
                                                        tideLunaList.add(i - 1, repo.getTide_body().get(i).getLuna());
                                                        tideHeightList.add(i - 1, repo.getTide_body().get(i).getHeight());
                                                    }
                                                    for (int i=0; i<tideHeightList.size(); i++){
                                                        tideHeightFirst.add(i,tideHeightList.get(i).get(0));
                                                        tideHeightSecond.add(i,tideHeightList.get(i).get(1));
                                                        tideHeightThird.add(i,tideHeightList.get(i).get(2));
                                                        if (tideHeightList.get(i).size()==4){
                                                            tideHeightFourth.add(i,tideHeightList.get(i).get(3));
                                                        }else{
                                                            tideHeightFourth.add(i,null);
                                                        }
                                                    }
                                                    Log.e("Tide List ", "" + tideDateList + "/" + tideLunaList);
                                                    Log.e("tideHeightList", "" + tideHeightList);
                                                    Log.e("3", "3");

                                                    Call<Repo> wid_call = wid_service.repo(String.valueOf(kr));
                                                    wid_call.enqueue(new Callback<Repo>() {
                                                        @Override
                                                        public void onResponse(Call<Repo> call, Response<Repo> response) {
                                                            if (response.isSuccessful()) {
                                                                Repo repo = response.body();
                                                                for (int i = 0; i < repo.getWid().getBody().getData().size(); i++) {
                                                                    double ws = Double.parseDouble(repo.getWid().getBody().getData().get(i).getWs());
                                                                    double ws2 = Double.parseDouble(String.format("%.2f", ws));

                                                                    hourXml.add(i, repo.getWid().getBody().getData().get(i).getHour());
                                                                    dayXml.add(i, repo.getWid().getBody().getData().get(i).getDay());
                                                                    tempXml.add(i, repo.getWid().getBody().getData().get(i).getTemp());
                                                                    popXml.add(i, repo.getWid().getBody().getData().get(i).getPop());
                                                                    wfkorXml.add(i, repo.getWid().getBody().getData().get(i).getWfKor());
                                                                    wsXml.add(i, String.valueOf(ws2));
                                                                    wdXml.add(i, repo.getWid().getBody().getData().get(i).getWdKor());
                                                                    rehXml.add(i, repo.getWid().getBody().getData().get(i).getReh());
//                                                                    tideLunaList.add(i, repo.getTide_body().get(i).getLuna());
//                                                                    tideHeightList.add(i, repo.getTide_body().get(i).getHeight());
                                                                }
//                                                                for (int i = 0; i < tideHeightList.size(); i++) {
//                                                                    tideHeightFirst.add(i, tideHeightList.get(i).get(0));
//                                                                    tideHeightSecond.add(i, tideHeightList.get(i).get(1));
//                                                                    tideHeightThird.add(i, tideHeightList.get(i).get(2));
//                                                                    if (tideHeightList.get(i).size() == 4) {
//                                                                        tideHeightFourth.add(i, tideHeightList.get(i).get(3));
//                                                                    } else {
//                                                                        tideHeightFourth.add(i, null);
//                                                                    }
//                                                                }
                                                                Log.e("4", "4");

                                                            } else {
                                                            }
                                                            SingObj.getSingObj().setMoonDateList(moonDateList);
                                                            SingObj.getSingObj().setMoonRiseList(moonRiseList);
                                                            SingObj.getSingObj().setMoonIngList(moonIngList);
                                                            SingObj.getSingObj().setMoonSetList(moonSetList);

                                                            SingObj.getSingObj().setTideDateList(tideDateList);
                                                            SingObj.getSingObj().setTideLunaList(tideLunaList);
                                                            SingObj.getSingObj().setTideHeightFirst(tideHeightFirst);
                                                            SingObj.getSingObj().setTideHeightSecond(tideHeightSecond);
                                                            SingObj.getSingObj().setTideHeightThird(tideHeightThird);
                                                            SingObj.getSingObj().setTideHeightFourth(tideHeightFourth);

                                                            SingObj.getSingObj().setHourList(hourXml);
                                                            SingObj.getSingObj().setDayList(dayXml);
                                                            SingObj.getSingObj().setTempList(tempXml);
                                                            SingObj.getSingObj().setWfkorList(wfkorXml);
                                                            SingObj.getSingObj().setPopList(popXml);

                                                            SingObj.getSingObj().setToday(day);

                                                            Fragment weatherfragment = new WeatherFragment();
                                                            Bundle args = new Bundle();
                                                            args.putString("day_go", day);

                                                            args.putString("time_go", time[0]);
                                                            args.putString("tide_level_go", tide_level[0]);
                                                            args.putStringArrayList("tide_detail_go", tide_detail);
                                                            args.putStringArrayList("info_go", info);

                                                            args.putStringArrayList("moonDateList_go", moonDateList);
                                                            args.putStringArrayList("moonRiseList_go", moonRiseList);
                                                            args.putStringArrayList("moonIngList_go", moonIngList);
                                                            args.putStringArrayList("moonSetList_go", moonSetList);

                                                            args.putStringArrayList("tideDateList_go", tideDateList);
                                                            args.putStringArrayList("tideLunaList_go", tideLunaList);

                                                            args.putStringArrayList("tideHeightFirst_go", tideHeightFirst);
                                                            args.putStringArrayList("tideHeightSecond_go", tideHeightSecond);
                                                            args.putStringArrayList("tideHeightThird_go", tideHeightThird);
                                                            args.putStringArrayList("tideHeightFourth_go", tideHeightFourth);

                                                            args.putStringArrayList("hourXml_go", hourXml);
                                                            args.putStringArrayList("dayXml_go", dayXml);
                                                            args.putStringArrayList("tempXml_go", tempXml);
                                                            args.putStringArrayList("wfkorXml_go", wfkorXml);
                                                            args.putStringArrayList("popXml_go", popXml);
                                                            args.putStringArrayList("wsXml_go", wsXml);
                                                            args.putStringArrayList("wdXml_go", wdXml);
                                                            args.putStringArrayList("rehXml_go", rehXml);
                                                            weatherfragment.setArguments(args);
                                                            Log.i("List에서 넘기기", " " + args);
                                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_id, weatherfragment).commit();

                                                        }

                                                        @Override
                                                        public void onFailure(Call<Repo> call, Throwable t) {

                                                        }
                                                    });

                                                } else {
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Repo> call, Throwable t) {

                                            }

                                        });
                                    } else {
                                    }
                                }

                                @Override
                                public void onFailure(Call<Repo> call, Throwable t) {

                                }

                            });
                        } else {
                        }

                    }

                    @Override
                    public void onFailure(Call<Repo> call, Throwable t) {

                    }

                });


//
////                Handler handler = new Handler();
////                new Handler().postDelayed(new Runnable() {// 1 초 후에 실행
////                    @Override
////                    public void run() {
//                        Fragment weatherfragment = new WeatherFragment();
//                        Bundle args = new Bundle();
//                        args.putStringArray("time_go", time);
//                        args.putStringArray("tide_level_go", tide_level);
//                        args.putStringArrayList("tide_detail_go", tide_detail);
//                        args.putStringArrayList("info_go", info);
//
//                        args.putStringArrayList("moonDateList_go", moonDateList);
//                        args.putStringArrayList("moonRiseList_go", moonRiseList);
//                        args.putStringArrayList("moonIngList_go", moonIngList);
//                        args.putStringArrayList("moonSetList_go", moonSetList);
//
//                        args.putStringArrayList("tideDateList_go", tideDateList);
//                        args.putStringArrayList("tideLunaList_go", tideLunaList);
//                args.putParcelableArrayList("tideHeightList_go", (ArrayList<? extends Parcelable>) tideHeightList);
//
//                        args.putStringArrayList("hourXml_go", hourXml);
//                        args.putStringArrayList("dayXml_go", dayXml);
//                        args.putStringArrayList("tempXml_go", tempXml);
//                        args.putStringArrayList("wfkorXml_go", wfkorXml);
//                        args.putStringArrayList("popXml_go", popXml);
//                        args.putStringArrayList("wsXml_go", wsXml);
//                        args.putStringArrayList("wdXml_go", wdXml);
//                        args.putStringArrayList("rehXml_go", rehXml);
//
//                        weatherfragment.setArguments(args);
//                        Log.i("List에서 넘기기", " " + args);
//                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_id, weatherfragment).commit();
//
//                        Log.e("시간2 : ", "" + (realEndTime - realStartTime)/1000L);
//                        Log.e("시간2-1 : ", "" + (tideEndTime - tideStartTime));
//                        Log.e("시간2-2 : ", "" + (moonEndTime - moonStartTime) / 1000L);
//                        Log.e("시간! : ", "" + tideStartTime);
//                        Log.e("시간! : ", "" + tideEndTime);
//                    }
//                }, 2000);


            }
        });
        return rootView;
    }

    public String getDateString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String str_date = df.format(new java.util.Date());

        return str_date;
    }

    //
//    public String getHeight(String day) {
//        int num = 0;
//        for (int i = 0; i < dataList.size(); i++) {
//            if (day.equals(dataList.get(i))) {
//                num = i;
//            }
//        }
//        return heightList.get(num);
//    }
//
//    public String getLuna(String day) {
//        int num = 0;
//        for (int i = 0; i < dataList.size(); i++) {
//            if (day.equals(dataList.get(i))) {
//                num = i;
//            }
//        }
//        return lunaList.get(num);
//    }
//
//    public String getRise(String day) {
//        int num = 0;
//        Log.i("허미쉽팔0", "" + dataList);
//        for (int i = 0; i < dataList.size(); i++) {
//            if (day.equals(dataList.get(i))) {
//                num = i;
//            }
//        }
//        Log.i("허미쉽팔", "" + moonRiseList.get(num));
//        return moonRiseList.get(num);
//    }
//
//    public String getIng(String day) {
//        int num = 0;
//        for (int i = 0; i < dataList.size(); i++) {
//            if (day.equals(dataList.get(i))) {
//                num = i;
//            }
//        }
//        return moonIngList.get(num);
//    }
//
//    public String getSet(String day) {
//        int num = 0;
//        for (int i = 0; i < dataList.size(); i++) {
//            if (day.equals(dataList.get(i))) {
//                num = i;
//            }
//        }
//        return moonSetList.get(num);
//    }
//
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

//    Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            mEndTime = System.currentTimeMillis();
//            Log.d("Taken Time", Long.toString((mEndTime - mStartTime) / 1000L));
//
//            ArrayList<FishDatas> dataList = mXMLParser.getResult();
//            int dataListSize = dataList.size();
//            Log.d("Data List Size", Integer.toString(dataListSize));
//
//            for (int i = 0; i < dataListSize; i++) {
//                Double ws = Double.parseDouble(dataList.get(i).getWs());
//                double ws2 = Double.parseDouble(String.format("%.2f", ws));
//                hourXml.add(i, dataList.get(i).getHour());
//                dayXml.add(i, dataList.get(i).getDay());
//                tempXml.add(i, dataList.get(i).getTemp());
//                wfkorXml.add(i, dataList.get(i).getWfkor());
//                popXml.add(i, dataList.get(i).getPop());
//                wsXml.add(i, String.valueOf(ws2));
//                wdXml.add(i, dataList.get(i).getWd());
//                rehXml.add(i, dataList.get(i).getReh());
//                //               Log.d("XML Parsing Result", "Result" + dataList.get(i).getHour() + dataList.get(i).getWfkor());
//            }
//            Log.d("xml array에 넣기 됐나? ", "" + hourXml + "/" + dayXml);
//            Log.d("xml array에 넣기 됐나? ", "" + tempXml + "/" + wfkorXml);
//            Log.d("xml array에 넣기 됐나? ", "" + popXml + "/" + wsXml);
//            Log.d("xml array에 넣기 됐나? ", "" + wdXml + "/" + rehXml);
//        }
//    };

}
