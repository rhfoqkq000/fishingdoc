package com.example.kim.fishingdoc.fish;

<<<<<<< HEAD

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.fishingdoc.R;
import com.example.kim.fishingdoc.fish.retrofit.permission.PermissionsChecker;
import com.google.common.io.Files;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
=======
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kim.fishingdoc.R;
>>>>>>> 6bfd2ccc95d212bc5e3f3be52fe0e49d45845944

/**
 * Created by rhfoq on 2016-08-31.
 */
public class Fish_Write extends AppCompatActivity {
<<<<<<< HEAD
    private static final int SELECT_PHOTO = 100;
    ImageView insertImg;
    int chkImg = 0;
    Uri selectedImage;
    File imgFile;
    String email;
    /**
     * Permission List
     */
    private static final String[] PERMISSIONS_READ_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

    /**
     * Context Variables
     */
    Context mContext;

    /**
     * Views
     */
    View parentView;
    ImageView imageView;
    TextView textView;

    /**
     * Image path to send
     */
    String imagePath;

    /**
     *
     */
    PermissionsChecker checker;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
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
                    insertImg.setImageURI(selectedImage);// To display selected image in image view
                    chkImg = 1;
                    Uri selectedImageUri = imageReturnedIntent.getData();
                    String imagepath = getRealPathFromURI(selectedImageUri);

                    //실험용^v^
//                    Bitmap resizedBitmap = null;
//                    try{
//                        Log.i("0000000000","000000");
//                        resizedBitmap = Picasso.with(getApplicationContext()).load(selectedImageUri).resize(300, -1).get();
//                        Log.i("11111111111111111","111111111111");
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                    String new_selectedImageUri = String.valueOf(getImageUri(getApplicationContext(), resizedBitmap));
//                    Log.i("22222222222","2222222222");
                    imgFile = new File(imagepath);

                }
        }
    }

=======
    Button add_name_bt;
    Button ok_name_bt;
    EditText edit_text;
    TextView name_text;
>>>>>>> 6bfd2ccc95d212bc5e3f3be52fe0e49d45845944
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fish_write);

<<<<<<< HEAD
        mContext = getApplicationContext();
        checker = new PermissionsChecker(this);

        ArrayList<String> fish_id_list = new ArrayList<String>();

        Intent i = getIntent();
        fish_id_list = i.getExtras().getStringArrayList("fish_id");
        email = i.getExtras().getString("email");

        final ArrayList<String> resultArray = new ArrayList<String>();

        final EditText name_edit = (EditText) findViewById(R.id.name_edit);
        final EditText distin_edit = (EditText) findViewById(R.id.edit_distin);
        final EditText habit_edit = (EditText) findViewById(R.id.edit_habit);
        final EditText live_edit = (EditText) findViewById(R.id.edit_live);
        final EditText bait_edit = (EditText) findViewById(R.id.edit_bait);
        final EditText catched_edit = (EditText) findViewById(R.id.edit_catched);

        insertImg = (ImageView)findViewById(R.id.insert_image);
        insertImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("떴쪙","1");
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        Button btnSave = (Button)findViewById(R.id.button_add);
        assert btnSave != null;
        final ArrayList<String> finalFish_id_list = fish_id_list;
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultArray.add(0, name_edit.getText().toString());
                resultArray.add(1, distin_edit.getText().toString());
                resultArray.add(2, habit_edit.getText().toString());
                resultArray.add(3, live_edit.getText().toString());
                resultArray.add(4, bait_edit.getText().toString());
                resultArray.add(5, catched_edit.getText().toString());

                insertFishTask insertFishTask = new insertFishTask(resultArray, finalFish_id_list);
                String new_Id;
                try{
                    new_Id = insertFishTask.execute("http://45.32.61.201:3000/fish/").get();
//                    Log.i("떴써용",""+new_Id);
//                    Log.i("이미지바꿨냐용",""+chkImg);
                    if(chkImg == 1) {
//                        Log.i("selectedImage", "" + selectedImage);
                        uploadFile(new_Id, email);
                        chkImg = 0;
                        finish();
                    }
                }catch(Exception e){
                }



            }
        });
    }



    private void uploadFile(String new_Id, String email) {
//        Log.i("ImgFile이름모양1",""+imgFile);
        // create upload service client
        Service service =
                ServiceGenerator.createService(Service.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/"+Files.getFileExtension(imgFile.getName())),imgFile);
//        Log.i("ImgFile이름모양2",""+imgFile);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", imgFile.getName(), requestFile);

        // finally, execute the request
        String fish_id = new_Id;
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



    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
=======
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

        edit_text = (EditText)findViewById(R.id.name_edit);
        name_text = (TextView)findViewById(R.id.name_text);

        add_name_bt = (Button)findViewById(R.id.button_add_name);
        ok_name_bt = (Button)findViewById(R.id.button_ok_name);

        add_name_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setVisibility(View.VISIBLE);
                name_text.setVisibility(View.INVISIBLE);
                add_name_bt.setVisibility(View.INVISIBLE);
                ok_name_bt.setVisibility(View.VISIBLE);
            }
        });
        ok_name_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setVisibility(View.INVISIBLE);
                name_text.setVisibility(View.VISIBLE);
                add_name_bt.setVisibility(View.VISIBLE);
                ok_name_bt.setVisibility(View.INVISIBLE);

                name_text.setText(edit_text.getText());

                if(name_text.getTextSize()>0){
                    ok_name_bt.setText("확인");
                }
            }
        });
        final Button button_add_distin = (Button)findViewById(R.id.button_add_distin);
        final Button button_save_distin = (Button)findViewById(R.id.button_save_distin);

        //확인 버튼 눌렀을 때
        button_add_distin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_distin.setVisibility(View.INVISIBLE);
                text_distin.setVisibility(View.VISIBLE);
                button_add_distin.setVisibility(View.INVISIBLE);
                button_save_distin.setVisibility(View.VISIBLE);

                text_distin.setText(edit_distin.getText().toString());
//                String temp = (String)text_distin.getText();
//                edit_distin.setText(temp);
            }
        });

        //수정 버튼 눌렀을 때
        button_save_distin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_distin.setVisibility(View.VISIBLE);
                text_distin.setVisibility(View.INVISIBLE);
                button_add_distin.setVisibility(View.VISIBLE);
                button_save_distin.setVisibility(View.INVISIBLE);

            }
        });

//        text_habit.setText("습성~~");

        final Button button_add_habit = (Button)findViewById(R.id.button_add_habit);
        final Button button_save_habit = (Button)findViewById(R.id.button_save_habit);

        //확인 버튼 눌렀을 때
        button_add_habit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_habit.setVisibility(View.INVISIBLE);
                text_habit.setVisibility(View.VISIBLE);
                button_add_habit.setVisibility(View.INVISIBLE);
                button_save_habit.setVisibility(View.VISIBLE);

                text_habit.setText(edit_habit.getText().toString());
//                String temp = (String)text_habit.getText();
//                edit_habit.setText(temp);
//                writeFishTask writeFishTask = new writeFishTask(id+1, "habit", edit_habit.getText().toString(), text_distin, edit_distin);
//                writeFishTask.execute("http://45.32.61.201:3000/fish/");
//                text_habit.setText(edit_habit.getText().toString());
            }
        });

        //수정 버튼 눌렀을 때
        button_save_habit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_habit.setVisibility(View.VISIBLE);
                text_habit.setVisibility(View.INVISIBLE);
                button_add_habit.setVisibility(View.VISIBLE);
                button_save_habit.setVisibility(View.INVISIBLE);

            }
        });

//        text_live.setText("서식지");

        final Button button_add_live = (Button)findViewById(R.id.button_add_live);
        final Button button_save_live = (Button)findViewById(R.id.button_save_live);

        //추가 버튼 눌렀을 때
        button_add_live.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_live.setVisibility(View.INVISIBLE);
                text_live.setVisibility(View.VISIBLE);
                button_add_live.setVisibility(View.INVISIBLE);
                button_save_live.setVisibility(View.VISIBLE);

//                String temp = (String)text_live.getText();
//                edit_live.setText(temp);
                text_live.setText(edit_live.getText().toString());
            }
        });

        //저장 버튼 눌렀을 때
        button_save_live.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_live.setVisibility(View.VISIBLE);
                text_live.setVisibility(View.INVISIBLE);
                button_add_live.setVisibility(View.VISIBLE);
                button_save_live.setVisibility(View.INVISIBLE);
//                String fish_id = fish+"3";

            }
        });


//        text_bait.setText("미끼");

        final Button button_add_bait = (Button)findViewById(R.id.button_add_bait);
        final Button button_save_bait = (Button)findViewById(R.id.button_save_bait);

        //추가 버튼 눌렀을 때
        button_add_bait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_bait.setVisibility(View.INVISIBLE);
                text_bait.setVisibility(View.VISIBLE);
                button_add_bait.setVisibility(View.INVISIBLE);
                button_save_bait.setVisibility(View.VISIBLE);
//
//                String temp = (String)text_bait.getText();
//                edit_bait.setText(temp);
                text_bait.setText(edit_bait.getText().toString());
            }
        });

        //저장 버튼 눌렀을 때
        button_save_bait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_bait.setVisibility(View.VISIBLE);
                text_bait.setVisibility(View.INVISIBLE);
                button_add_bait.setVisibility(View.VISIBLE);
                button_save_bait.setVisibility(View.INVISIBLE);
            }
        });


//        text_catched.setText("잡는방법");

        final Button button_add_catched = (Button)findViewById(R.id.button_add_catched);
        final Button button_save_catched = (Button)findViewById(R.id.button_save_catched);

        //추가 버튼 눌렀을 때
        button_add_catched.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_catched.setVisibility(View.INVISIBLE);
                text_catched.setVisibility(View.VISIBLE);
                button_add_catched.setVisibility(View.INVISIBLE);
                button_save_catched.setVisibility(View.VISIBLE);

//                String temp = (String)text_catched.getText();
//                edit_catched.setText(temp);
                text_catched.setText(edit_catched.getText().toString());
            }
        });

        //저장 버튼 눌렀을 때
        button_save_catched.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_catched.setVisibility(View.VISIBLE);
                text_catched.setVisibility(View.INVISIBLE);
                button_add_catched.setVisibility(View.VISIBLE);
                button_save_catched.setVisibility(View.INVISIBLE);
            }
        });
>>>>>>> 6bfd2ccc95d212bc5e3f3be52fe0e49d45845944
    }
}
