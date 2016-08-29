package com.example.kim.fishingdoc.weather.mFragment;

import android.app.ProgressDialog;
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
    private static ProgressDialog mProgressDialog;
    static Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public NowFragment(){

    }

    public static NowFragment newInstance(int SectionNumber, ProgressDialog mProgressDialog2) throws ExecutionException, InterruptedException {
        mProgressDialog = mProgressDialog2;
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
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            Log.i("숨겼당","1");
        }
        return rootview;
    }


}
