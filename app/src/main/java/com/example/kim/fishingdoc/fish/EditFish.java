package com.example.kim.fishingdoc.fish;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by user on 2016-07-15.
 */
class EditFish extends AsyncTask<String, String, String> {
    String result,id, param, content, email = "";
    int fish_id;
    ArrayList<String> paramList = new ArrayList<String>();
    TextView tvHistory;

    public EditFish(String id, String param, String content, int fish_id, String email, TextView tvHistory){
        this.id = id;
        this.param = param;
        this.content = content;
        this.fish_id = fish_id;
        this.email = email;
        this.tvHistory = tvHistory;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]+id+"/edit");
//            Log.i("URL제대로떴냐",""+url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            JSONObject obj = new JSONObject();
            JSONObject obj2 = new JSONObject();
            Calendar calendar = Calendar.getInstance();
            obj.put(param, content);
            obj.put("email", email);
            obj.put("history", email+"님이 "+calendar.getTime().toString().substring(0, 19)+"에 수정^^");
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
        return email;
    }

    @Override
    protected void onPostExecute(String email) {
        Calendar calendar = Calendar.getInstance();
        tvHistory.setText(email+"님이 "+calendar.getTime().toString().substring(0, 19)+"에 수정^^");
    }

}