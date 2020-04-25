package com.example.openhometest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScrinHomeActivity extends AppCompatActivity {
    Apartments apartments;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrin_home);
        Intent intent = getIntent();
        textView = findViewById(R.id.id_home);
        Bundle arguments = getIntent().getExtras();
        apartments = (Apartments) arguments.getSerializable(Apartments.class.getSimpleName());
        //Log.d("apart", apartments.getjson().toString());
        textView.setText(apartments.getId());
    }

    //вернутся на предыдущий экран
    public void back (View v){
        onBackPressed();
    }
    public void btnOplata (View v){
        Intent intent = new Intent(ScrinHomeActivity.this, OplataActivity.class);
        startActivity(intent);
    }
    public void CloseDogovor (View v){

    }
    public void laoadNewDogovor (View v){

    }
    public void laoadNewShablon (View v){

    }
    public void buttonGenerateDogovor (View v){
        Log.d("url" , "generate");
        String id_creator, id_respon;
        //id_creator = apartments.creator.getId();
        //id_respon = apartments.renter.getId();
        String url = "https://lydesiapi.herokuapp.com/api/pdf/"+"5e9b8733243d3433947f0421"+"/"+"5ea37a18aa959b001759afda";
        Log.d("url" , url);
        try {
            /** json object parameter**/
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("apartmentId", apartments.getId());
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("1");
            jsonArray.put("2");
            jsonArray.put("3");
            jsonArray.put("4");
            jsonObject.put( "renterFamily", jsonArray);
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
}
