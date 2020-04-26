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
    TextView textViewHomeTitel, id_home, textViewPrece, textViewCreater, textViewRenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrin_home);
        Intent intent = getIntent();

        textViewHomeTitel= findViewById(R.id.textViewHomeTitel);
        id_home= findViewById(R.id.id_home);
        textViewPrece= findViewById(R.id.textViewPrece);
        textViewCreater= findViewById(R.id.textViewCreater);
        textViewRenter= findViewById(R.id.textViewRenter);
        Bundle arguments = getIntent().getExtras();
        apartments = (Apartments) arguments.getSerializable(Apartments.class.getSimpleName());
        //Log.d("apart", apartments.getjson().toString());
        textViewHomeTitel.setText(apartments.title);
        id_home.setText(apartments.getId());
        textViewPrece.setText(apartments.getPrice());
        //textViewCreater.setText(apartments.creatorId);
        //textViewRenter.setText(apartments.renterId);
        Log.d("TAG", apartments.getjson2().toString());
        /*if(apartments.creator.firstName.equals("")){
            try {
                textViewCreater.setText((apartments.getJO().getJSONObject("creator").getString("firstName")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(apartments.renter.firstName.equals("")){
            textViewRenter.setText((apartments.renter.firstName+apartments.creator.lastName));
        }*/
    }
    //вернутся на предыдущий экран
    public void back (View v){
        onBackPressed();
    }
    public void btnOplata (View v){
        Intent intent = new Intent(ScrinHomeActivity.this, OplataActivity.class);
        intent.putExtra("pr", apartments.getPrice());
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
        id_creator = apartments.creatorId;
        id_respon = apartments.renterId;
        String url = "https://lydesiapi.herokuapp.com/api/pdf/"+id_creator+"/"+id_respon;
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
    public void openList(View view) {
        Intent intent = new Intent(ScrinHomeActivity.this, CheckList.class);
        startActivity(intent);
    }
}
