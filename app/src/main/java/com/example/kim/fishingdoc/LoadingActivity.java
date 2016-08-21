package com.example.kim.fishingdoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by kim on 2016-08-21.
 */
public class LoadingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 1800);
    }
    private class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class));
            LoadingActivity.this.finish();
        }
    }
}
