package com.example.kim.fishingdoc.weather.mFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kim.fishingdoc.R;
import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.CurveGraphView;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraph;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraphVO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by kim on 2016-08-18.
 */
public class NowFragment extends Fragment {

    private RelativeLayout layoutGraphView;

    String[] legendArr;
    int[] graph3;

    public String moonRise;
    public String moonIng;
    public String moonSet;

    public int moonRiseInt;
    public int moonIngInt;
    public int moonSetInt;

//    public String height;
//
//    public String time;
//    public String tide_level;
//    public String tide_detail;
//    public String info;
//
    public String first;
    public String second;
    public String third;
    public String fourth;

    public String first_time;
    public String second_time;
    public String third_time;
    public String fourth_time;

    //------------
    public String temp;
    public String waterTem;
    public String salt;
    public String atmosPressure;
    public String wind;
    public String ws;
    public String wd;

    public String tide_level;
    //    public ArrayList<String> tide_detail;
    public ArrayList<String> tide_info;

    public int day;

    public String reh;
    public String pop;
    public String wfkor;

    public ArrayList<String> tideHeightFirst;
    public ArrayList<String> tideHeightSecond;
    public ArrayList<String> tideHeightThird;
    public ArrayList<String> tideHeightFourth;

    public ArrayList<String> moonDateList;
    public ArrayList<String> moonRiseList;
    public ArrayList<String> moonIngList;
    public ArrayList<String> moonSetList;

//    private TextView temTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        for (int i=0; i<SingObj.getSingObj().getToday().length(); i++){
//            Log.e("모냐능?",""+ SingObj.getSingObj().getToday());
//        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("time_go1") != null) {
                tide_level = bundle.getString("tide_level_go1").replace(" ", "");
//                tide_detail = bundle.getStringArrayList("tide_detail_go1");
                tide_info = bundle.getStringArrayList("info_go1");
            }
            day = Integer.parseInt(bundle.getString("day_go1"));
            temp = bundle.getString("tempXml_go1") + " ºC";
            ws = bundle.getString("wsXml_go1") +" (m/s)";
            wd = bundle.getString("wdXml_go1");
            reh = bundle.getString("rehXml_go1") + " %";
            pop = bundle.getString("popXml_go1") + " %";
            wfkor = bundle.getString("wfkorXml_go1");

            tideHeightFirst = bundle.getStringArrayList("tideHeightFirst_go1");
            tideHeightSecond = bundle.getStringArrayList("tideHeightSecond_go1");
            tideHeightThird = bundle.getStringArrayList("tideHeightThird_go1");
            tideHeightFourth = bundle.getStringArrayList("tideHeightFourth_go1");
            Log.e("tideHeightFirst", "" + tideHeightFirst);
            Log.e("tideHeightFourth", "" + tideHeightFourth);

            moonDateList = bundle.getStringArrayList("moonDateList_go1");
            moonRiseList = bundle.getStringArrayList("moonRiseList_go1");
            moonIngList = bundle.getStringArrayList("moonIngList_go1");
            moonSetList = bundle.getStringArrayList("moonSetList_go1");
            Log.e("moonsetList",""+moonSetList.get(1));

            Log.e("Main->now 받은 거 ", "" + temp + "/" + tide_info + "/" + wfkor);

            if (tide_info != null) {
                waterTem = tide_info.get(0).split(",")[1];
                if (waterTem.equals("미설치")) {
                    waterTem = "정보없음";
                } else {
                    waterTem = waterTem + " ºC";
                }
                salt = tide_info.get(1).split(",")[1];
                if (salt.equals("미설치")) {
                    salt = "정보없음";
                } else {
                    salt = salt + " (PSU)";
                }
                temp = tide_info.get(2).split(",")[1];
                if (temp.equals("미설치")) {
                    temp = bundle.getString("tempXml_go1");
                } else {
                    temp = temp + " ºC";
                }
                atmosPressure = tide_info.get(3).split(",")[1];
                if (atmosPressure.equals("미설치")) {
                    atmosPressure = "정보없음";
                } else {
                    atmosPressure = atmosPressure + " (hPa)";
                }
                wind = tide_info.get(4).split(",")[1];
                if (wind.equals("미설치")) {
                    wd = bundle.getString("wdXml_go1");
                    ws = bundle.getString("wsXml_go1"+ " (m/s)");
                } else {
                    String replace = wind.replace(" ", ",");
                    wd = replace.split(",")[0];
                    ws = replace.split(",")[1] + " (m/s)";
                }

            } else {
                waterTem = "정보없음";
                salt = "정보없음";
                atmosPressure = "정보없음";
                tide_level = "정보없음";
            }
            Log.e("temp/wd/ws/watertem", "" + temp + wd + ws + waterTem + tide_level);


//                String replace5 = wind.replace(" ",",");

//            String replace3 = height.replace("[","");
//            String replace4 = replace3.replace("]","");
//            List<String> height_list = new ArrayList<String>(Arrays.asList(replace4.split(",")));
//            Log.e("height list ",""+height_list);
//
            first = tideHeightFirst.get(day - 1);
            first = first.split("\\(")[1];
            first = first.split("\\)")[0];
            first = first.replaceAll("\\p{Space}", "");
            first_time = tideHeightFirst.get(day - 1).split("\\(")[0];
            second = tideHeightSecond.get(day - 1);
            second = second.split("\\(")[1];
            second = second.split("\\)")[0];
            second = second.replaceAll("\\p{Space}", "");
            second_time = tideHeightSecond.get(day - 1).split("\\(")[0];
            third = tideHeightThird.get(day - 1);
            third = third.split("\\(")[1];
            third = third.split("\\)")[0];
            third = third.replaceAll("\\p{Space}", "");
            third_time = tideHeightThird.get(day - 1).split("\\(")[0];
            if (tideHeightFourth.get(day - 1) != null) {
                fourth = tideHeightFourth.get(day - 1);
                fourth = fourth.split("\\(")[1];
                fourth = fourth.split("\\)")[0];
                fourth = fourth.replaceAll("\\p{Space}", "");
                fourth_time = tideHeightFourth.get(day - 1).split("\\(")[0];
            }
            Log.e("오늘 조위 ", "" + tideHeightFirst.get(day - 1) + "/" + tideHeightFourth.get(day - 1));

            moonRise = moonRiseList.get(day-1);
            moonIng = moonIngList.get(day-1);
            moonSet = moonSetList.get(day - 1);
            moonRiseInt = Integer.parseInt(moonRise.split(":")[0]) + 1;
            moonIngInt = Integer.parseInt(moonIng.split(":")[0]) + 2;
            moonSetInt = Integer.parseInt(moonSet.split(":")[0])+1;
//            String text = String.format("%04d%02d%02d", 2010, 11, 2);
//            Log.e("text",""+text);

//            Log.i("info ",""+waterTem+" / "+salt+" / "+temperature+" / "+atmosPressure+" / "+ windDirection +" / "+windSpeed);
//            Log.i("height ",""+first+" / "+first_time+" / "+second+" / "+third_time +" / "+fourth+" / "+fourth_time);
        }
    }

    public NowFragment() {

    }

    static Context context;
//    context = this.getContext();

    public Context getContxt() {
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
        Log.i("rootview.getContext()떴냐", "" + rootview.getContext());

        TextView text1 = (TextView) rootview.findViewById(R.id.text1);
        text1.setText(moonRise+" ~ "+String.format("%02d", moonRiseInt)+":"+moonRise.split(":")[1] + "  낚시하기 안 좋은 때");
        TextView text2 = (TextView) rootview.findViewById(R.id.text2);
        text2.setText(moonIng+" ~ "+String.format("%02d", moonIngInt)+":"+moonIng.split(":")[1]+"  낚시하기 좋은 때");
        TextView text3 = (TextView) rootview.findViewById(R.id.text3);
        text3.setText(moonSet +" ~ " +String.format("%02d", moonSetInt)+":"+moonSet.split(":")[1] +"  낚시하기 안 좋은 때");

        TextView temTV = (TextView) rootview.findViewById(R.id.now_tem_content);
        temTV.setText(temp);
        TextView watertemTV = (TextView) rootview.findViewById(R.id.now_water_tem_content);
        watertemTV.setText(waterTem);
        TextView windDTV = (TextView) rootview.findViewById(R.id.now_airflow_content);
        windDTV.setText(wd);
        TextView windSTV = (TextView) rootview.findViewById(R.id.now_wind_content);
        windSTV.setText(ws);
        TextView saltTV = (TextView) rootview.findViewById(R.id.now_salt_content);
        saltTV.setText(salt);
        TextView atmosPressTV = (TextView) rootview.findViewById(R.id.now_airpress_content);
        atmosPressTV.setText(atmosPressure);
        TextView popTV = (TextView) rootview.findViewById(R.id.now_fargo_content);
        popTV.setText(pop);
        TextView rehTV = (TextView) rootview.findViewById(R.id.now_hu_content);
        rehTV.setText(reh);

        TextView wfkorTV = (TextView) rootview.findViewById(R.id.now_weather_text);
        wfkorTV.setText(wfkor);

        ImageView weather = (ImageView) rootview.findViewById(R.id.now_weather);
        if (wfkor.equals("맑음")) {
            weather.setImageResource(R.drawable.sunny);
        } else if (wfkor.equals("구름 조금")) {
            weather.setImageResource(R.drawable.cloudy1);
        } else if (wfkor.equals("구름 많음")) {
            weather.setImageResource(R.drawable.cloudy2);
        } else if (wfkor.equals("흐림")) {
            weather.setImageResource(R.drawable.cloudy3);
        } else if (wfkor.equals("비")) {
            weather.setImageResource(R.drawable.rainy);
        } else if (wfkor.equals("눈/비")) {
            weather.setImageResource(R.drawable.rainsnow);
        } else if (wfkor.equals("눈")) {
            weather.setImageResource(R.drawable.snowy);
        }

        layoutGraphView = (RelativeLayout) rootview.findViewById(R.id.layoutGraphView);
        layoutGraphView.setEnabled(false);
        layoutGraphView.setFocusable(false);
        layoutGraphView.setClickable(false);

        setCurveGraph();

        return rootview;
    }

    private void setCurveGraph() {
        //all setting
        CurveGraphVO vo = makeCurveGraphAllSetting();

        //default setting
//		CurveGraphVO vo = makeCurveGraphDefaultSetting();

        layoutGraphView.addView(new CurveGraphView(getContext(), vo));
    }

    /**
     * make simple Curve graph
     *
     * @return
     */
//    private CurveGraphVO makeCurveGraphDefaultSetting() {
//
//        if (tideHeightFourth.get(day - 1) != null) {
//            legendArr = new String[]{first_time, second_time, third_time, fourth_time};
//            graph3 = new int[]{Integer.parseInt(first), Integer.parseInt(second), Integer.parseInt(third), Integer.parseInt(fourth)};
//        } else {
//            legendArr = new String[]{first_time, second_time, third_time};
//            graph3 = new int[]{Integer.parseInt(first), Integer.parseInt(second), Integer.parseInt(third)};
//        }
//
//
//        List<CurveGraph> arrGraph = new ArrayList<CurveGraph>();
//        arrGraph.add(new CurveGraph("tide", 0xff27c1f5, graph3)); ////27C1F5
//
//        CurveGraphVO vo = new CurveGraphVO(legendArr, arrGraph);
//        return vo;
//    }

    /**
     * make Curve graph using options
     *
     * @return
     */
    private CurveGraphVO makeCurveGraphAllSetting() {
        //BASIC LAYOUT SETTING
        //padding
        int paddingBottom = CurveGraphVO.DEFAULT_PADDING;
        int paddingTop = CurveGraphVO.DEFAULT_PADDING;
        int paddingLeft = CurveGraphVO.DEFAULT_PADDING;
        int paddingRight = CurveGraphVO.DEFAULT_PADDING;

        //graph margin
        int marginTop = CurveGraphVO.DEFAULT_MARGIN_TOP;
        int marginRight = 20;//CurveGraphVO.DEFAULT_MARGIN_RIGHT;

        //max value
        int maxValue = CurveGraphVO.DEFAULT_MAX_VALUE;

        //increment
        int increment = CurveGraphVO.DEFAULT_INCREMENT;

        //GRAPH SETTING
        if (fourth_time != null) {
            legendArr = new String[]{first_time, second_time, third_time, fourth_time};
            graph3 = new int[]{Integer.parseInt(first), Integer.parseInt(second), Integer.parseInt(third), Integer.parseInt(fourth)};
        } else {
            legendArr = new String[]{first_time, second_time, third_time};
            graph3 = new int[]{Integer.parseInt(first), Integer.parseInt(second), Integer.parseInt(third)};
        }

        Log.d("점 찍어 ", "" + graph3[1]);

        List<CurveGraph> arrGraph = new ArrayList<CurveGraph>();

//        arrGraph.add(new CurveGraph("android", 0xaa66ff33, graph1, R.drawable.ic_launcher));
//        arrGraph.add(new CurveGraph("ios", 0xaa00ffff, graph2));
        arrGraph.add(new CurveGraph("tide", 0xff56a4fc, graph3)); //27C1F5//56A4FC


        CurveGraphVO vo = new CurveGraphVO(
                paddingBottom, paddingTop, paddingLeft, paddingRight,
                marginTop, marginRight, maxValue, increment, legendArr, arrGraph);

        //set animation
        vo.setAnimation(new GraphAnimation(GraphAnimation.CURVE_REGION_ANIMATION_2, GraphAnimation.DEFAULT_DURATION));
        //set graph name box
//        vo.setGraphNameBox(new GraphNameBox());
        //set draw graph region
        vo.setDrawRegion(true);

        //use icon
//		arrGraph.add(new Graph(0xaa66ff33, graph1, R.drawable.icon1));
//		arrGraph.add(new Graph(0xaa00ffff, graph2, R.drawable.icon2));
//		arrGraph.add(new Graph(0xaaff0066, graph3, R.drawable.icon3));

//		CurveGraphVO vo = new CurveGraphVO(
//				paddingBottom, paddingTop, paddingLeft, paddingRight,
//				marginTop, marginRight, maxValue, increment, legendArr, arrGraph, R.drawable.bg);
        return vo;
    }

    public Context getContxxt() {
        Log.i("getContxxt떴냐제발좀", "" + NowFragment.context);
        return NowFragment.context;
    }

}
