package com.example.androidsecurityapplication.CameraFunctionality;

import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.androidsecurityapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CamreaActivity extends AppCompatActivity {

    private Camera camera;
    private FrameLayout frameLayout;
    ShowCamera showCamera;
    CardView gobackButton;
    String imageFilePath;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        this.frameLayout = findViewById(R.id.frameLayout);
        this.camera = Camera.open(1);
        this.showCamera = new ShowCamera(this, camera);
        frameLayout.addView(showCamera);
        this.gobackButton = findViewById(R.id.gobackButton);
        final CardView takePicture = findViewById(R.id.takePicture);


        int noOfSecond = 1;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //TODO Set your button auto perform click.
                takePicture.performClick();
            }
        }, noOfSecond * 1000);


        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePic();
            }
        });

        gobackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void takePic() {
        if (camera != null) {
            camera.takePicture(null, null, mPictureCallback);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                camera.enableShutterSound(false);
            }
        }
    }

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File picture_file = null;
            try {
                picture_file = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (picture_file == null) {
                return;
            } else {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(picture_file);
                    fileOutputStream.write(data);
                    fileOutputStream.close();
                    camera.startPreview();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyy-MM-dd HH:mm   ",
                        Locale.getDefault()).format(new Date());
        String imageFileName = timeStamp;

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        imageFilePath = image.getAbsolutePath();
        return image;
    }

}