package com.example.kim.fishingdoc.fish;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kim.fishingdoc.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kim on 2016-08-17.
 */
public class FishFragment extends Fragment {
    private ArrayList<String> recyclerViewTitleText;
    private ArrayList<String> recyclerViewImages;
    private ArrayList<String> idList;
    private ArrayList<String> fish_id;
    private ArrayList<String> distin;
<<<<<<< HEAD

    String email = "";
=======
    private Button add_bt;
>>>>>>> 6bfd2ccc95d212bc5e3f3be52fe0e49d45845944

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fish_fragment, container, false);
<<<<<<< HEAD
        email = getArguments().getString("email");
//        Log.i("email떴냐",""+email);
//        tvEmail.setText(email.substring(0, 3)+"*** ^0^");
        Log.i("여기까진안전데스-2","뭐어쩌라고");
=======
>>>>>>> 6bfd2ccc95d212bc5e3f3be52fe0e49d45845944

        recyclerViewTitleText = new ArrayList<String>();
        recyclerViewImages = new ArrayList<String>();
        idList = new ArrayList<String>();
        fish_id = new ArrayList<String>();
        distin = new ArrayList<String>();

//        Log.i("여기까진안전데스-1","" + recyclerViewTitleText);


        try{
            getFishTask getFishTask = new getFishTask();
            HashMap<String, ArrayList<String>> hash = getFishTask.execute("http://45.32.61.201:3000/fish").get();

            Log.i("hash가져왔당",""+hash.get("fishName"));
            Log.i("hash가져왔당",""+hash.get("fishImg"));
            Log.i("hash가져왔당",""+hash.get("id"));
            Log.i("hash가져왔당",""+hash.get("fish_id"));
            Log.i("hash가져왔당",""+hash.get("distin"));

            recyclerViewTitleText = hash.get("fishName");
            recyclerViewImages = hash.get("fishImg");
//            Log.i("여기까진안전데스0","" + recyclerViewTitleText);
            idList = hash.get("id");
            fish_id = hash.get("fish_id");
            distin = hash.get("distin");

//            Log.i("여기까진안전데스1","" + recyclerViewTitleText);
//            Toast.makeText(getContext(), "여기까진안전데스1", Toast.LENGTH_SHORT).show();
//            Log.i("id떴냐고요",""+id);
//            Log.i("fish_id떴냐고요",""+fish_id);
//            Log.i("distin떴냐고요",""+distin);

            for(int i = 0; i<recyclerViewImages.size(); i++){
//                if(recyclerViewImages.get(i).equals("null")){
                if(recyclerViewImages.get(i).equals("NULL")){
                    recyclerViewImages.set(i, "http://cfile2.uf.tistory.com/image/2372113F56D281ED133BB3");
                }
            }
//            Log.i("recyclerViewImages떴냐고요",""+recyclerViewImages);
//            Log.i("recyclerViewTitle떴냐고요",""+recyclerViewTitleText);
//            Log.i("여기까진안전데스2",""+recyclerViewTitleText);

//            Log.i("images떴냐",""+recyclerViewImages);
        }catch(Exception e){
            e.printStackTrace();
        }

        final AutoCompleteTextView auto = (AutoCompleteTextView)rootView.findViewById(R.id.auto);
        ImageButton searchBt = (ImageButton)rootView.findViewById(R.id.searchbt);
        final String[] titleTextArray = recyclerViewTitleText.toArray(new String[recyclerViewTitleText.size()]);
//        Log.i("titleTextArray떴냐",""+titleTextArray[0]);
        ArrayAdapter<String> adWord = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_dropdown_item_1line, titleTextArray);
        auto.setAdapter(adWord);
//        Log.i("여기까진안전데스3",""+recyclerViewTitleText);

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
        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fishName = auto.getText().toString();
                for(int i = 0; i<recyclerViewTitleText.size(); i ++){
                    if(auto.getText().toString().equals(recyclerViewTitleText.get(i))){
                        Intent(i);
                    }else{
//                        Log.i("없는데뜨겠냐",""+fishName);
                    }
                }
            }
        });


        Button btnNew = (Button)rootView.findViewById(R.id.file_add);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alt_bld = new AlertDialog.Builder(getContext());
                alt_bld.setMessage("새 글을 작성하시겠습니까?").setCancelable(
                        false).setPositiveButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        }).setNegativeButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(getActivity().getApplicationContext(), Fish_Write.class);
                                i.putExtra("email", email);
                                i.putExtra("fish_id", fish_id);
                                startActivityForResult(i, 3);
//                Log.i("뜨긴뜸?","1");
                            }
                        });
                AlertDialog alert = alt_bld.create();
                // Title for AlertDialog
                alert.setTitle("새 글 작성하기");
                // Icon for AlertDialog
                alert.setIcon(R.drawable.icon);
                alert.show();



            }
        });



        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<AndroidVersion> av = prepareData();
        AndroidDataAdapter_F mAdapter = new AndroidDataAdapter_F(getActivity().getApplicationContext(), av, recyclerViewImages, recyclerViewTitleText);
        mRecyclerView.setAdapter(mAdapter);
//        Log.i("여기까진안전데스4",""+recyclerViewTitleText);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        Intent(i);
                    }
                })
        );

//        CheckBox checkBox = (CheckBox)rootView.findViewById(R.id.checkBox);

        //추가버튼 눌렀을 때
        add_bt = (Button)rootView.findViewById(R.id.file_add);
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Fish_Write.class);
                startActivity(intent);
            }
        });

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
        intent.putExtra("titleText", recyclerViewTitleText);
        intent.putExtra("imgUrl", recyclerViewImages);
        intent.putExtra("id", i);
        intent.putExtra("idList", idList);
        intent.putExtra("distin", distin);
        intent.putExtra("fish_id", i);
<<<<<<< HEAD
        intent.putExtra("email", email);
        startActivityForResult(intent, 3);
    }


    private void recreate() {
        Log.e("새로고오오옹침","새로로롤로로로로로고침");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        recreate();
    }
=======
        startActivity(intent);
    }
>>>>>>> 6bfd2ccc95d212bc5e3f3be52fe0e49d45845944
}
