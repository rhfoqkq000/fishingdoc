package com.example.kim.fishingdoc.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by kim on 2016-08-17.
 */
public class WeatherFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

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

        Button button = (Button)rootView.findViewById(R.id.bottoncalendar);
        button.setText(now);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new CalenderFragment();
                newFragment.show(getActivity().getSupportFragmentManager(),"Date Picker");
            }
        });
        return rootView;

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    try {
                        return NowFragment.newInstance(position + 1);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                case 1:
                    return HourFragment.newInstance(position + 2);
                case 2:
                    return MonthFragment.newInstance(position + 3);
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
