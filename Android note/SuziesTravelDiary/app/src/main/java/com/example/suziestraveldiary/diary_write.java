package com.example.suziestraveldiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class diary_write extends AppCompatActivity {
    String msg = " ------- : ";
    private static final int REQUEST_CODE = 0;
    private static final int PICK_IMAGE = 5;
    private ImageView imagePut;
    public String uri_string;
    private Uri uri_uri;
    Button create2Button;

    private void setImage(Uri uri) {
        try {
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            imagePut.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private final int GALLERY_CODE=1112;


    private void selectGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_CODE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_write);
        System.out.println("Android App : On Create");
        imagePut = findViewById(R.id.image_);

        imagePut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });


        create2Button = (Button) findViewById(R.id.button3);
        create2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPost();
                Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                startActivityForResult(intent2,PICK_IMAGE);
            }
        });
    }

    public void addPost() {
        ContentValues addValues = new ContentValues();
        System.out.println("addpost 들어");
        addValues.put(MyContentProvider._TITLE, ((EditText) findViewById(R.id.title_text_)).getText().toString());
        addValues.put(MyContentProvider._DETAILS, ((EditText) findViewById(R.id.detail_text_)).getText().toString());
        addValues.put(MyContentProvider._IMAGE, uri_string);
        addValues.put(MyContentProvider._LATITUDE, ((EditText) findViewById(R.id.latitude_text_)).getText().toString());
        addValues.put(MyContentProvider._LONGITUDE, ((EditText) findViewById(R.id.longitude_text_)).getText().toString());
        getContentResolver().insert(MyContentProvider.CONTENT_URI, addValues);
        System.out.println("토스트 전");

        Toast.makeText(getBaseContext(), "Record Added", Toast.LENGTH_LONG).show();
        System.out.println("나가기 직전 s");
    }

    @Override //갤러리에서 이미지 불러온 후 행동
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_IMAGE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지뷰에 세팅
                    uri_string = BitmapToString(Bitmap.createScaledBitmap(img, 250, 250, true));
                    imagePut.setImageBitmap(img);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
  
/*
    private void sendPicture(Uri imgUri) {
        String imagePath = getRealPathFromURI(imgUri); // path 경로
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        //경로를 통해 비트맵으로 전환
        imagePut.setImageBitmap(rotate(bitmap, exifDegree));
        // 이미지 뷰에 비트맵 넣기
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap src, float degree) { // Matrix 객체 생성
        Matrix matrix = new Matrix(); // 회전 각도 셋팅 matrix.postRotate(degree); // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    private String getRealPathFromURI(Uri contentUri) {
        int column_index = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }
        return cursor.getString(column_index);
    }

*/


    /*
     * String형을 BitMap으로 변환시켜주는 함수
     * */
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /*
     * Bitmap을 String형으로 변환
     * */
    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }

    /*
     * Bitmap을 byte배열로 변환
     * */
    public static byte[] BitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        return baos.toByteArray();
    }

}