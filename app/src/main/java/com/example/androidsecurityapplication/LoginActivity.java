package com.example.androidsecurityapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidsecurityapplication.CameraFunctionality.CamreaActivity;
import com.example.androidsecurityapplication.UnauthorizedPersonsInfo.UnauthorizedPersonsInfoActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private CardView loginButton;
    private EditText userNameEditText, passwordEditText;
    private Button autoClick;
    private int noOfSecond = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadContents();
    }

    private void loadContents() {

        this.loginButton = findViewById(R.id.loginButton);
        this.userNameEditText = findViewById(R.id.userNameEditText);
        this.passwordEditText = findViewById(R.id.passwordEditText);
        this.autoClick=findViewById(R.id.autoClick);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                autoClick.performClick();
            }
        }, noOfSecond * 1000);
        autoClick.setOnClickListener(new View.OnClickListener() {
            PermissionListener permissionListener=new PermissionListener() {
                @Override
                public void onPermissionGranted() {

                }

                @Override
                public void onPermissionDenied(List<String> deniedPermissions) {
                    Toast.makeText(LoginActivity.this,"Application Not Run",Toast.LENGTH_SHORT).show();
                }
            };

            @Override
            public void onClick(View v) {
                TedPermission.with(LoginActivity.this)
                        .setPermissionListener(permissionListener)
                        .setPermissions(Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
            }
        });


    }

    public void login() {
        String userName = userNameEditText.getText().toString();
        String passWord = passwordEditText.getText().toString();

        if (userName.equals("admin") && passWord.equals("1111")) {
            Intent intent = new Intent(LoginActivity.this, UnauthorizedPersonsInfoActivity.class);
            startActivity(intent);
        } else {
            Intent cameraIntent = new Intent(LoginActivity.this, CamreaActivity.class);
            startActivity(cameraIntent);

        }
    }
}
