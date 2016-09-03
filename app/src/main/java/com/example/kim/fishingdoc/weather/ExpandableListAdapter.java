package com.example.kim.fishingdoc.weather;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.weather.mFragment.MonthFragment;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraphVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kim on 2016-08-19.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private RelativeLayout layoutGraphView;
    static CurveGraphVO vo;

    String[] legendArr;
    int[] graph3;

    MonthFragment mf = new MonthFragment();

    private Context context;
    private List<String> ParentItem;
    private HashMap<String, List<Integer>> ChildItem;


    public ExpandableListAdapter(Context context, List<String> ParentItem,
                                 HashMap<String, List<Integer>> ChildItem) {
        this.context = context;
        this.ParentItem = ParentItem;
        this.ChildItem = ChildItem;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.ChildItem.get(this.ParentItem.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.month_child, null);
        }

        ImageView image = (ImageView)convertView.findViewById(R.id.child_image);
        if(listPosition==0){
            image.setImageResource(R.drawable.graph);
        }else if(listPosition==1){
            image.setImageResource(R.drawable.graph);
        }else if(listPosition==2) {
            image.setImageResource(R.drawable.graph);
        }else if(listPosition==3) {
            image.setImageResource(R.drawable.graph);
        }
            return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.ChildItem.get(this.ParentItem.get(listPosition))
                .size();
    }
//
    @Override
    public Object getGroup(int listPosition) {
        return this.ParentItem.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.ParentItem.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.month_parent, null);
        }
        TextView parent_date = (TextView) convertView.findViewById(R.id.parent_date);
        parent_date.setTypeface(null, Typeface.BOLD);
        parent_date.setText(listTitle);

        String today = SingObj.getSingObj().getToday();
        ArrayList<String> moonRiseList = SingObj.getSingObj().getMoonRiseList();

        Calendar cal = Calendar.getInstance();
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH)+1;
        int date = cal.get(cal.DATE);


        String st = month+"/"+date+"/"+year;
        String st1 = month+"/"+(date+1)+"/"+year;
        String st2 = month+"/"+(date+2)+"/"+year;
        String st3 = month+"/"+(date+3)+"/"+year;

        if(listPosition==0){
            parent_date.setText(st);
        }else if(listPosition==1){
            parent_date.setText(st1);
        }else if(listPosition==2){
            parent_date.setText(st2);
        }else if(listPosition==3){
            parent_date.setText(st3);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    //parent와 child를 잇기 위해 만들어놓은 것. 실제로 출력되는 것과 상관x 그러나 새로 parent를 추가할 때마다 그에 맞게 출력해야함
    public static HashMap<String, List<Integer>> getData() {
        HashMap<String, List<Integer>> ParentItem = new HashMap<String, List<Integer>>();

        //실제로 chileviw 이미지를 넣는 곳.

        List<Integer> current1 = new ArrayList<Integer>();
        current1.add(R.drawable.graph);

        List<Integer> current2 = new ArrayList<Integer>();
        current2.add(R.drawable.graph);

        List<Integer> current3 = new ArrayList<Integer>();
        current3.add(R.drawable.graph);

        List<Integer> current4 = new ArrayList<Integer>();
        current4.add(R.drawable.graph);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH)+1;
        int date = cal.get(cal.DATE);

        String st = month+"/"+date+"/"+year;
        String st1 = month+"/"+(date+1)+"/"+year;
        String st2 = month+"/"+(date+2)+"/"+year;
        String st3 = month+"/"+(date+3)+"/"+year;

        ParentItem.put(st, current1);
        ParentItem.put(st1, current2);
        ParentItem.put(st2, current3);
        ParentItem.put(st3, current4);

        return ParentItem;

    }
}


