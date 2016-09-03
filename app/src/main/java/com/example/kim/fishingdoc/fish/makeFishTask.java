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

class makeFishTask extends AsyncTask<String, String, String> {

    int newFish_id;
    String newName;

    public makeFishTask(int newFish_id, String newName){
        this.newFish_id = newFish_id;
        this.newName = newName;
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
            obj.put("distin", "NULL");
            obj.put("habit", "NULL");
            obj.put("live", "NULL");
            obj.put("bait", "NULL");
            obj.put("catched", "NULL");

            obj2.put("doc", obj);
            obj2.put("img_path","NULL");
            obj2.put("name",newName);
            obj2.put("fish_id", newFish_id);
            Log.i("obj2떴냐",""+obj2.toString());
            urlConnection.setRequestMethod("POST");
//            Log.i("url뭐냐",""+url);
//            OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
//            osw.write(obj.toString());
//            osw.flush();
//            osw.close();
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
            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

    }
}