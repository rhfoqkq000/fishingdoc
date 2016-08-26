package com.example.kim.fishingdoc.fish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.fishingdoc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kim on 2016-08-18.
 */
public class Fish_Content extends AppCompatActivity {
    int id;
//    String fish_id2 = String.valueOf(fish);
ArrayList<String> idList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fish_content);
        ArrayList<String> distin = new ArrayList<String>();
        final int fish, fish_id;
        HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();

        Intent intent = getIntent();
        ArrayList<String> titleText = intent.getExtras().getStringArrayList("titleText");
        ArrayList<String> imgUrl = intent.getExtras().getStringArrayList("imgUrl");
        id = intent.getExtras().getInt("id");
        fish_id = intent.getExtras().getInt("fish_id");
        idList = intent.getExtras().getStringArrayList("idList");
//        Log.i("idList뭐잇냥",""+idList.get(id));
        distin = intent.getExtras().getStringArrayList("distin");
        TextView content_text = (TextView)findViewById(R.id.content_text);
        content_text.setText(titleText.get(id));

        ImageView imv = (ImageView)findViewById(R.id.content_image);
        Picasso.with(getApplicationContext()).load(imgUrl.get(id)).into(imv);

        Toolbar toolbar1 = (Toolbar) findViewById(R.id.fish_toolbar);
        setSupportActionBar(toolbar1);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final EditText edit_distin = (EditText)findViewById(R.id.edit_distin);
        final TextView text_distin = (TextView)findViewById(R.id.text_distin);
//        text_distin.setText("특징 메롱메롱메롱!");
        final EditText edit_habit = (EditText)findViewById(R.id.edit_habit);
        final TextView text_habit = (TextView)findViewById(R.id.text_habit);
        final EditText edit_live = (EditText)findViewById(R.id.edit_live);
        final TextView text_live = (TextView)findViewById(R.id.text_live);
        final EditText edit_bait = (EditText)findViewById(R.id.edit_bait);
        final TextView text_bait = (TextView)findViewById(R.id.text_bait);
        final EditText edit_catched = (EditText)findViewById(R.id.edit_catched);
        final TextView text_catched = (TextView)findViewById(R.id.text_catched);

//        Log.i("distin.get(id)떴냐",""+distin.get(id));
//        text_distin.setText(distin.get(id));
//        edit_distin.setText(distin.get(id));
        try{
            getFishTask getFishTask = new getFishTask();
            hash = getFishTask.execute("http://45.32.61.201:3000/fish").get();

//            Log.i("hash뜨긴떴냐",""+hash);

//            Log.i("hash.get이다데스",""+hash.get("distin"));
            text_distin.setText(hash.get("distin").get(id));
            edit_distin.setText(hash.get("distin").get(id));
            text_habit.setText(hash.get("habit").get(id));
            edit_habit.setText(hash.get("habit").get(id));
            text_live.setText(hash.get("live").get(id));
            edit_live.setText(hash.get("live").get(id));
            text_bait.setText(hash.get("bait").get(id));
            edit_bait.setText(hash.get("bait").get(id));
            text_catched.setText(hash.get("catched").get(id));
            edit_catched.setText(hash.get("catched").get(id));
//            writeFishTask writeFishTask = new writeFishTask(idList.get(id), );

//            resetFish resetFish = new resetFish(idList);
////            Log.i("distin이 왜 널이냐","1111"+text_distin.getText().toString());
//        resetFish.execute("http://45.32.61.201:3000/fish/");
//        Log.i("resetFish.execute끝^^","1");
//
        }catch(Exception e){
            e.printStackTrace();
        }



        final Button button_add_distin = (Button)findViewById(R.id.button_add_distin);
        final Button button_save_distin = (Button)findViewById(R.id.button_save_distin);

        //추가 버튼 눌렀을 때
        button_add_distin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_distin.setVisibility(View.VISIBLE);
                text_distin.setVisibility(View.INVISIBLE);
                button_add_distin.setVisibility(View.INVISIBLE);
                button_save_distin.setVisibility(View.VISIBLE);

//                String temp = (String)text_distin.getText();
//                edit_distin.setText(temp);
            }
        });

        //저장 버튼 눌렀을 때
        button_save_distin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_distin.setVisibility(View.INVISIBLE);
                text_distin.setVisibility(View.VISIBLE);
                button_add_distin.setVisibility(View.VISIBLE);
                button_save_distin.setVisibility(View.INVISIBLE);
                getFishInfo getFishInfo = new getFishInfo(idList.get(id), "distin", edit_distin.getText().toString(), fish_id);
                getFishInfo.execute("http://45.32.61.201:3000/fish/");
                text_distin.setText(edit_distin.getText().toString());
            }
        });



//        text_habit.setText("습성~~");

        final Button button_add_habit = (Button)findViewById(R.id.button_add_habit);
        final Button button_save_habit = (Button)findViewById(R.id.button_save_habit);

        //추가 버튼 눌렀을 때
        button_add_habit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_habit.setVisibility(View.VISIBLE);
                text_habit.setVisibility(View.INVISIBLE);
                button_add_habit.setVisibility(View.INVISIBLE);
                button_save_habit.setVisibility(View.VISIBLE);

//                String temp = (String)text_habit.getText();
//                edit_habit.setText(temp);
//                writeFishTask writeFishTask = new writeFishTask(id+1, "habit", edit_habit.getText().toString(), text_distin, edit_distin);
//                writeFishTask.execute("http://45.32.61.201:3000/fish/");
//                text_habit.setText(edit_habit.getText().toString());
            }
        });

        //저장 버튼 눌렀을 때
        button_save_habit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_habit.setVisibility(View.INVISIBLE);
                text_habit.setVisibility(View.VISIBLE);
                button_add_habit.setVisibility(View.VISIBLE);
                button_save_habit.setVisibility(View.INVISIBLE);

                getFishInfo getFishInfo = new getFishInfo(idList.get(id), "habit", edit_habit.getText().toString(), fish_id);
                getFishInfo.execute("http://45.32.61.201:3000/fish/");
                text_habit.setText(edit_habit.getText().toString());
            }
        });

//        text_live.setText("서식지");

        final Button button_add_live = (Button)findViewById(R.id.button_add_live);
        final Button button_save_live = (Button)findViewById(R.id.button_save_live);

        //추가 버튼 눌렀을 때
        button_add_live.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_live.setVisibility(View.VISIBLE);
                text_live.setVisibility(View.INVISIBLE);
                button_add_live.setVisibility(View.INVISIBLE);
                button_save_live.setVisibility(View.VISIBLE);

                String temp = (String)text_live.getText();
                edit_live.setText(temp);
            }
        });

        //저장 버튼 눌렀을 때
        button_save_live.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_live.setVisibility(View.INVISIBLE);
                text_live.setVisibility(View.VISIBLE);
                button_add_live.setVisibility(View.VISIBLE);
                button_save_live.setVisibility(View.INVISIBLE);
//                String fish_id = fish+"3";

                getFishInfo getFishInfo = new getFishInfo(idList.get(id), "live", edit_live.getText().toString(), fish_id);
                getFishInfo.execute("http://45.32.61.201:3000/fish/");
                text_live.setText(edit_live.getText().toString());
            }
        });


//        text_bait.setText("미끼");

        final Button button_add_bait = (Button)findViewById(R.id.button_add_bait);
        final Button button_save_bait = (Button)findViewById(R.id.button_save_bait);

        //추가 버튼 눌렀을 때
        button_add_bait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_bait.setVisibility(View.VISIBLE);
                text_bait.setVisibility(View.INVISIBLE);
                button_add_bait.setVisibility(View.INVISIBLE);
                button_save_bait.setVisibility(View.VISIBLE);

                String temp = (String)text_bait.getText();
                edit_bait.setText(temp);
            }
        });

        //저장 버튼 눌렀을 때
        button_save_bait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_bait.setVisibility(View.INVISIBLE);
                text_bait.setVisibility(View.VISIBLE);
                button_add_bait.setVisibility(View.VISIBLE);
                button_save_bait.setVisibility(View.INVISIBLE);
                getFishInfo getFishInfo = new getFishInfo(idList.get(id), "bait", edit_bait.getText().toString(), fish_id);
                getFishInfo.execute("http://45.32.61.201:3000/fish/");
                text_bait.setText(edit_bait.getText().toString());
            }
        });


//        text_catched.setText("잡는방법");

        final Button button_add_catched = (Button)findViewById(R.id.button_add_catched);
        final Button button_save_catched = (Button)findViewById(R.id.button_save_catched);

        //추가 버튼 눌렀을 때
        button_add_catched.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_catched.setVisibility(View.VISIBLE);
                text_catched.setVisibility(View.INVISIBLE);
                button_add_catched.setVisibility(View.INVISIBLE);
                button_save_catched.setVisibility(View.VISIBLE);

                String temp = (String)text_catched.getText();
                edit_catched.setText(temp);
            }
        });

        //저장 버튼 눌렀을 때
        button_save_catched.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_catched.setVisibility(View.INVISIBLE);
                text_catched.setVisibility(View.VISIBLE);
                button_add_catched.setVisibility(View.VISIBLE);
                button_save_catched.setVisibility(View.INVISIBLE);
                getFishInfo getFishInfo = new getFishInfo(idList.get(id), "catched", edit_catched.getText().toString(), fish_id);
                getFishInfo.execute("http://45.32.61.201:3000/fish/");
                text_catched.setText(edit_catched.getText().toString());
            }
        });
    }
}


