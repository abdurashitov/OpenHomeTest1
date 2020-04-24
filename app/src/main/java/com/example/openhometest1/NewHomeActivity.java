package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
    }
    public void back (View v){
        onBackPressed();
    }
    public void next (View v){
        Intent intent = new Intent(NewHomeActivity.this, NewHomeActivity.class);
        startActivity(intent);
    }
}
