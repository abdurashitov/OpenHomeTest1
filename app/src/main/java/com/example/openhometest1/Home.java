package com.example.openhometest1;

public class Home {
    private String name;
    private String value;
    private String address;

    public Home(String name, String value, String address) {
        this.name = name;
        this.value = value;
        this.address = address;
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
        return address;
    }

    public void setValue(String value) {
        this.value = value;
    }


}