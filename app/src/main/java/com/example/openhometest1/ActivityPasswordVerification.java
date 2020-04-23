package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class ActivityPasswordVerification extends AppCompatActivity {

    MaskedEditText editCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_verification);
        editCode = findViewById(R.id.code);
    }
    public void checkCode(View v){
        String code = editCode.getRawText();
        if (code.length() == 5){
            //отправляем на сервер для проверки
            Intent intent = new Intent(ActivityPasswordVerification.this, ProfileActivity.class);
            startActivity(intent);
        }
    }
}
