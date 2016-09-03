package com.example.kim.fishingdoc.fish;


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

/**
 * Created by rhfoq on 2016-08-31.
 */
public class Fish_Write extends AppCompatActivity {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fish_write);

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
    }
}
