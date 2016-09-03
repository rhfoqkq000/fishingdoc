package com.example.kim.fishingdoc.fish;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.fish.SQLite.DBHelper;
import com.google.common.io.Files;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kim on 2016-08-18.
 */
public class Fish_Content extends AppCompatActivity {
    int id;
<<<<<<< HEAD
    private static final int SELECT_PHOTO = 100;
    ImageView imv;
    int chkImg = 0;
    Uri selectedImage;
    File imgFile;
    String email;
    //    String fish_id2 = String.valueOf(fish);
=======
    private FloatingActionButton fab_add;
    private FloatingActionButton fab_minus;
//    String fish_id2 = String.valueOf(fish);
>>>>>>> 6bfd2ccc95d212bc5e3f3be52fe0e49d45845944
ArrayList<String> idList = new ArrayList<String>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent){
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
//                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    imv.setImageURI(selectedImage);// To display selected image in image view
                    chkImg = 1;
                    Uri selectedImageUri = imageReturnedIntent.getData();
                    String imagepath = getRealPathFromURI(selectedImageUri);
                    imgFile = new File(imagepath);
                    Log.i("imgFile만들기^0^",""+imgFile);
                    uploadFile(idList.get(id), email);
                }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fish_content);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "crud.db", null, 2);
        Log.i("안전해욤","1");
        ArrayList<String> distin = new ArrayList<String>();
        final int fish, fish_id;
        HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();

        final Intent intent = getIntent();
        final ArrayList<String> titleText = intent.getExtras().getStringArrayList("titleText");
        final ArrayList<String> imgUrl = intent.getExtras().getStringArrayList("imgUrl");
        id = intent.getExtras().getInt("id");
        fish_id = intent.getExtras().getInt("fish_id");
        idList = intent.getExtras().getStringArrayList("idList");
<<<<<<< HEAD
        email = intent.getExtras().getString("email").substring(0, 3)+"***";
        Log.e("email떴냥",""+email);
=======
>>>>>>> 6bfd2ccc95d212bc5e3f3be52fe0e49d45845944
//        Log.i("idList뭐잇냥",""+idList.get(id));
        distin = intent.getExtras().getStringArrayList("distin");
        final TextView content_text = (TextView)findViewById(R.id.content_text);
        content_text.setText(titleText.get(id));
        Log.i("안전해욤","2");

        imv = (ImageView)findViewById(R.id.insert_image);
        Picasso.with(getApplicationContext()).load(imgUrl.get(id)).into(imv);
        Log.i("안전해욤","3.-1");




        final android.support.design.widget.FloatingActionButton fab_add = (android.support.design.widget.FloatingActionButton)findViewById(R.id.fab_add);
        final android.support.design.widget.FloatingActionButton fab_minus = (android.support.design.widget.FloatingActionButton)findViewById(R.id.fab_minus);

        for(int i = 0; i<dbHelper.getResult2().get("nameList").size(); i++){
            Log.i("안전해욤",""+dbHelper.getResult2().get("nameList").get(i));
            if(content_text.getText().toString().equals(dbHelper.getResult2().get("nameList").get(i))){
                fab_add.setVisibility(View.INVISIBLE);
                fab_minus.setVisibility(View.VISIBLE);

            }else{
                fab_add.setVisibility(View.VISIBLE);
                fab_minus.setVisibility(View.INVISIBLE);
            }
        }

        assert fab_add != null;
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for(int i = 0; i<dbHelper.getResult2().get("nameList").size(); i++){
//                    if(content_text.getText().toString().equals(dbHelper.getResult2().get("nameList").get(i))){
////                        fab_add.setVisibility(View.INVISIBLE);
//                        Log.i("if트루","1");
////                        fab_minus.setVisibility(View.VISIBLE);
//                    }else{
//                        Log.i("if노트루","2");
////                        fab_add.setVisibility(View.VISIBLE);
////                        fab_minus.setVisibility(View.INVISIBLE);
//                    }
//                }
                fab_add.setVisibility(View.INVISIBLE);
                fab_minus.setVisibility(View.VISIBLE);
//                final DBHelper dbHelper = new DBHelper(getApplicationContext(), "crud.db", null, 4);//흑흑씨발이거야이거
////                  Toast.makeText(getApplicationContext(),""+dbHelper.getResult(), Toast.LENGTH_SHORT).show();
//                dbArticle = dbHelper.getResult();

                String goodid = idList.get(id);
                String name = content_text.getText().toString();
                String imgurl = imgUrl.get(id);
                Log.i("goodid,name,imgurl",""+goodid+","+name+","+imgurl);
                dbHelper.insert(goodid, name, imgurl);
                Toast.makeText(getApplicationContext(),"체크리스트에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                Log.i("result^0^",""+dbHelper.getResult2());
            }
        });

        assert fab_minus != null;
        fab_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_add.setVisibility(View.VISIBLE);
                fab_minus.setVisibility(View.INVISIBLE);
                String Badid = idList.get(id);
                int result = dbHelper.delete(Badid);

                if(result==1){
                    Toast.makeText(getApplicationContext(),"체크리스트에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    int resultCode = 2;
                    setResult(resultCode);
                    finish();

                }
//                Log.i("resultㅠㅠ","1"+dbHelper.getResult());
            }
        });


        Button image_add = (Button)findViewById(R.id.image_add);
        assert image_add != null;
        image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

<<<<<<< HEAD


        final TextView tvHistory = (TextView)findViewById(R.id.tvHistory);
        Log.i("안전해욤","3.1");

        getHistory getHistory = new getHistory(fish_id, tvHistory);
        Log.i("안전해욤","3.2");
        getHistory.execute("http://45.32.61.201:3000/fish");
        Log.i("안전해욤","4");

//
//        FishFragment fishFragment = new FishFragment();
//        ArrayList<String> fish_idList = fishFragment.getFish_idList();
//        for(int i = 0; i<fishFragment.getFish_idList().size(); i++){
//
//        }

=======
>>>>>>> 6bfd2ccc95d212bc5e3f3be52fe0e49d45845944
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.fish_toolbar);
        setSupportActionBar(toolbar1);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final EditText edit_distin = (EditText)findViewById(R.id.edit_distin);
        final TextView text_distin = (TextView)findViewById(R.id.text_distin);
//        text_distin.setText("특징 메롱메롱메롱!");
        final EditText edit_habit = (EditText)findViewById(R.id.edit_habit);
        final TextView text_habit = (TextView)findViewById(R.id.text_habit);
        final EditText edit_live = (EditText)findViewById(R.id.edit_live);
        final TextView text_live = (TextView)findViewById(R.id.text_live);
        final EditText edit_bait = (EditText)findViewById(R.id.edit_bait);
        final TextView text_bait = (TextView)findViewById(R.id.text_bait);
        final EditText edit_catched = (EditText)findViewById(R.id.edit_catched);
        final TextView text_catched = (TextView)findViewById(R.id.text_catched);
        Log.i("안전해욤","5");

//        Log.i("distin.get(id)떴냐",""+distin.get(id));
//        text_distin.setText(distin.get(id));
//        edit_distin.setText(distin.get(id));
        try{
            getFishTask getFishTask = new getFishTask();
            hash = getFishTask.execute("http://45.32.61.201:3000/fish").get();

//            Retrofit client = new Retrofit.Builder().baseUrl("http://45.32.61.201:3000/").addConverterFactory(GsonConverterFactory.create()).build();
//            getFish service = client.create(getFish.class);
//            Call<Repo> call = service.repo();
//            call.enqueue(new Callback<Repo>() {
//                @Override
//                public void onResponse(Response<Repo> response) {
//                    if (response.isSuccess()) {
//                        Repo repo = response.body();
////                    if(String.valueOf(repo.getResult()).equals("error")){
////
////                    }
////                        tem.setText(repo.getTide_body().get(1).getDate());
//                        Log.i("???????????????",""+repo.getDoc().get(0));
//                    } else {
//                        Log.i("조졌따","1");
//                    }
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    Log.i("조졌따","2");
//                }
//            });



//            Log.i("hash뜨긴떴냐",""+hash);

//            Log.i("hash.get이다데스",""+hash.get("distin"));
            text_distin.setText(hash.get("distin").get(id));
            edit_distin.setText(hash.get("distin").get(id));
            text_habit.setText(hash.get("habit").get(id));
            edit_habit.setText(hash.get("habit").get(id));
            text_live.setText(hash.get("live").get(id));
            edit_live.setText(hash.get("live").get(id));
            text_bait.setText(hash.get("bait").get(id));
            edit_bait.setText(hash.get("bait").get(id));
            text_catched.setText(hash.get("catched").get(id));
            edit_catched.setText(hash.get("catched").get(id));
//            writeFishTask writeFishTask = new writeFishTask(idList.get(id), );


//            resetFish resetFish = new resetFish(idList, email);
////            Log.i("distin이 왜 널이냐","1111"+text_distin.getText().toString());
//        resetFish.execute("http://45.32.61.201:3000/fish/");
//        Log.i("resetFish.execute끝^^","1");
        }catch(Exception e){
            e.printStackTrace();
        }

        //floating icon
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_minus = (FloatingActionButton) findViewById(R.id.fab_minus);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_add.setVisibility(View.INVISIBLE);
                fab_minus.setVisibility(View.VISIBLE);
            }
        });
        fab_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_add.setVisibility(View.VISIBLE);
                fab_minus.setVisibility(View.INVISIBLE);
            }
        });

        final Button button_add_distin = (Button)findViewById(R.id.button_add_distin);
        final Button button_save_distin = (Button)findViewById(R.id.button_save_distin);

        //추가 버튼 눌렀을 때
        button_add_distin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_distin.setVisibility(View.VISIBLE);
                text_distin.setVisibility(View.INVISIBLE);
                button_add_distin.setVisibility(View.INVISIBLE);
                button_save_distin.setVisibility(View.VISIBLE);

//                String temp = (String)text_distin.getText();
//                edit_distin.setText(temp);
            }
        });

        //저장 버튼 눌렀을 때
        button_save_distin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_distin.setVisibility(View.INVISIBLE);
                text_distin.setVisibility(View.VISIBLE);
                button_add_distin.setVisibility(View.VISIBLE);
                button_save_distin.setVisibility(View.INVISIBLE);
                EditFish EditFish = new EditFish(idList.get(id), "distin", edit_distin.getText().toString(), fish_id, email, tvHistory);
                EditFish.execute("http://45.32.61.201:3000/fish/");
                text_distin.setText(edit_distin.getText().toString());
            }
        });



//        text_habit.setText("습성~~");

        final Button button_add_habit = (Button)findViewById(R.id.button_add_habit);
        final Button button_save_habit = (Button)findViewById(R.id.button_save_habit);

        //추가 버튼 눌렀을 때
        button_add_habit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_habit.setVisibility(View.VISIBLE);
                text_habit.setVisibility(View.INVISIBLE);
                button_add_habit.setVisibility(View.INVISIBLE);
                button_save_habit.setVisibility(View.VISIBLE);

//                String temp = (String)text_habit.getText();
//                edit_habit.setText(temp);
//                writeFishTask writeFishTask = new writeFishTask(id+1, "habit", edit_habit.getText().toString(), text_distin, edit_distin);
//                writeFishTask.execute("http://45.32.61.201:3000/fish/");
//                text_habit.setText(edit_habit.getText().toString());
            }
        });

        //저장 버튼 눌렀을 때
        button_save_habit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_habit.setVisibility(View.INVISIBLE);
                text_habit.setVisibility(View.VISIBLE);
                button_add_habit.setVisibility(View.VISIBLE);
                button_save_habit.setVisibility(View.INVISIBLE);

                EditFish EditFish = new EditFish(idList.get(id), "habit", edit_habit.getText().toString(), fish_id, email, tvHistory);
                EditFish.execute("http://45.32.61.201:3000/fish/");
                text_habit.setText(edit_habit.getText().toString());
            }
        });

//        text_live.setText("서식지");

        final Button button_add_live = (Button)findViewById(R.id.button_add_live);
        final Button button_save_live = (Button)findViewById(R.id.button_save_live);

        //추가 버튼 눌렀을 때
        button_add_live.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_live.setVisibility(View.VISIBLE);
                text_live.setVisibility(View.INVISIBLE);
                button_add_live.setVisibility(View.INVISIBLE);
                button_save_live.setVisibility(View.VISIBLE);

                String temp = (String)text_live.getText();
                edit_live.setText(temp);
            }
        });

        //저장 버튼 눌렀을 때
        button_save_live.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_live.setVisibility(View.INVISIBLE);
                text_live.setVisibility(View.VISIBLE);
                button_add_live.setVisibility(View.VISIBLE);
                button_save_live.setVisibility(View.INVISIBLE);
//                String fish_id = fish+"3";

                EditFish EditFish = new EditFish(idList.get(id), "live", edit_live.getText().toString(), fish_id, email, tvHistory);
                EditFish.execute("http://45.32.61.201:3000/fish/");
                text_live.setText(edit_live.getText().toString());
            }
        });


//        text_bait.setText("미끼");

        final Button button_add_bait = (Button)findViewById(R.id.button_add_bait);
        final Button button_save_bait = (Button)findViewById(R.id.button_save_bait);

        //추가 버튼 눌렀을 때
        button_add_bait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_bait.setVisibility(View.VISIBLE);
                text_bait.setVisibility(View.INVISIBLE);
                button_add_bait.setVisibility(View.INVISIBLE);
                button_save_bait.setVisibility(View.VISIBLE);

                String temp = (String)text_bait.getText();
                edit_bait.setText(temp);
            }
        });

        //저장 버튼 눌렀을 때
        button_save_bait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_bait.setVisibility(View.INVISIBLE);
                text_bait.setVisibility(View.VISIBLE);
                button_add_bait.setVisibility(View.VISIBLE);
                button_save_bait.setVisibility(View.INVISIBLE);
                EditFish EditFish = new EditFish(idList.get(id), "bait", edit_bait.getText().toString(), fish_id, email, tvHistory);
                EditFish.execute("http://45.32.61.201:3000/fish/");
                text_bait.setText(edit_bait.getText().toString());
            }
        });


//        text_catched.setText("잡는방법");

        final Button button_add_catched = (Button)findViewById(R.id.button_add_catched);
        final Button button_save_catched = (Button)findViewById(R.id.button_save_catched);

        //추가 버튼 눌렀을 때
        button_add_catched.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_catched.setVisibility(View.VISIBLE);
                text_catched.setVisibility(View.INVISIBLE);
                button_add_catched.setVisibility(View.INVISIBLE);
                button_save_catched.setVisibility(View.VISIBLE);

                String temp = (String)text_catched.getText();
                edit_catched.setText(temp);
            }
        });

        //저장 버튼 눌렀을 때
        button_save_catched.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_catched.setVisibility(View.INVISIBLE);
                text_catched.setVisibility(View.VISIBLE);
                button_add_catched.setVisibility(View.VISIBLE);
                button_save_catched.setVisibility(View.INVISIBLE);
                EditFish EditFish = new EditFish(idList.get(id), "catched", edit_catched.getText().toString(), fish_id, email, tvHistory);
                EditFish.execute("http://45.32.61.201:3000/fish/");
                text_catched.setText(edit_catched.getText().toString());
            }
        });
    }



    private void uploadFile(String new_Id, String email) {
        Log.i("ImgFile이름모양1",""+imgFile);
        // create upload service client
        Service service =
                ServiceGenerator.createService(Service.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/"+ Files.getFileExtension(imgFile.getName())),imgFile);
        Log.i("ImgFile이름모양2",""+imgFile);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", imgFile.getName(), requestFile);

        // finally, execute the request
        String fish_id = idList.get(id);
        String filename = imgFile.getName()+email;//원본이름+이메일
        Call<ResponseBody> call = service.upload(fish_id,filename,body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }



    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}


