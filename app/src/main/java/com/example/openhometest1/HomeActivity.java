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
    Dialog addHome;
    Button connectBtn;
    EditText editTokenText;
    ImageView scanImageView, closeImage;
    TextView createHomeDialog;
    private static final String url = "https://lydesiapi.herokuapp.com/api/apartments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addHome =  new Dialog(this);
        demo();
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
    public void demo(){
        states.add(new Home ("Дом - Иванова М.А", "35.000","Оплата 10 Апреля, 2020" ));
        states.add(new Home ("Дом - Иванова М.А", "35.000","Оплата 10 Апреля, 2020" ));
        states.add(new Home ("Дом - Иванова М.А", "35.000","Оплата 10 Апреля, 2020" ));
        states.add(new Home ("Дом - Иванова М.А", "35.000","Оплата 10 Апреля, 2020" ));
        states.add(new Home ("Дом - Иванова М.А", "35.000","Оплата 10 Апреля, 2020" ));
        SetView();
    }

    public void add_new_home(View v){
        addHome.setContentView(R.layout.add_home_dialog);
        connectBtn = addHome.findViewById(R.id.dialog_btn_conect);
        editTokenText = addHome.findViewById(R.id.dialog_token_edit);
        scanImageView = addHome.findViewById(R.id.dialog_scan_image);
        closeImage = addHome.findViewById(R.id.dialog_close_image);
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
}
