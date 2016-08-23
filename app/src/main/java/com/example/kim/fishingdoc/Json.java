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
import java.util.HashMap;

/**
 * Created by Ryu on 2016-07-28.
 */

public class Json extends AsyncTask<String, String, HashMap<String,ArrayList<String>>> {

    HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
    public static ArrayList<String> sido_kr = new ArrayList<>();
    public static ArrayList<String> sido_en = new ArrayList<>();
    public static ArrayList<Double> latitude = new ArrayList<>();
    public static ArrayList<Double> longitude = new ArrayList<>();
    public static ArrayList<String> location = new ArrayList<>();

    public static ArrayList<String> luna = new ArrayList<>();

    String addSt;

    @Override
    protected HashMap<String, ArrayList<String>> doInBackground(String... params) {
        HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
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

                try {
                    // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                    JSONArray jArray = new JSONArray(jsonHtml.toString());
                    JSONObject jObject;
                    luna.clear();
                    sido_kr.clear();
                    sido_en.clear();
                    longitude.clear();
                    latitude.clear();
                    location.clear();
                    addSt = null;

//          //ArrayList<Double> -> ArrayList<String>
//            ArrayList<String> latitude2 = new ArrayList<String>();
//            for (Double d:latitude)
//                latitude2.add(d.toString());




//                    ArrayList<String> longitude2 = new ArrayList<String>();
//                    for (Double d:longitude)
//                        longitude2.add(d.toString());
//
//                    ArrayList<String> latitude2 = new ArrayList<String>();
//                    for (Double d:latitude)
//                        latitude2.add(d.toString());


                    for (int i = 0; i < jArray.length(); i++) {
                        jObject = jArray.getJSONObject(i);
//                if (jObject.has("luna")) {
//                    luna.add(i, jObject.getString("luna"));
//                }
                        sido_kr.add(i, jObject.getString("sido_kr"));
                        sido_en.add(i, jObject.getString("sido_en"));
                        longitude.add(i, jObject.getDouble("longitude"));
                        latitude.add(i, jObject.getDouble("latitude"));
                        location.add(i, jObject.getString("location"));

                        hash.put("sido_kr", sido_kr);
                        hash.put("sido_en", sido_en);
//                        hash.put("longitude", longitude2);
//                        hash.put("latitude", latitude2);
                        hash.put("location", location);
//                addSt = address.get(i);
//                add.add(i, addSt);
//                    Log.i("테스트 세번째", "First : " + first + " / Second : " + second);
                    }
//                    Log.i("asynctask에서 hash떴냥",""+hash);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    protected void onPostExecute(String str) {
//        try {
//            // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
//            JSONArray jArray = new JSONArray(str);
//            JSONObject jObject;
//            luna.clear();
//            sido_kr.clear();
//            sido_en.clear();
//            longitude.clear();
//            latitude.clear();
//            location.clear();
//            addSt = null;
//
//            for (int i = 0; i < jArray.length(); i++) {
//                jObject = jArray.getJSONObject(i);
////                if (jObject.has("luna")) {
////                    luna.add(i, jObject.getString("luna"));
////                }
//                sido_kr.add(i, jObject.getString("sido_kr"));
//                sido_en.add(i, jObject.getString("sido_en"));
//                longitude.add(i, jObject.getDouble("longitude"));
//                latitude.add(i, jObject.getDouble("latitude"));
//                location.add(i, jObject.getString("location"));
////                addSt = address.get(i);
////                add.add(i, addSt);
////                    Log.i("테스트 세번째", "First : " + first + " / Second : " + second);
//            }
////            Collections.sort(sido_kr, String.CASE_INSENSITIVE_ORDER);
////            Log.i("두번째 excute 끝났다", "" + sido_kr);
////                String zzzz= "" + second2;
////                String zzz= "" + add;
////                String zz = "" + address;
////                tv.setText(zzzz);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        }
    }

}