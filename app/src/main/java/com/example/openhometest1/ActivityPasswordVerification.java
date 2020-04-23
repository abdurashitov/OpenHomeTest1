package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityPasswordVerification extends AppCompatActivity {

    EditText editCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_verification);
        editCode = findViewById(R.id.code);
    }
    public void checkCode(View v){
        String code = editCode.getText().toString();
        if (code.length() == 6){
            //отправляем на сервер для проверки
            Intent intent = new Intent(ActivityPasswordVerification.this, ProfileActivity.class);
            startActivity(intent);
        }
    }
}
