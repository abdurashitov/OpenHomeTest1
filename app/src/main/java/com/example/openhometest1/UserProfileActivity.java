package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {
    TextView w_name, w_surname , w_lastname, w_tel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


    }

    public void back (View v){
        onBackPressed();
    }

    public void setting (View v){
        Intent intent = new Intent(UserProfileActivity.this, ProfileEditActivity.class);
        startActivity(intent);
    }
}
