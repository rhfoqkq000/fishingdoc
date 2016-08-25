package com.example.kim.fishingdoc.weather.mFragment;

import android.os.AsyncTask;
import android.os.Bundle;

/**
 * Created by user on 2016-07-15.
 */
class Loading extends AsyncTask<String, String, NowFragment> {

    int SectionNumber;
//    Context context;

    public Loading(int SectionNumber){
        this.SectionNumber = SectionNumber;
//        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected NowFragment doInBackground(String... strings) {

        NowFragment fragment = new NowFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", SectionNumber);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    protected void onPostExecute(NowFragment nowFragment) {

    }

}