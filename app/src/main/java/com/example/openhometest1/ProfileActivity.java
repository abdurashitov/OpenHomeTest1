package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    EditText name, firstName, lastName, data;
    Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.editText3);
        firstName = findViewById(R.id.editText2);
        lastName = findViewById(R.id.editText4);
        data = findViewById(R.id.editText5);
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        intent1 =  getIntent();
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
    public void register(View v) {

    }

    public void setImage(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        CircleImageView imageView = (CircleImageView) findViewById(R.id.circleImageView);

        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
        }
    }
    //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZWEzN2ExOGFhOTU5YjAwMTc1OWFmZGEiLCJpYXQiOjE1ODc3OTU4OTh9.2gc7o7G9ehiogLkSCS2uqwiVeNhjAPW9cxQ3G5wnBqM
    public void continue_btn(View v) {
        String name_str="name", firstName_str="firstName", lastName_str="lastName", data_str="data", phone_str="";
        name_str = name.getText().toString();
        firstName_str = firstName.getText().toString();
        data_str = data.getText().toString();
        User user = new User(name_str, firstName_str, data_str );
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        //intent.putExtra("obg", user);
        //postUser(user);
        startActivity(intent);
    }
    public void postUser(User user){
        try {
            /** json object parameter**/
            String url = "https://lydesiapi.herokuapp.com/api/user/update/";
            final JSONObject jsonObject = user.getJson1();
            Log.e("jsonObject params", jsonObject.toString() + "");
            /**URL */
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.e("res", "Response " + jsonObject.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("TAG1", volleyError.toString());
                }
            })
            {
                //
                @Override
                public Map<String, String> getHeaders()  {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTliODczMzI0M2QzNDMzOTQ3ZjA0MjEiLCJpYXQiOjE1ODc4NTY1NzV9.ogNpU668A1I1GDmqs5B1_4zlodf6TwR7uMvnXC6z6MY");
                    return headers;
                }
                ////
                @Override
                public byte[] getBody() {

                    try {
                        Log.i("json", jsonObject.toString());
                        return jsonObject.toString().getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };;
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);

        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }
}