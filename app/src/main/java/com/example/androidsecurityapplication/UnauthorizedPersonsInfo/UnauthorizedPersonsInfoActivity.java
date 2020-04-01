package com.example.androidsecurityapplication.UnauthorizedPersonsInfo;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidsecurityapplication.R;
import java.io.File;
import java.util.ArrayList;

public class UnauthorizedPersonsInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView refImage;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unauthorized_personinfo);
        recyclerView = findViewById(R.id.infoRecyclerView);
        this.refImage=findViewById(R.id.refImage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        int noOfSecond = 1;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                refImage.performClick();
            }
        }, noOfSecond * 1000);
        refImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(new MyAdapter(UnauthorizedPersonsInfoActivity.this,getdata()));
            }
        });

    }

    private ArrayList<PictureItem> getdata() {

        ArrayList<PictureItem>pictureItems=new ArrayList<>();

        File picturefolder =getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        PictureItem pictureItem;

        if (picturefolder.exists()){
            File [] files=picturefolder.listFiles();

            for (int i=0;i<files.length;i++){
                File file=files[i];
                pictureItem=new PictureItem();
                pictureItem.setDate(file.getName());
                pictureItem.setUri(Uri.fromFile(file));

                pictureItems.add(pictureItem);
            }
        }

        return pictureItems;
    }

}
