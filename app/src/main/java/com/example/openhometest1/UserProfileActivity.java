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

        w_name = findViewById(R.id._name);
        w_surname = findViewById(R.id._surname);
        w_lastname = findViewById(R.id._lastname);
        w_tel = findViewById(R.id._tel);

    }
    public void Redact(View view) {
        Intent intent = new Intent(UserProfileActivity.this, ProfileEditActivity.class);
        startActivity(intent);
    }
}
