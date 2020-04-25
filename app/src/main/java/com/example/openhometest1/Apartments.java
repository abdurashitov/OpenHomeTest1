package com.example.openhometest1;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class Apartments implements Serializable {
        String id="";
        String title="";
        String date="";
        int    price=0;
        String address="";
        String description="";
        int    area=0;
        int    rooms=0;
        User   creator;
        User   renter;
        int    utilities =0;

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }


    public String getCreaterId() {
        return creator.ID;
    }
    public String getRenterId() {
        return renter.ID;
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
            JSONObject jsonObject1 = jsonObject.getJSONObject("creator");
            this.creator = new User(jsonObject1.getString("firstName"), jsonObject1.getString("lastName"),jsonObject1.getString("date"),jsonObject1.getString("_id"));
            JSONObject jsonObject2 = jsonObject.getJSONObject("renter");
            this.renter = new User(jsonObject2.getString("firstName"), jsonObject2.getString("lastName"),jsonObject2.getString("date"),jsonObject2.getString("_id") );
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
            jsonObject.put(  "title",    this.title);
            jsonObject.put(   "data", this.data);
            jsonObject.put(  "price",    this.price);
            jsonObject.put(  "address",  this.address);
            jsonObject.put(  "area",     this.area);
            jsonObject.put(  "utilities",this.utilities);
            jsonObject.put(  "rooms",    this.rooms);
            jsonObject.put("", );

            Log.d("JSONObject", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject;
    }
}
