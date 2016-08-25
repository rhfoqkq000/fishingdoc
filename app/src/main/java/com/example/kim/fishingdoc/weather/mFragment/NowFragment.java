package com.example.kim.fishingdoc.weather.mFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kim.fishingdoc.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by kim on 2016-08-18.
 */
public class NowFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    public NowFragment(){

    }
    static Context context;
//    context = this.getContext();

    public Context getContxt(){
//        Log.i("Context모냥모냥",""+this.getContxt());
        return this.getContext();
    }
    public static NowFragment newInstance(int SectionNumber) throws ExecutionException, InterruptedException {
        NowFragment now = new NowFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", SectionNumber);
        now.setArguments(args);
        return now;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.now_fragment, container, false);
//        NowFragment.context = context.getApplicationContext();
        Log.i("rootview.getContext()떴냐",""+rootview.getContext());

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
//                },2000);
//            }
//        });
        return rootview;
    }

    public Context getContxxt(){
        Log.i("getContxxt떴냐제발좀",""+NowFragment.context);
        return NowFragment.context;
    }

}
