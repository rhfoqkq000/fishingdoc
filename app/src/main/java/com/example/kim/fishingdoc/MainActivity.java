package com.example.kim.fishingdoc;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.kim.fishingdoc.check.CheckFragment;
import com.example.kim.fishingdoc.fish.FishFragment;
import com.example.kim.fishingdoc.weather.Weather_List;

public class  MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        bottomNavigation = (AHBottomNavigation)findViewById(R.id.bottom_id);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
    }

    private void createNavItems(){
        //Create item
        AHBottomNavigationItem weatherItem = new AHBottomNavigationItem("날씨",R.drawable.menu_weather);
        AHBottomNavigationItem fishItem = new AHBottomNavigationItem("도감",R.drawable.menu_illu);
        AHBottomNavigationItem checkItem = new AHBottomNavigationItem("체크리스트",R.drawable.menu_check);


        //Add Them to bar
        bottomNavigation.addItem(weatherItem);
        bottomNavigation.addItem(fishItem);
        bottomNavigation.addItem(checkItem);

        //set properties
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
        bottomNavigation.setInactiveColor(Color.parseColor("#B2B2B2"));
        bottomNavigation.setAccentColor(Color.parseColor("#0084FF"));


        //set current item
        bottomNavigation.setCurrentItem(0);

    }

    @Override
    public void onTabSelected(int position, boolean wasSelected) {
        //show fragment
        if (position == 0) {
            Weather_List weatherList = new Weather_List();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, weatherList).commit();
//            WeatherFragment weatherFragment = new WeatherFragment();
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, weatherFragment).commit();
        } else if (position == 1) {
            FishFragment fishFragment = new FishFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, fishFragment).commit();
        } else if (position == 2) {
            CheckFragment checkFragment = new CheckFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, checkFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;
        if(0<=intervalTime && FINISH_INTERVAL_TIME >= intervalTime){
            super.onBackPressed();
        }else{
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
