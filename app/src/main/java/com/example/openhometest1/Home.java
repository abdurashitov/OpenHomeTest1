package com.example.openhometest1;

import java.io.Serializable;

public class Home implements Serializable {
    private String name;
    private String value;
    private String data;

    public Home(String name, String value, String data) {
        this.name = name;
        this.value = value;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getAddress() {
        return data;
    }

    public void setValue(String value) {
        this.value = value;
    }


}