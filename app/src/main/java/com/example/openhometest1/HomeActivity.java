package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<Home> states = new ArrayList();
    ListView countriesList;
    private static final String url = "https://lydesiapi.herokuapp.com/api/apartments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                                String title = itemObj.getString("title");
                                String price = itemObj.getString("price");
                                Log.d("msg", title + " " + price);
                                //JSONObject itemObj1 = itemObj.getJSONObject("location");
                                //String adress = itemObj1.getString("lat")+" "+itemObj1.getString("lng");
                                states.add(new Home (title, price, "demo"));
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
}
