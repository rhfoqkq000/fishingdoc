package com.example.kim.fishingdoc.fish;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 2016-07-15.
 */
class getFishInfo extends AsyncTask<String, String, String> {
    String result,id, param, content = "";
    int fish_id;
    ArrayList<String> paramList = new ArrayList<String>();

    public getFishInfo(String id, String param, String content, int fish_id){
        this.id = id;
        this.param = param;
        this.content = content;
        this.fish_id = fish_id;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
//            paramList.add(0, "distin");
//            paramList.add(1, "habit");
//            paramList.add(2, "live");
//            paramList.add(3, "bait");
//            paramList.add(4, "catched");
//            for(int i = 0; i<paramList.size(); i++){
//                if(param.equals(paramList.get(i))){
//                    paramList.remove(i);
//                }
//            }
//            Log.i("paramList뜬거맞냐","잘뜸ㅎ"+paramList);

            URL url = new URL(strings[0]+id+"/edit");
//            Log.i("URL제대로떴냐",""+url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            JSONObject obj = new JSONObject();
            JSONObject obj2 = new JSONObject();
            obj.put(param, content);
//            for(int i = 0; i<paramList.size(); i++){
//                obj.put(paramList.get(i), "");
//            }
            obj2.put("doc", obj);
            Log.i("obj2떴냐2",""+obj2.toString());
            urlConnection.setRequestMethod("POST");
//            Log.i("url뭐냐",""+url);
            OutputStreamWriter os = new OutputStreamWriter(
                    urlConnection.getOutputStream());
            os.write(obj2.toString());
            os.flush();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }
            result = content;
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

    }

}