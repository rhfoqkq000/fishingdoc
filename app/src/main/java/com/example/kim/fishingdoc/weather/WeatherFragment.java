package com.example.kim.fishingdoc.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kim.fishingdoc.CalenderFragment;
import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.weather.mFragment.HourFragment;
import com.example.kim.fishingdoc.weather.mFragment.MonthFragment;
import com.example.kim.fishingdoc.weather.mFragment.NowFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by kim on 2016-08-17.
 */
public class WeatherFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static String day;

    public static String time;
    public static String tide_level;
    public static ArrayList<String> tide_detail;
    public static ArrayList<String> info;

    public static ArrayList<String> moonDateList;
    public static ArrayList<String> moonRiseList;
    public static ArrayList<String> moonIngList;
    public static ArrayList<String> moonSetList;

    public static ArrayList<String> tideDateList;
    public static ArrayList<String> tideLunaList;
    //    public static ArrayList<ArrayList<String>> tideHeightList;
    public static ArrayList<String> tideHeightFirst;
    public static ArrayList<String> tideHeightSecond;
    public static ArrayList<String> tideHeightThird;
    public static ArrayList<String> tideHeightFourth;

    public static ArrayList<String> hourList;
    public static ArrayList<String> dayList;
    public static ArrayList<String> tempList;
    public static ArrayList<String> wfkorList;
    public static ArrayList<String> popList;
    public static ArrayList<String> wsList;
    public static ArrayList<String> wdList;
    public static ArrayList<String> rehList;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {

            day = bundle.getString("day_go");

            time = bundle.getString("time_go");
            tide_level = bundle.getString("tide_level_go");
            tide_detail = bundle.getStringArrayList("tide_detail_go");
            info = bundle.getStringArrayList("info_go");

            moonDateList = bundle.getStringArrayList("moonDateList_go");
            moonRiseList = bundle.getStringArrayList("moonRiseList_go");
            moonIngList = bundle.getStringArrayList("moonIngList_go");
            moonSetList = bundle.getStringArrayList("moonSetList_go");

            tideDateList = bundle.getStringArrayList("tideDateList_go");
            tideLunaList = bundle.getStringArrayList("tideLunaList_go");
//            tideHeightList = bundle.getParcelableArrayList("tideHeightList_go");
            tideHeightFirst = bundle.getStringArrayList("tideHeightFirst_go");
            tideHeightSecond = bundle.getStringArrayList("tideHeightSecond_go");
            tideHeightThird = bundle.getStringArrayList("tideHeightThird_go");
            tideHeightFourth = bundle.getStringArrayList("tideHeightFourth_go");

            hourList = bundle.getStringArrayList("hourXml_go");
            dayList = bundle.getStringArrayList("dayXml_go");
            tempList = bundle.getStringArrayList("tempXml_go");
            wfkorList = bundle.getStringArrayList("wfkorXml_go");
            popList = bundle.getStringArrayList("popXml_go");
            wsList = bundle.getStringArrayList("wsXml_go");
            wdList = bundle.getStringArrayList("wdXml_go");
            rehList = bundle.getStringArrayList("rehXml_go");

            Log.e("moonRise", "" + moonRiseList);
            Log.e("time", "" + time);
            Log.e("rehList 습도", "" + rehList);
            Log.e("real info", "" + info);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weather_fragment, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) rootView.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        SimpleDateFormat msimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date currentTime = new Date();
        String now = msimpleDateFormat.format(currentTime);

        Button button = (Button) rootView.findViewById(R.id.bottoncalendar);
        button.setText(now);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new CalenderFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "Date Picker");
            }
        });
        return rootView;

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    try {
                        Fragment mon0 = NowFragment.newInstance(position + 1);
                        Bundle args0 = new Bundle();

                        args0.putString("day_go1", day);

                        args0.putString("rehXml_go1", rehList.get(0));
                        args0.putString("popXml_go1", popList.get(0));
                        args0.putString("wfkorXml_go1", wfkorList.get(0));

                        args0.putStringArrayList("tideHeightFirst_go1", tideHeightFirst);
                        args0.putStringArrayList("tideHeightSecond_go1", tideHeightSecond);
                        args0.putStringArrayList("tideHeightThird_go1", tideHeightThird);
                        args0.putStringArrayList("tideHeightFourth_go1", tideHeightFourth);

                        args0.putStringArrayList("moonDateList_go1", moonDateList);
                        args0.putStringArrayList("moonRiseList_go1", moonRiseList);
                        args0.putStringArrayList("moonIngList_go1", moonIngList);
                        args0.putStringArrayList("moonSetList_go1", moonSetList);
                        args0.putString("tempXml_go1", tempList.get(0));
                        args0.putString("wsXml_go1", wsList.get(0));
                        args0.putString("wdXml_go1", wdList.get(0));

                        if (time != null) {
                            args0.putString("time_go1", time);
                            args0.putString("tide_level_go1", tide_level);
                            args0.putStringArrayList("tide_detail_go1", tide_detail);
                            args0.putStringArrayList("info_go1", info);
                        }
//                        args0.putString("time_go1", null);


                        mon0.setArguments(args0);
                        return mon0;

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                case 1:
                    Fragment mon1 = HourFragment.newInstance(position + 2);
                    Bundle args1 = new Bundle();

                    args1.putStringArrayList("hourXml_go1", hourList);
                    args1.putStringArrayList("dayXml_go1", dayList);
                    args1.putStringArrayList("tempXml_go1", tempList);
                    args1.putStringArrayList("wfkorXml_go1", wfkorList);
                    args1.putStringArrayList("popXml_go1", popList);
                    args1.putStringArrayList("wsXml_go1", wsList);
                    args1.putStringArrayList("wdXml_go1", wdList);
                    args1.putStringArrayList("rehXml_go1", rehList);

                    mon1.setArguments(args1);
                    return mon1;
                case 2:
                    Fragment mon2 = MonthFragment.newInstance(position + 3);
                    Bundle args2 = new Bundle();

                    args2.putStringArrayList("moonDateList_go1", moonDateList);
                    args2.putStringArrayList("moonRiseList_go1", moonRiseList);
                    args2.putStringArrayList("moonIngList_go1", moonIngList);
                    args2.putStringArrayList("moonSetList_go1", moonSetList);
                    args2.putStringArrayList("tideDateList_go1", tideDateList);
                    args2.putStringArrayList("tideLunaList_go1", tideLunaList);
                    args2.putStringArrayList("tideHeightFirst_go1", tideHeightFirst);
                    args2.putStringArrayList("tideHeightSecond_go1", tideHeightSecond);
                    args2.putStringArrayList("tideHeightThird_go1", tideHeightThird);
                    args2.putStringArrayList("tideHeightFourth_go1", tideHeightFourth);
                    args2.putStringArrayList("hourXml_go1", hourList);
                    args2.putStringArrayList("dayrXml_go1", dayList);
                    args2.putStringArrayList("tempXml_go1", tempList);
                    args2.putStringArrayList("wfkorXml_go1", wfkorList);
                    args2.putStringArrayList("popXml_go1", popList);

                    mon2.setArguments(args2);
                    return mon2;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "현재";
                case 1:
                    return "시간별";
                case 2:
                    return "월별";
            }
            return null;
        }
    }
}
