package com.example.rsv_hackathon.network.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthUserRequest {

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("second_name")
    private String lastName;

    @SerializedName("phone")
    private String phone;

    @SerializedName("code")
    private String code;

    public AuthUserRequest(String firstName, String lastName, String phone, String code) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.code = code;
    }

    public JsonObject toJSON(){

       JsonObject json = new JsonObject();
       json.addProperty("first_name", getFirstName());
        json.addProperty("second_name", getLastName());
        json.addProperty("phone", getPhone());
        json.addProperty("code", getCode());

        return json;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
