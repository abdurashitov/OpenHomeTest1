package com.example.openhometest1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    public String firstName="";
    public String lastName="";
    public boolean moderated=false;
    public String photoUrl="";
    public String ID="";
    Contact contact;
    public String phoneNumber="";
    public String dateAndBirthPlace="";
    public String passportSeriesAndNumber="";
    public String  passportIssuedBy="";
    public String passportIssuedDate="";
    public String passportIssuedAddress="";
    public String date="";


    public User(String firstName, String last_name , String date) {
        this.firstName = firstName;
        this.lastName = last_name;
        this.moderated = true;
        this.date = date;
        this.photoUrl = "photoUrl";
        this.contact = new Contact();
        this.dateAndBirthPlace = "dateAndBirthPlace";
        this.passportSeriesAndNumber = "passportSeriesAndNumber";
        this.passportIssuedBy = "passportIssuedBy";
        this.passportIssuedDate = "passportIssuedDate";
        this.passportIssuedAddress = "stringpassportIssuedAddress";
    }
    public String getId(){
        return ID;
    }
    public User(JSONObject jsonObject) {
        try {
            this.firstName = jsonObject.getString("firstName");
            this.lastName = jsonObject.getString("last_name");
            this.moderated = jsonObject.getBoolean("moderated");
            this.date = jsonObject.getString("date");;
            this.ID = jsonObject.getString("_id");
            this.photoUrl = "photoUrl";
            this.contact = new Contact();
            this.dateAndBirthPlace = jsonObject.getString("dateAndBirthPlace");
            this.passportSeriesAndNumber = jsonObject.getString("passportSeriesAndNumber");
            this.passportIssuedBy = jsonObject.getString("passportIssuedBy");
            this.passportIssuedDate = jsonObject.getString("passportIssuedDate");
            this.passportIssuedAddress = jsonObject.getString("stringpassportIssuedAddress");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public User(String firstName, String last_name , String date, String id) {
        this.firstName = firstName;
        this.lastName = last_name;
        this.moderated = true;
        this.date = date;
        this.ID = id;
        this.photoUrl = "photoUrl";
        this.contact = new Contact();
        this.dateAndBirthPlace = "dateAndBirthPlace";
        this.passportSeriesAndNumber = "passportSeriesAndNumber";
        this.passportIssuedBy = "passportIssuedBy";
        this.passportIssuedDate = "passportIssuedDate";
        this.passportIssuedAddress = "stringpassportIssuedAddress";
    }

    public User(String firstName, String last_name, boolean moderated, String photoUrl, Contact contact, String phoneNumber, String dateAndBirthPlace, String passportSeriesAndNumber, String passportIssuedBy, String passportIssuedDate, String stringpassportIssuedAddress, String date) {
        this.firstName = firstName;
        this.lastName = last_name;
        this.moderated = moderated;
        this.photoUrl = photoUrl;
        this.contact = contact;
        this.phoneNumber = phoneNumber;
        this.dateAndBirthPlace = dateAndBirthPlace;
        this.passportSeriesAndNumber = passportSeriesAndNumber;
        this.passportIssuedBy = passportIssuedBy;
        this.passportIssuedDate = passportIssuedDate;
        this.passportIssuedAddress = stringpassportIssuedAddress;
        this.date = date;
    }

    public JSONObject getJson(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstName",  this.firstName);
            jsonObject.put("lastName",  this.lastName);
            jsonObject.put("moderated",  this.moderated);
            jsonObject.put("photoUrl",  this.photoUrl);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(0, this.contact.emal);
            jsonArray.put(1, this.contact.telegram);
            jsonObject.put("contacts",jsonArray);
            jsonObject.put("phoneNumber",  this.phoneNumber);
            jsonObject.put("dateAndBirthPlace",  this.dateAndBirthPlace);
            jsonObject.put("passportSeriesAndNumber",  this.passportSeriesAndNumber);
            jsonObject.put("passportIssuedBy",  this.passportIssuedBy);
            jsonObject.put("passportIssuedDate",  this.passportIssuedDate);
            jsonObject.put("passportIssuedAddress",  this.passportIssuedAddress);
            jsonObject.put("date",  this.date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject getJson1(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstName",  this.firstName);
            jsonObject.put("lastName",  this.lastName);
            jsonObject.put("photoUrl",  this.photoUrl);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("email", this.contact.emal);
            jsonObject1.put("telegram", this.contact.telegram);
            jsonObject.put("contacts",jsonObject1);
            jsonObject.put("dateAndBirthPlace",  this.dateAndBirthPlace);
            jsonObject.put("passportSeriesAndNumber",  this.passportSeriesAndNumber);
            jsonObject.put("passportIssuedBy",  this.passportIssuedBy);
            jsonObject.put("passportIssuedDate",  this.passportIssuedDate);
            jsonObject.put("passportIssuedAddress",  this.passportIssuedAddress);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
class Contact{
    public String emal="";
    public String telegram="";
    public Contact() {
        this.emal = "emal";
        this.telegram = "telegram";
    }

    public Contact(String emal, String telegram) {
        this.emal = emal;
        this.telegram = telegram;
    }
}
