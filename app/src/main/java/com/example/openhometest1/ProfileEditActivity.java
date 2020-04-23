package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileEditActivity extends AppCompatActivity {
    EditText v_name;
    EditText v_surname;
    EditText v_lastname;
    EditText v_tel;

    private ImageView imageView;
    private final int Pick_image = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Pick_image);
            }
        });

        v_name = findViewById(R.id._name);
        v_surname = findViewById(R.id._surname);
        v_lastname = findViewById(R.id._lastname);
        v_tel = findViewById(R.id._tel);
    }

    ///Обрабатываем результат выбора в галерее:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case Pick_image:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    ///

    public void onClick(View view) { //передает значения на другой экран, нужно их связать
        String V_name = v_name.getText().toString();
        String V_surname = v_surname.getText().toString();
        String V_lastname = v_lastname.getText().toString();
        String V_tel = v_tel.getText().toString();
        if(V_name.isEmpty() || V_surname.isEmpty() || V_tel.isEmpty()) { //проверка введены ли данные
            Toast toast = Toast.makeText(getApplicationContext(), "введите данные!", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Intent profile = new Intent(ProfileEditActivity.this, ContactsContract.Profile.class);
            profile.putExtra("v_name", V_name);
            profile.putExtra("v_surname", V_surname);
            profile.putExtra("v_lastname", V_lastname);
            profile.putExtra("v_tel", V_tel);
            profile.putExtra("Pick_image", Pick_image);
            startActivity(profile);
        }
    }
}
