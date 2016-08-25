package com.example.kim.fishingdoc.fish;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 2016-07-15.
 */
class getFishTask extends AsyncTask<String, String, HashMap<String, ArrayList<String>>> {
    ArrayList<String> fishName = new ArrayList<String>();
    ArrayList<String> fishImg = new ArrayList<String>();
    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> fish_id = new ArrayList<String>();
    ArrayList<String> distin = new ArrayList<String>();
    ArrayList<String> habit = new ArrayList<String>();
    static HashMap<String, ArrayList<String>> hash;

    @Override
    protected HashMap<String, ArrayList<String>> doInBackground(String... strings) {
        try {
            hash = new HashMap<String, ArrayList<String>>();
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }
            JSONArray jArray = new JSONArray(builder.toString());
//            Log.i("jArray뭐야",""+jArray);

            try {
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject topLevel = jArray.getJSONObject(i);
//                Log.i("topLevel떴냐",""+topLevel);
                    JSONObject topLevel2 = (JSONObject) topLevel.get("doc");
//                Log.i("topLevel2떴냐",""+topLevel2);
                    if (topLevel.get("name") != null) {
                        fishName.add(i, ObjToString(topLevel.get("name")));
                    } else {
                        fishName.add(i, "NULL");
                    }

                    if (topLevel.get("img_path") != null) {
                        fishImg.add(i, ObjToString(topLevel.get("img_path")));
                    } else {
                        fishImg.add(i, "NULL");
                    }

                    if (topLevel.get("id") != null) {
                        id.add(i, ObjToString(topLevel.get("id")));
                    } else {
                        id.add(i, "NULL");
                    }

                    if (topLevel.get("fish_id") != null) {
                        fish_id.add(i, ObjToString(topLevel.get("fish_id")));
                    } else {
                        fish_id.add(i, "NULL");
                    }

                    if (topLevel2.get("distin") != null) {
                        distin.add(i, ObjToString(topLevel2.get("distin")));
                    } else {
                        distin.add(i, "NULL");

                    }
                }



//                habit.add(i, ObjToString(topLevel2.get("habit")));


                }catch(Exception e){
                e.printStackTrace();
            }

            hash.put("fishName", fishName);
            hash.put("fishImg", fishImg);
            hash.put("id", id);
            hash.put("fish_id", fish_id);
            hash.put("distin", distin);
//            hash.put("habit", habit);

//            Log.i("fishName떴냐",""+fishName);
//            Log.i("fishImg떴냐",""+fishImg);
//            Log.i("id떴냐",""+id);
//            Log.i("fish_id떴냐",""+fish_id);
//            Log.i("distin떴냐",""+distin);
//            Log.i("getFishTask result떴냐",""+hash);
            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return hash;

    }

    @Override
    protected void onPostExecute(HashMap<String, ArrayList<String>> result) {
//        Log.i("hash떴냐..",""+result);
    }

    private String ObjToString(Object p)
    {
        String string = "";
        if (p == null)
        {
            string = "";
        }
        else
        {
            string = p.toString();
        }
        return string;
    }
}