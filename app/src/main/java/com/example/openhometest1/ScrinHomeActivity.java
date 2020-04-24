package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ScrinHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrin_home);
    }

    //вернутся на предыдущий экран
    public void back (View v){
        onBackPressed();
    }

}
