package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckList extends AppCompatActivity {

    private List<CheckItem> check1 = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        load();
    }
    String url = "https://lydesiapi.herokuapp.com/api/user/checks";
    public void chexkList(View v){
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
                                CheckItem checkItem = new CheckItem();
                                checkItem.checkNumber = itemObj.getString("checkNumber");
                                checkItem.data = itemObj.getString("date");
                                checkItem.time = itemObj.getString("time");
                                checkItem.price = itemObj.getString("price");
                                Log.d("1", itemObj.getString("_id"));
                                Log.d("2", itemObj.getString("checkNumber"));
                                Log.d("3", itemObj.getString("date"));
                                Log.d("4", itemObj.getString("time"));
                                check1.add(checkItem);
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
                }){
            //
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTliODczMzI0M2QzNDMzOTQ3ZjA0MjEiLCJpYXQiOjE1ODc4NTY1NzV9.ogNpU668A1I1GDmqs5B1_4zlodf6TwR7uMvnXC6z6MY");
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void back(View view) {
        onBackPressed();
    }

    private void SetView(){
        RecyclerView mRecyclerView =findViewById(R.id.checkRecycler);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new RecyclerAdapterCheck(this, check1);
        mRecyclerView.setAdapter(mAdapter);
    }


}
