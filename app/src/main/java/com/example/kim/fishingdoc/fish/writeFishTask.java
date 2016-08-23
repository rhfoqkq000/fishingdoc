package com.example.kim.fishingdoc.fish;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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

class writeFishTask extends AsyncTask<String, String, String> {
    String result, fish_id, param, content = "";
    TextView tv;
    EditText ed;

    public writeFishTask(String fish_id, String param, String content, TextView tv, EditText ed){
        this.fish_id = fish_id;
        this.param = param;
        this.content = content;
        this.tv = tv;
        this.ed = ed;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]+fish_id+"/edit");
            Log.i("URL제대로떴냐",""+url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            JSONObject obj = new JSONObject();
            JSONObject obj2 = new JSONObject();
            obj.put(param, content);
            obj2.put("doc", obj);
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
            result = content;
//            JSONArray jArray = new JSONArray(builder.toString());
//            for (int i = 0; i < jArray.length(); i++) {
//                JSONObject topLevel = jArray.getJSONObject(i);
////                fishName.add(i, ObjToString(topLevel.get("name")));
//                result = String.valueOf(topLevel.get(param));
//                Log.i("result떳어",""+result);
//            }
            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
//        Log.i("hash떴냐..",""+result);
        tv.setText(result);
        ed.setText(result);
    }
}