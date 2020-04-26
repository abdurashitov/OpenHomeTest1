package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class OplataActivity extends AppCompatActivity {
    int summa;
    private String pr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oplata);
        Intent intent = getIntent();
        pr1 = intent.getStringExtra("pr");
    }

    String url = "https://lydesiapi.herokuapp.com/api/pay";
    public void chexkList(View v){
        load();
    }
/*            "_id": "5ea4d79bdf6200363cf1b788",
                    "checkNumber": "b1065fa458",
                    "date": "2020.04.26",
                    "time": "03:36:43",*/
    private void load(){
        try {
            /** json object parameter**/
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("price",pr1 );
            jsonObject.put("inn","123" );
            Log.e("jsonObject params", jsonObject.toString() + "");
            /** URL **/
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
            }){
                //
                @Override
                public Map<String, String> getHeaders()  {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTliODczMzI0M2QzNDMzOTQ3ZjA0MjEiLCJpYXQiOjE1ODc4NTY1NzV9.ogNpU668A1I1GDmqs5B1_4zlodf6TwR7uMvnXC6z6MY");
                    return headers;
                }
                ////
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            Log.d("TAG", e.toString());
        }
        catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }
}
