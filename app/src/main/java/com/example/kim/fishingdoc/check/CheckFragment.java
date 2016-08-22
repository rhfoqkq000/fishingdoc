package com.example.kim.fishingdoc.check;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.fish.AndroidVersion;
import com.example.kim.fishingdoc.fish.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * Created by kim on 2016-08-17.
 */
public class CheckFragment extends Fragment {
//    private final String recyclerViewTitleText[] = {"fish1", "fish2", "fish3", "fish4", "fish5", "fish6", "fish7", "fish8", "fish9", "fish10", "fish11", "fish12",
//            "fish1", "fish2", "fish3", "fish4", "fish5", "fish6", "fish7", "fish8", "fish9", "fish10", "fish11", "fish12",
//            "fish1", "fish2", "fish3", "fish4", "fish5", "fish6"};
//
//    private final int recyclerViewImages[] = {R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4,
//            R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4,
//            R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4,
//            R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4, R.drawable.image4};
    private final ArrayList<String> recyclerViewTitleText = new ArrayList<String>();

    private final ArrayList<String> recyclerViewImages = new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.check_fragment, container, false);
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<AndroidVersion> av = prepareData();
        AndroidDataAdapter_C mAdapter = new AndroidDataAdapter_C(getActivity().getApplicationContext(), av);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        switch (i) {
                            case 0:
                                Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                                break;
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
}