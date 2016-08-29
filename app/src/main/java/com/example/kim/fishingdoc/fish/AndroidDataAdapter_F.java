package com.example.kim.fishingdoc.fish;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.fishingdoc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kim on 2016-08-16.
 */
public class AndroidDataAdapter_F extends RecyclerView.Adapter<AndroidDataAdapter_F.ViewHolder> {
    private ArrayList<AndroidVersion> arrayList;
    ArrayList<String> checkList;
    private Context mcontext;
    ArrayList<String> imgs, fish;


    public AndroidDataAdapter_F(Context context, ArrayList<AndroidVersion> android) {
        this.arrayList = android;
        this.mcontext = context;
    }

    public AndroidDataAdapter_F(Context context, ArrayList<AndroidVersion> android, ArrayList<String> imgs, ArrayList<String> fish) {
        this.arrayList = android;
        this.mcontext = context;
        this.imgs = imgs;
        this.fish = fish;
    }

    @Override
    public void onBindViewHolder(AndroidDataAdapter_F.ViewHolder holder, int i) {
//        Log.i("어댑터에서imgs떴냐용",""+imgs.get(i));
        holder.textView.setText(fish.get(i).toString());
//        holder.imageView.setImageResource(arrayList.get(i).getrecyclerViewImage());
        Picasso.with(mcontext).load(imgs.get(i)).resize(100, 100).into(holder.imageView);

        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("onLongClick이당","1");
                DialogSimple();
                return false;
            }
        });

    }

    @Override
    public AndroidDataAdapter_F.ViewHolder onCreateViewHolder(ViewGroup vGroup, int i) {

        View view = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.fish_cardview, vGroup, false);
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

    private void DialogSimple(){
        checkList = new ArrayList<>();
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(mcontext);
        alt_bld.setMessage("등록?^0^").setCancelable(
                false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        // Title for AlertDialog
        alert.setTitle("등☆록하기");
        // Icon for AlertDialog
        alert.setIcon(R.drawable.icon);
        alert.show();
    }
}
