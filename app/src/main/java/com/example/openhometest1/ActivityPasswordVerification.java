package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import br.com.sapereaude.maskedEditText.MaskedEditText;

public class ActivityPasswordVerification extends AppCompatActivity {
    Intent intent, intent1;
    MaskedEditText editCode;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_verification);
        editCode = findViewById(R.id.code);
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        intent = getIntent();
        intent1 = new Intent(ActivityPasswordVerification.this, ProfileActivity.class);
        //postPhone(intent.getStringExtra("phone"));


    }
    public void back (View v){
        onBackPressed();
    }
    public void checkCode(View v){
        String code = editCode.getRawText();
        if (code.length() == 6){
            //postPhoneVerefication(intent.getStringExtra("phone"), editCode.getRawText());
            intent1.putExtra("phone",intent.getStringExtra("phone"));
            intent1.putExtra("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTc2ODdkMGRlZjQ5MTM2ZGM2YjNmOGQiLCJpYXQiOjE1ODQ5MDk3MjZ9.1s-it-HR50dr54AV72agBmYAgjWkzK3h-dA3epdMnyk");
            startActivity(intent1);
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

    public void postPhone(String number){
        try {
            /** json object parameter**/
            String url = "https://lydesiapi.herokuapp.com/api/auth/sms/request";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phoneNumber", number);
            Log.e("jsonObject params", jsonObject.toString() + "");
            /**URL */

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.e("res", "Response " + jsonObject.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("TAG1", volleyError.toString());
                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);

        } catch (JSONException e) {
            Log.d("TAG", e.toString());
        }
        catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    public void postPhoneVerefication(String number, String virefication){
        try {
            /** json object parameter**/

            String url = "https://lydesiapi.herokuapp.com/api/auth/sms/verify";
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("phoneNumber", number);
            jsonObject1.put("code", virefication);
            Log.e("jsonObject params", jsonObject1.toString() + "");
            /**URL */

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject1, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                    Log.e("res1", "Response " + jsonObject.getString("token"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    //Toast.makeText(ActivityPasswordVerification.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                    Log.e("TAG1", volleyError.toString());
                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);

        } catch (JSONException e) {
            Log.d("TAG3", e.toString());
        }
        catch (Exception e) {
            Log.e("TAG4", e.toString());
        }
    }
}


