package com.example.kim.fishingdoc;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Ryu on 2016-07-28.
 */

public class Json2 extends AsyncTask<String, String, String> {
    public static ArrayList<String> date = new ArrayList<>();
    public static ArrayList<String> height = new ArrayList<>();
    public static ArrayList<String> luna = new ArrayList<>();

    String addSt;

    @Override
    protected String doInBackground(String... params) {
        StringBuilder jsonHtml = new StringBuilder();
        try {
            URL phpUrl = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) phpUrl.openConnection();

            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        jsonHtml.append(line + "\n");
                    }
                    Log.i("jsonHtml.toString() 컴온", jsonHtml.toString());
                    br.close();
                }
                conn.disconnect(); //메모리누수방지
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonHtml.toString();
    }

    protected void onPostExecute(String str) {
        try {
            // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
            JSONArray jArray = new JSONArray(str);
            jArray.get(0);
            JSONObject jObject;
            date.clear();
            height.clear();
            luna.clear();
            for (int i = 1; i < jArray.length()-1; i++) {
                jObject = jArray.getJSONObject(i);
                date.add(i - 1, jObject.getString("data"));
                height.add(i - 1, jObject.getString("height"));
//                Log.e("height : ",""+jObject.getString("height"));
                luna.add(i - 1, jObject.getString("luna"));
//                Log.e("json2<Luna> : ",""+jObject.getString("luna"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getHeight(String day) {
        int num = 0;
        for (int i = 0; i < date.size(); i++) {
            if (day.equals(date.get(i))) {
                num = i;
            }
        }
        return height.get(num);
    }

    public String getLuna(String day) {
        int num = 0;
        for (int i = 0; i < date.size(); i++) {
            if (day.equals(date.get(i))) {
                num = i;
            }
        }
        return luna.get(num);
    }
}