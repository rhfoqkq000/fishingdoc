package com.example.kim.fishingdoc.fish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kim.fishingdoc.R;

/**
 * Created by rhfoq on 2016-08-31.
 */
public class Fish_Write extends AppCompatActivity {
    Button add_name_bt;
    Button ok_name_bt;
    EditText edit_text;
    TextView name_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fish_write);

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

        edit_text = (EditText)findViewById(R.id.name_edit);
        name_text = (TextView)findViewById(R.id.name_text);

        add_name_bt = (Button)findViewById(R.id.button_add_name);
        ok_name_bt = (Button)findViewById(R.id.button_ok_name);

        add_name_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setVisibility(View.VISIBLE);
                name_text.setVisibility(View.INVISIBLE);
                add_name_bt.setVisibility(View.INVISIBLE);
                ok_name_bt.setVisibility(View.VISIBLE);
            }
        });
        ok_name_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setVisibility(View.INVISIBLE);
                name_text.setVisibility(View.VISIBLE);
                add_name_bt.setVisibility(View.VISIBLE);
                ok_name_bt.setVisibility(View.INVISIBLE);

                name_text.setText(edit_text.getText());

                if(name_text.getTextSize()>0){
                    ok_name_bt.setText("확인");
                }
            }
        });
        final Button button_add_distin = (Button)findViewById(R.id.button_add_distin);
        final Button button_save_distin = (Button)findViewById(R.id.button_save_distin);

        //확인 버튼 눌렀을 때
        button_add_distin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_distin.setVisibility(View.INVISIBLE);
                text_distin.setVisibility(View.VISIBLE);
                button_add_distin.setVisibility(View.INVISIBLE);
                button_save_distin.setVisibility(View.VISIBLE);

                text_distin.setText(edit_distin.getText().toString());
//                String temp = (String)text_distin.getText();
//                edit_distin.setText(temp);
            }
        });

        //수정 버튼 눌렀을 때
        button_save_distin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_distin.setVisibility(View.VISIBLE);
                text_distin.setVisibility(View.INVISIBLE);
                button_add_distin.setVisibility(View.VISIBLE);
                button_save_distin.setVisibility(View.INVISIBLE);

            }
        });

//        text_habit.setText("습성~~");

        final Button button_add_habit = (Button)findViewById(R.id.button_add_habit);
        final Button button_save_habit = (Button)findViewById(R.id.button_save_habit);

        //확인 버튼 눌렀을 때
        button_add_habit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_habit.setVisibility(View.INVISIBLE);
                text_habit.setVisibility(View.VISIBLE);
                button_add_habit.setVisibility(View.INVISIBLE);
                button_save_habit.setVisibility(View.VISIBLE);

                text_habit.setText(edit_habit.getText().toString());
//                String temp = (String)text_habit.getText();
//                edit_habit.setText(temp);
//                writeFishTask writeFishTask = new writeFishTask(id+1, "habit", edit_habit.getText().toString(), text_distin, edit_distin);
//                writeFishTask.execute("http://45.32.61.201:3000/fish/");
//                text_habit.setText(edit_habit.getText().toString());
            }
        });

        //수정 버튼 눌렀을 때
        button_save_habit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_habit.setVisibility(View.VISIBLE);
                text_habit.setVisibility(View.INVISIBLE);
                button_add_habit.setVisibility(View.VISIBLE);
                button_save_habit.setVisibility(View.INVISIBLE);

            }
        });

//        text_live.setText("서식지");

        final Button button_add_live = (Button)findViewById(R.id.button_add_live);
        final Button button_save_live = (Button)findViewById(R.id.button_save_live);

        //추가 버튼 눌렀을 때
        button_add_live.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_live.setVisibility(View.INVISIBLE);
                text_live.setVisibility(View.VISIBLE);
                button_add_live.setVisibility(View.INVISIBLE);
                button_save_live.setVisibility(View.VISIBLE);

//                String temp = (String)text_live.getText();
//                edit_live.setText(temp);
                text_live.setText(edit_live.getText().toString());
            }
        });

        //저장 버튼 눌렀을 때
        button_save_live.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_live.setVisibility(View.VISIBLE);
                text_live.setVisibility(View.INVISIBLE);
                button_add_live.setVisibility(View.VISIBLE);
                button_save_live.setVisibility(View.INVISIBLE);
//                String fish_id = fish+"3";

            }
        });


//        text_bait.setText("미끼");

        final Button button_add_bait = (Button)findViewById(R.id.button_add_bait);
        final Button button_save_bait = (Button)findViewById(R.id.button_save_bait);

        //추가 버튼 눌렀을 때
        button_add_bait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_bait.setVisibility(View.INVISIBLE);
                text_bait.setVisibility(View.VISIBLE);
                button_add_bait.setVisibility(View.INVISIBLE);
                button_save_bait.setVisibility(View.VISIBLE);
//
//                String temp = (String)text_bait.getText();
//                edit_bait.setText(temp);
                text_bait.setText(edit_bait.getText().toString());
            }
        });

        //저장 버튼 눌렀을 때
        button_save_bait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_bait.setVisibility(View.VISIBLE);
                text_bait.setVisibility(View.INVISIBLE);
                button_add_bait.setVisibility(View.VISIBLE);
                button_save_bait.setVisibility(View.INVISIBLE);
            }
        });


//        text_catched.setText("잡는방법");

        final Button button_add_catched = (Button)findViewById(R.id.button_add_catched);
        final Button button_save_catched = (Button)findViewById(R.id.button_save_catched);

        //추가 버튼 눌렀을 때
        button_add_catched.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_catched.setVisibility(View.INVISIBLE);
                text_catched.setVisibility(View.VISIBLE);
                button_add_catched.setVisibility(View.INVISIBLE);
                button_save_catched.setVisibility(View.VISIBLE);

//                String temp = (String)text_catched.getText();
//                edit_catched.setText(temp);
                text_catched.setText(edit_catched.getText().toString());
            }
        });

        //저장 버튼 눌렀을 때
        button_save_catched.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_catched.setVisibility(View.VISIBLE);
                text_catched.setVisibility(View.INVISIBLE);
                button_add_catched.setVisibility(View.VISIBLE);
                button_save_catched.setVisibility(View.INVISIBLE);
            }
        });
    }
}
