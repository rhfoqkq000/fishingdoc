package com.example.kim.fishingdoc.check;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.fish.AndroidVersion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kim on 2016-08-16.
 */
public class AndroidDataAdapter_C extends RecyclerView.Adapter<AndroidDataAdapter_C.ViewHolder> {
    private ArrayList<AndroidVersion> arrayList;
    private Context mcontext;
    ArrayList<String> imgs, fish;


    public AndroidDataAdapter_C(Context context, ArrayList<AndroidVersion> android) {
        this.arrayList = android;
        this.mcontext = context;
    }
    public AndroidDataAdapter_C(Context context, ArrayList<AndroidVersion> android, ArrayList<String> imgs, ArrayList<String> fish) {
        this.arrayList = android;
        this.mcontext = context;
        this.imgs = imgs;
        this.fish = fish;
    }

    @Override
    public void onBindViewHolder(AndroidDataAdapter_C.ViewHolder holder, int i) {

        holder.textView.setText(fish.get(i).toString());
//        holder.imageView.setImageResource(arrayList.get(i).getrecyclerViewImage());
        if(imgs.get(i).substring(0, 6).equals("images")){
            try{
                Log.i("if는되는것같은뎅","1");
//                getFishTask getFishTask = new getFishTask();
//                HashMap<String, ArrayList<String>> hash = getFishTask.execute().get();
//                Log.i("if는되는것같은뎅","2");

                String replace = "http://45.32.61.201:3000/"+imgs.get(i);
                Log.i("if는되는것같은뎅",""+replace);

//                imgs.get(i).replace(replace, replace);
                imgs.set(i, replace);
                Log.i("imgs제대로뜸",""+imgs.get(i));
//                Picasso.with(mcontext).load(imgs.get(i)).resize(100, 100).into(holder.imageView);

            }catch (Exception e){
                e.printStackTrace();
                Log.i("이미지뾰롱뾰롱","뭔가있었는데안됐다데스");

            }
        }
        Picasso.with(mcontext).load(imgs.get(i)).into(holder.imageView);

    }

    @Override
    public AndroidDataAdapter_C.ViewHolder onCreateViewHolder(ViewGroup vGroup, int i) {

        View view = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.check_cardview, vGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.text);
            imageView = (ImageView) v.findViewById(R.id.image);

        }
    }
}
