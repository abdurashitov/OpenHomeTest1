package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private List<Apartments> states = new ArrayList();
    ListView countriesList;
    Dialog addHome;
    Button connectBtn;
    EditText editTokenText;
    ImageView scanImageView, closeImage;
    TextView createHomeDialog;
    private static final String url = "https://lydesiapi.herokuapp.com/api/user/5e9b8733243d3433947f0421/apartments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addHome =  new Dialog(this);
        load();

    }
    private void load(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("msg", "qwertyuiop");
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0; i<jsonArray.length(); ++i) {
                                JSONObject itemObj = jsonArray.getJSONObject(i);
                                Log.d("msg", itemObj.toString());
                                Apartments apartments = new Apartments( (JSONObject) itemObj );
                                Log.d("msg2", apartments.getjson2().toString());
                                //apartments.setJO(itemObj);
                                states.add(apartments);
                               /*
                                String title = itemObj.getString("title");
                                String price = itemObj.getString("price");
                                String data  = itemObj.getString("date");*/
                                //Log.d("msg", title + " " + price);
                            }
                            SetView();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void SetView(){
        RecyclerView mRecyclerView =findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new RecyclerAdapter(this, states);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void openUserProfile(View v) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }


    public void add_new_home(View v){
        addHome.setContentView(R.layout.add_home_dialog);
        connectBtn = addHome.findViewById(R.id.dialog_btn_conect);
        editTokenText = addHome.findViewById(R.id.dialog_token_edit);
        scanImageView = addHome.findViewById(R.id.dialog_scan_image);
        closeImage = addHome.findViewById(R.id.dialog_close_image);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postUser(editTokenText.getText().toString());
                addHome.dismiss();
            }
        });

        createHomeDialog = addHome.findViewById(R.id.dialog_create_text);
        createHomeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddHomeActivity.class);
                startActivity(intent);
            }
        });
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHome.dismiss();
            }
        });
        addHome.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addHome.show();

    }

    public void postUser(String idhome){
        try {
            /** json object parameter**/
            String url = "https://lydesiapi.herokuapp.com/api/apartments/"+idhome+"/renter/";
            final JSONObject jsonObject = new JSONObject();
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
                    headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZWEzN2ExOGFhOTU5YjAwMTc1OWFmZGEiLCJpYXQiOjE1ODc3OTU4OTh9.2gc7o7G9ehiogLkSCS2uqwiVeNhjAPW9cxQ3G5wnBqM");
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
