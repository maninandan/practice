package com.mani.googlemapsapplication;

import android.app.Activity;

/**
 * Created by mani on 01-10-2016.
 */
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;

import static com.mani.googlemapsapplication.MainActivity.BrowseView;
import static com.mani.googlemapsapplication.MainActivity.browse_flag;
import static com.mani.googlemapsapplication.MainActivity.cam_flag;
import static com.mani.googlemapsapplication.MainActivity.picturePath;


public class BrowseActivity extends Activity {

   // public static boolean browse_flag = false;//declared in photo activity.java
    //public static String picturePath;  //declared in photoactivity.java
    //public static ImageView BrowseView;//declared in photoactivity.java
    private static int RESULT_LOAD_IMAGE = 1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("entered into on create function");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoactivity);

        Button buttonLoadImage = (Button) findViewById(R.id.btn_browse);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                System.out.println("::loading an image:::");

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                System.out.println("::loading an image successful i guess:::");
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            System.out.println("picturePath::::<<<<<<<<:::" + picturePath);
            cursor.close();
            browse_flag = true;
            cam_flag    = false;
            BrowseView = (ImageView) findViewById(R.id.view_photo);
            BrowseView.setImageBitmap(BitmapFactory.decodeFile(picturePath));


        }


    }

    public void redirectToHome(View v) {
        Intent redirect = new Intent(BrowseActivity.this, MainActivity.class);
        startActivity(redirect);

    }

}