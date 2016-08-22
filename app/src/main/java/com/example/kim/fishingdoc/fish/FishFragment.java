package com.example.kim.fishingdoc.fish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kim.fishingdoc.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kim on 2016-08-17.
 */
public class FishFragment extends Fragment {
    private ArrayList<String> recyclerViewTitleText;
    private ArrayList<String> recyclerViewImages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fish_fragment, container, false);

        recyclerViewTitleText = new ArrayList<String>();
        recyclerViewImages = new ArrayList<String>();

        try{
            getFishTask getFishTask = new getFishTask();
            HashMap<String, ArrayList<String>> hash = getFishTask.execute("http://45.76.96.142:3000/fish/name_img/0/21").get();
            recyclerViewTitleText = hash.get("fishName");
            recyclerViewImages = hash.get("fishImg");
            for(int i = 0; i<recyclerViewImages.size(); i++){
                if(recyclerViewImages.get(i).equals("null")){
                    recyclerViewImages.set(i, "http://cfile2.uf.tistory.com/image/2372113F56D281ED133BB3");
                }
            }
//            Log.i("images떴냐",""+recyclerViewImages);
        }catch(Exception e){
            e.printStackTrace();
        }

        Log.i("recyclerViewTitleText떳냐",""+recyclerViewTitleText);
        AutoCompleteTextView auto = (AutoCompleteTextView)rootView.findViewById(R.id.auto);
        ImageButton searchBt = (ImageButton)rootView.findViewById(R.id.searchbt);
        final String[] titleTextArray = recyclerViewTitleText.toArray(new String[recyclerViewTitleText.size()]);
//        Log.i("titleTextArray떴냐",""+titleTextArray[0]);
        ArrayAdapter<String> adWord = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_dropdown_item_1line, titleTextArray);
        auto.setAdapter(adWord);
        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                for(int i = 0; i<titleTextArray.length; i++){
                    if(parent.getItemAtPosition(position).toString().equals(recyclerViewTitleText.get(i))){
                        Intent(i);
                    }
                }

            }
        });


        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<AndroidVersion> av = prepareData();
        AndroidDataAdapter_F mAdapter = new AndroidDataAdapter_F(getActivity().getApplicationContext(), av, recyclerViewImages, recyclerViewTitleText);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {

                        switch (i) {
                            case 0:
                                Intent intent = new Intent(getActivity().getApplicationContext(),Fish_Content.class);
                                startActivity(intent);
                            case 1:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 3:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 4:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 5:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 6:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 7:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 8:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 9:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 10:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                            case 11:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                })
        );
        return rootView;
    }

    private ArrayList<AndroidVersion> prepareData() {

        ArrayList<AndroidVersion> av = new ArrayList<>();
        for (int i = 0; i < recyclerViewTitleText.size(); i++) {
            AndroidVersion mAndroidVersion = new AndroidVersion();
            mAndroidVersion.setAndroidVersionName(recyclerViewTitleText);
            mAndroidVersion.setrecyclerViewImage(recyclerViewImages);
            av.add(mAndroidVersion);
        }
        return av;
    }


    public void Intent(int i){
        Intent intent = new Intent(getActivity().getApplicationContext(),Fish_Content.class);
//        Log.i("titleText멀쩡함?",""+recyclerViewTitleText);
        intent.putExtra("titleText",recyclerViewTitleText);
        intent.putExtra("imgUrl",recyclerViewImages);
        intent.putExtra("fish", i);
        startActivity(intent);
    }
}
