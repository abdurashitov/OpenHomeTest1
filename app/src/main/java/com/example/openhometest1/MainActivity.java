package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import br.com.sapereaude.maskedEditText.MaskedEditText;

public class MainActivity extends AppCompatActivity {

    MaskedEditText phone;
    CheckBox rule;
    Button sendNumberPhone;
    TextView alertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNumberPhone = findViewById(R.id.send_number_phone);
        phone = findViewById(R.id.phone_input);
        rule = findViewById(R.id.rule);
        alertext = findViewById(R.id.alert_text);
        //make translucent statusBar on kitkat devices
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    public void setNumber(View v){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            phone.setTextColor(this.getColor(R.color.Dark_jungle_green));
            phone.setBackgroundTintList(ColorStateList.valueOf(this.getColor(R.color.Dark_jungle_green)));
        }
    }
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);

    }
    public void checkRule(View v){
        if (rule.isChecked()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                rule.setButtonTintList(ColorStateList.valueOf(this.getColor(R.color.Dark_jungle_green)));
            }
        }
    }
    public void readRule(View v){
        Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docviewer.yandex.ru/view/319650141/?*=2DdO0cq8THFt1hawM5I19bXofqh7InVybCI6Imh0dHBzOi8vcnVzbG9nLnJ1L3NpdGVzL2RlZmF1bHQvZmlsZXMvZG93bmxvYWQtZmlsZXMvMV9ncmF6aGRhbnNraXlfa29kZWtzX3JmX2NoYXN0XzIucGRmIiwidGl0bGUiOiIxX2dyYXpoZGFuc2tpeV9rb2Rla3NfcmZfY2hhc3RfMi5wZGYiLCJub2lmcmFtZSI6dHJ1ZSwidWlkIjoiMzE5NjUwMTQxIiwidHMiOjE1ODcyOTA0MjI0NzksInl1IjoiMTA2NDc4ODE5MTU1MTgxMjk4MCIsInNlcnBQYXJhbXMiOiJsYW5nPXJ1JnRtPTE1ODcyOTAyODAmdGxkPXJ1Jm5hbWU9MV9ncmF6aGRhbnNraXlfa29kZWtzX3JmX2NoYXN0XzIucGRmJnRleHQ9JUQwJTkzJUQwJUEwJUQwJTkwJUQwJTk2JUQwJTk0JUQwJTkwJUQwJTlEJUQwJUExJUQwJTlBJUQwJTk4JUQwJTk5KyVEMCU5QSVEMCU5RSVEMCU5NCVEMCU5NSVEMCU5QSVEMCVBMSslRDAlQTAlRDAlOUUlRDAlQTElRDAlQTElRDAlOTglRDAlOTklRDAlQTElRDAlOUElRDAlOUUlRDAlOTkrJUQwJUE0JUQwJTk1JUQwJTk0JUQwJTk1JUQwJUEwJUQwJTkwJUQwJUE2JUQwJTk4JUQwJTk4KyVEMCVBNyVEMCU5MCVEMCVBMSVEMCVBMiVEMCVBQyslRDAlOTIlRDAlQTIlRDAlOUUlRDAlQTAlRDAlOTAlRDAlQUYrK3BkZiZ1cmw9aHR0cHMlM0EvL3J1c2xvZy5ydS9zaXRlcy9kZWZhdWx0L2ZpbGVzL2Rvd25sb2FkLWZpbGVzLzFfZ3JhemhkYW5za2l5X2tvZGVrc19yZl9jaGFzdF8yLnBkZiZscj0xNDYmbWltZT1wZGYmbDEwbj1ydSZzaWduPTIzZTY2ZmFiMzg1MmZjN2M1OGJhNjM0NDkxYzlhY2RlJmtleW5vPTAifQ%3D%3D&lang=ru"));
        startActivity(browseIntent);
    }
    public void SendNumber(View v){

        Toast.makeText(this, "click send", Toast.LENGTH_SHORT).show();
        String phoneNumber = phone.getRawText();
        if(phoneNumber.length()==10){
            if(rule.isChecked())
            {
                Intent intent = new Intent(MainActivity.this, ActivityPasswordVerification.class);
                intent.putExtra("phone" , "+7"+phoneNumber);
                startActivity(intent);
            }
            else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        rule.setButtonTintList(ColorStateList.valueOf(this.getColor(R.color.red_orange)));
                }
            }
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                phone.setTextColor(this.getColor(R.color.red_orange));
                phone.setBackgroundTintList(ColorStateList.valueOf(this.getColor(R.color.red_orange)));
            }
        }
    }
}
