package com.example.kim.fishingdoc.weather.mFragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kim.fishingdoc.R;

/**
 * Created by kim on 2016-08-18.
 */
public class NowFragment extends Fragment {

    private Handler mHandler;
    private ProgressDialog mProgressDialog;

    public NowFragment(){

    }
    public static NowFragment newInstance(int SectionNumber) {
        NowFragment fragment = new NowFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", SectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.now_fragment, container, false);

        mHandler = new Handler();
        getActivity().runOnUiThread(new Runnable(){

            @Override
            public void run() {
                mProgressDialog = ProgressDialog.show(getActivity(),"","잠시만 기다려 주세요.", true);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            if(mProgressDialog!=null&&mProgressDialog.isShowing()){
                                mProgressDialog.dismiss();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },2000);
            }
        });
        return rootview;
    }
}
