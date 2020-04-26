package com.example.openhometest1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class Apartments implements Serializable {
        String id="";
        String title="";
        String date= " ";
        int    price=0;
        String address="";
        String description="";
        String creatorId="";
        String renterId="";
        int    area=0;
        int    rooms=0;
        User   creator;
        User   renter;
        int    utilities =0;
/*        JSONObject jo = new JSONObject();



    public void setJO(JSONObject jo1){
       this.jo = jo1;
    }
    public JSONObject getJO(){
        return jo;
    }*/

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }


    public String getPrice() {
        return ""+price;
    }

    public String getAddress() {
        return address;
    }

    public Apartments(JSONObject jsonObject){
        try {
            this.title = jsonObject.getString("title");
            this.date = jsonObject.getString("date");
            this.price = jsonObject.getInt("price");
            this.id =jsonObject.getString("_id");
            this.address = jsonObject.getString("address");
            this.description = jsonObject.getString("description");
            this.area = jsonObject.getInt("area");
            this.rooms = jsonObject.getInt("rooms");
            this.creatorId = jsonObject.getString("creatorId");
            this.renterId = jsonObject.getString("renterId");
            this.utilities = jsonObject.getInt("utilities");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Apartments(String title, String price, String address , String area, String rooms, String utilities) {
        this.title = title;
        this.price = Integer.parseInt(price);///
        this.address = address;
        this.area = Integer.parseInt(area);//
        this.rooms = Integer.parseInt(rooms);//
        this.utilities = Integer.parseInt(utilities);//
    }

    public JSONObject getjson1(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(  "title",    this.title);
            jsonObject.put(  "price",    this.price);
            jsonObject.put(  "address",  this.address);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("1");
            jsonArray.put("2");
            jsonObject.put( "images", jsonArray);
            jsonObject.put( "description", "description");
            jsonObject.put(  "area",     this.area);
            jsonObject.put(  "utilities",this.utilities);
            jsonObject.put(  "rooms",    this.rooms);

            Log.d("JSONObject", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject;
    }
    public JSONObject getjson2(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(  "data",     this.date);
            jsonObject.put(  "address",  this.address);
            jsonObject.put(  "title",    this.title);
            jsonObject.put(  "price",    this.price);
            jsonObject.put(  "description", this.description);
            jsonObject.put(  "area",     this.area);
            jsonObject.put(  "utilities",this.utilities);
            jsonObject.put(  "rooms",    this.rooms);
            jsonObject.put(  "creator",    this.creatorId);
            jsonObject.put(  "renter",    this.renterId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject;
    }
}
