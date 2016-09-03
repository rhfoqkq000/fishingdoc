package com.example.kim.fishingdoc.weather;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.kim.fishingdoc.retrofit.Moon_IN;
import com.example.kim.fishingdoc.retrofit.RealTime_IN;
import com.example.kim.fishingdoc.retrofit.Repo;
import com.example.kim.fishingdoc.retrofit.Tide_IN;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Ryu on 2016-08-31.
 */
public class Args extends AsyncTask<String, String, Bundle> {

    Fragment weatherfragment;
    Bundle args;

    @Override
    protected Bundle doInBackground(String... strings) {

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

        Retrofit client = new Retrofit.Builder().baseUrl("http://45.32.61.201:3000/").addConverterFactory(GsonConverterFactory.create()).build();

        RealTime_IN real_service = client.create(RealTime_IN.class);
        Tide_IN tide_service = client.create(Tide_IN.class);
        Moon_IN moon_service = client.create(Moon_IN.class);


        Call<Repo> realtime_call = real_service.repo(String.valueOf(strings[0])); //kr
        Call<Repo> moon_call = moon_service.repo(String.valueOf(strings[2]), String.valueOf(strings[3]), String.valueOf(strings[4])); //loc year month
        Call<Repo> tide_call = tide_service.repo(String.valueOf(strings[1]), String.valueOf(strings[3]+strings[4])); //en year+month

        args = new Bundle();

//                args.putParcelableArrayList("tideHeightList_go", (ArrayList<? extends Parcelable>) tideHeightList);


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

                        args.putStringArray("time_go", time);
                        args.putStringArray("tide_level_go", tide_level);
                        args.putStringArrayList("tide_detail_go", tide_detail);
                        args.putStringArrayList("info_go", info);

                        Log.e("RealTime List ", "" + info + "/" + tide_level[0]);
                        Log.e("realtime info ", "" + repo.getSection_gen().getInfo().get(1));
                        Log.e("1", "1");
                    } else {
                        time[0] = null;
                        Log.i("실시간 안되는 지역 ", "" + time);
                    }
                } else {
                }

            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {

            }

        });


        moon_call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                if (response.isSuccessful()) {
                    Repo repo = response.body();
                    for (int i = 0; i < repo.getMoon_body().size(); i++) {
                        moonDateList.add(i, repo.getMoon_body().get(i).getDate());
                        moonRiseList.add(i, repo.getMoon_body().get(i).getMoonRise());
                        moonIngList.add(i, repo.getMoon_body().get(i).getMoonRise());
                        moonSetList.add(i, repo.getMoon_body().get(i).getMoonRise());
                    }
                    args.putStringArrayList("moonDateList_go", moonDateList);
                    args.putStringArrayList("moonRiseList_go", moonRiseList);
                    args.putStringArrayList("moonIngList_go", moonIngList);
                    args.putStringArrayList("moonSetList_go", moonSetList);

                    Log.e("Moon List ", "" + moonDateList + "/" + moonSetList);
                    Log.e("realtime", "" + repo.getMoon_body().get(1).getMoonIng());
                    Log.e("2", "2");
                } else {
                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {

            }

        });

        tide_call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                if (response.isSuccessful()) {
                    Repo repo = response.body();
                    for (int i = 1; i < repo.getTide_body().size() - 1; i++) {
                        tideDateList.add(i - 1, repo.getTide_body().get(i).getDate());
                        tideLunaList.add(i - 1, repo.getTide_body().get(i).getLuna());
                        tideHeightList.add(i - 1, repo.getTide_body().get(i).getHeight());
                    }
                    args.putStringArrayList("tideDateList_go", tideDateList);
                    args.putStringArrayList("tideLunaList_go", tideLunaList);

                    Log.e("Tide List ", "" + tideDateList + "/" + tideLunaList);
                    Log.e("tideHeightList", "" + tideHeightList);
                    Log.e("3", "3");
                } else {
                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {

            }
        });
Log.i("args",""+args);

        return args;
    }

    @Override
    protected void onPostExecute(Bundle result) {
//        weatherfragment = new WeatherFragment();
//        weatherfragment.setArguments(result);
//        Log.i("AsyncTask에서 넘기기", " " + result);
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_id, weatherfragment).commit();


    }
}
