package com.example.kim.fishingdoc.fish;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class insertFishTask extends AsyncTask<String, String, String> {
    ArrayList<String> resultArray;
    ArrayList<String> fish_id_list;

    public insertFishTask(ArrayList<String> resultArray, ArrayList<String> fish_id_list){
        this.resultArray = resultArray;
        this.fish_id_list = fish_id_list;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        try {
            URL url = new URL(strings[0]+"/new");
            Log.i("URL제대로떴냐",""+url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            JSONObject obj = new JSONObject();
            JSONObject obj2 = new JSONObject();
            obj.put("distin", resultArray.get(1));
            obj.put("habit", resultArray.get(2));
            obj.put("live", resultArray.get(3));
            obj.put("bait", resultArray.get(4));
            obj.put("catched", resultArray.get(5));
            obj2.put("doc", obj);
            obj2.put("img_path","NULL");
            obj2.put("name",resultArray.get(0));
            obj2.put("fish_id", fish_id_list.size()+1);
            urlConnection.setRequestMethod("POST");
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
            result = builder.toString();
            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        Log.i("insertFish result떴쩌욤",""+result);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

    }
}