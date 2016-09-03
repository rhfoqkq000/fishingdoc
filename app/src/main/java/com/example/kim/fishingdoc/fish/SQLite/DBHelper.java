package com.example.kim.fishingdoc.fish.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    Handler handler = new Handler();

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE CHECKLIST (id TEXT PRIMARY KEY, name TEXT, imgurl TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String id, String name, String imgurl) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO CHECKLIST VALUES('"+id+"','"+name+"','"+imgurl+"');");
        db.close();
    }

    public void update(String item, int price) {
//        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
//        db.execSQL("UPDATE MONEYBOOK SET price=" + price + " WHERE item='" + item + "';");
//        db.close();
    }

    public int delete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM CHECKLIST WHERE id='" + id + "';");
        db.close();
        return 1;
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
//        String result = "";
//        ArrayList<String> result22 = new ArrayList<String>();
        String result22 = "";
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM CHECKLIST", null);
       while (cursor.moveToNext()) {
          // result += cursor.getString(1)+"\n\n\n";
//           result22 = cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2);
           result22 += cursor.getString(0)
                   + ","
                   + cursor.getString(1)
                   + ","
                   + cursor.getString(2)
                   + "^^";
       }

        return result22;
    }





    public HashMap<String, ArrayList<String>> getResult2() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
//        String result = "";
//        ArrayList<String> result22 = new ArrayList<String>();
        ArrayList<String> idList = new ArrayList<String>();
        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> imgList = new ArrayList<String>();
        HashMap<String, ArrayList<String>> result22 = new HashMap<String, ArrayList<String>>();
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM CHECKLIST", null);
        while (cursor.moveToNext()) {
            // result += cursor.getString(1)+"\n\n\n";
//           result22 = cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2);
            idList.add(cursor.getString(0));
            nameList.add(cursor.getString(1));
            imgList.add(cursor.getString(2));
        }
        result22.put("idList", idList);
        result22.put("nameList", nameList);
        result22.put("imgList", imgList);

        return result22;
    }

}