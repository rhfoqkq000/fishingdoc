package com.example.kim.fishingdoc.weather.mFragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.weather.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by kim on 2016-08-18.
 */
public class MonthFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Integer>> expandableListDetail;

    private Handler mHandler;
    private static ProgressDialog mProgressDialog;

    public MonthFragment(){

    }
    public static MonthFragment newInstance(int SectionNumber, ProgressDialog mProgressDialog2) {
        mProgressDialog = mProgressDialog2;

        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", SectionNumber);
        fragment.setArguments(args);


        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.month_fragment, container, false);

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
//                },2500);
//            }
//        });

        expandableListView = (ExpandableListView) rootview.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListAdapter.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " ListView Open.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " ListView Closed.",
                        Toast.LENGTH_SHORT).show();

            }
        });
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
            Log.i("없어졌냐?","1");
        }
        return rootview;
    }


}
