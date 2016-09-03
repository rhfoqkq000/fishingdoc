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

    public static ArrayList<String> moonDateList;
    public static ArrayList<String> moonRiseList;
    public static ArrayList<String> moonIngList;
    public static ArrayList<String> moonSetList;

    public static ArrayList<String> tideDateList;
    public static ArrayList<String> tideLunaList;
    public static ArrayList<String> tideHeightFirst;
    public static ArrayList<String> tideHeightSecond;
    public static ArrayList<String> tideHeightThird;
    public static ArrayList<String> tideHeightFourth;

    public static ArrayList<String> hourList;
    public static ArrayList<String> dayList;
    public static ArrayList<String> tempList;
    public static ArrayList<String> wfkorList;
    public static ArrayList<String> popList;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Integer>> expandableListDetail;

    private Handler mHandler;
    private ProgressDialog mProgressDialog;

    public void onCreate(Bundle savedInstanceState) {
//        SingObj.getSingObj().setTest(9999);

        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            moonDateList = bundle.getStringArrayList("moonDateList_go1");
            moonRiseList = bundle.getStringArrayList("moonRiseList_go1");
            moonIngList = bundle.getStringArrayList("moonIngList_go1");
            moonSetList = bundle.getStringArrayList("moonSetList_go1");

            tideDateList = bundle.getStringArrayList("tideDateList_go1");
            tideLunaList = bundle.getStringArrayList("tideLunaList_go1");
            tideHeightFirst = bundle.getStringArrayList("tideHeightFirst_go1");
            tideHeightSecond = bundle.getStringArrayList("tideHeightSecond_go1");
            tideHeightThird = bundle.getStringArrayList("tideHeightThird_go1");
            tideHeightFourth = bundle.getStringArrayList("tideHeightFourth_go1");

            hourList = bundle.getStringArrayList("hourXml_go1");
            dayList = bundle.getStringArrayList("dayrXml_go1");
            tempList = bundle.getStringArrayList("tempXml_go1");
            wfkorList = bundle.getStringArrayList("wfkorXml_go1");
            popList = bundle.getStringArrayList("popXml_go1");

            Log.e("Main->month 받은 거 ", "" + moonRiseList);
        }

        Bundle args2 = new Bundle();
        args2.putStringArrayList("moonDateList_go1", moonDateList);

    }

    public MonthFragment(){

    }
    public static MonthFragment newInstance(int SectionNumber) {
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
//        });parent_date

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

        return rootview;
    }
}
