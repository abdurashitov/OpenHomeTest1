package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class AddHomeActivity extends AppCompatActivity {
    EditText add_home_data_oplata,add_home_predoplata,add_home_summa,add_home_price,add_home_arenda,add_home_water,add_home_gaz,add_home_renter,add_home_vid,add_home_adress,add_home_editText, add_comnat;
    String str_add_home_data_oplata;
    String str_add_home_predoplata;
    String str_add_home_summa;
    String str_add_home_price;
    String str_add_home_arenda;
    String str_add_home_water;
    String str_add_comnat;
    String str_add_home_gaz;
    //String str_add_home_renter;
    String str_add_home_vid;
    String str_add_home_adress;
    String str_add_home_editText;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home);

        add_home_data_oplata = findViewById(R.id.add_home_data_oplata);
        add_home_predoplata = findViewById(R.id.add_home_predoplata);
        add_home_summa = findViewById(R.id.add_home_summa);
        add_home_price = findViewById(R.id.add_home_price);
        add_comnat = findViewById(R.id.add_comnat);
        add_home_arenda = findViewById(R.id.add_home_arenda);
        add_home_water = findViewById(R.id.add_home_water);
        add_home_gaz = findViewById(R.id.add_home_gaz);
        //add_home_renter = findViewById(R.id.add_home_renter);
        add_home_vid = findViewById(R.id.add_home_vid);
        add_home_adress = findViewById(R.id.add_home_adress);
        add_home_editText = findViewById(R.id.add_home_editText);
    }
    public void add_new_home(View view){
        str_add_home_data_oplata = add_home_data_oplata.getText().toString();
        str_add_home_predoplata = add_home_predoplata.getText().toString();
        str_add_home_summa = add_home_summa.getText().toString();
        str_add_home_price =  add_home_price.getText().toString();
        str_add_home_arenda = add_home_arenda.getText().toString();
        str_add_comnat = add_comnat.getText().toString();
        str_add_home_water = add_home_water.getText().toString();
        str_add_home_gaz = add_home_gaz.getText().toString();
        //str_add_home_renter = add_home_renter.getText().toString();
        str_add_home_vid = add_home_vid.getText().toString();
        str_add_home_adress = add_home_adress.getText().toString();
        str_add_home_editText = add_home_editText.getText().toString();
        User user1 = new User("alex", "abdurashitov", "03.07.2020");
        Apartments apartments = new Apartments(str_add_home_editText, str_add_home_price,str_add_home_adress,str_add_home_vid,str_add_comnat,str_add_home_water);
        postUser(apartments);
    }

    public void back(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void postUser(Apartments apartments){
        try {
            Log.d("qrfdg", "post");
            /** json object parameter**/
            String url = "https://lydesiapi.herokuapp.com/api/apartments";
            final JSONObject jsonObject = apartments.getjson1();
            Log.e("jsonObject params", jsonObject.toString() + "");
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
            })
            {
                //
                @Override
                public Map<String, String> getHeaders()  {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTliODczMzI0M2QzNDMzOTQ3ZjA0MjEiLCJpYXQiOjE1ODc4NTY1NzV9.ogNpU668A1I1GDmqs5B1_4zlodf6TwR7uMvnXC6z6MY");
                    return headers;
                }
            };;
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);

        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }
}
