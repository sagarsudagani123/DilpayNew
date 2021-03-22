package com.dilpay.app.models;

import android.content.Context;
import android.content.SharedPreferences;

public class userDetails {
    String name1, number1, wallet1;
    Boolean isLoged;
    Context context;
    String profilePicKey = "profilePicKey",lockKey="lockKey",SPinKey="SPinKey",dobKey="DOB", nameKey = "name", idKey = "ID", cityKey = "CITY", stateKey = "STATE", zipKey = "ZIP", countryKey = "COUNTRY", addressKey = "ADDRESS", numberKey = "number", walletKey = "wallet", logedKey = "isLoged", membershipKey = "membership", comissionKey = "Comission";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public userDetails(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getMembership() {
        return sharedPreferences.getString(membershipKey, "");
    }

    public void setMembership(String name) {
        editor.putString(membershipKey, name);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(nameKey, "");
    }

    public void setName(String name) {
        editor.putString(nameKey, name);
        editor.commit();
    }

    public String getNumber() {
        return sharedPreferences.getString(numberKey, "");
    }

    public void setNumber(String number) {
        editor.putString(numberKey, number);
        editor.commit();
    }

    public String getWallet() {
        return sharedPreferences.getString(walletKey, "");
    }

    public void setWallet(String wallet) {
        editor.putString(walletKey, wallet);
        editor.commit();
    }

    public String getComission() {
        return sharedPreferences.getString(comissionKey, "");
    }

    public void setComission(String wallet) {
        editor.putString(comissionKey, wallet);
        editor.commit();
    }

    public String getID() {
        return sharedPreferences.getString(idKey, "");
    }

    public void setID(String id) {
        editor.putString(idKey, id);
        editor.commit();
    }

    public String getAddress() {
        return sharedPreferences.getString(addressKey, "");
    }

    public void setAddress(String address) {
        editor.putString(addressKey, address);
        editor.commit();
    }

    public String getCity() {
        return sharedPreferences.getString(cityKey, "");
    }

    public void setCity(String city) {
        editor.putString(cityKey, city);
        editor.commit();
    }

    public String getState() {
        return sharedPreferences.getString(stateKey, "");
    }

    public void setState(String state) {
        editor.putString(stateKey, state);
        editor.commit();
    }

    public String getZip() {
        return sharedPreferences.getString(zipKey, "");
    }

    public void setZip(String city) {
        editor.putString(zipKey, city);
        editor.commit();
    }

    public String getCountry() {
        return sharedPreferences.getString(countryKey, "");
    }

    public void setCountry(String city) {
        editor.putString(countryKey, city);
        editor.commit();
    }

    public String getProfilePic() {
        return sharedPreferences.getString(profilePicKey, "");
    }

    public void setProfilePic(String city) {
        editor.putString(profilePicKey, city);
        editor.commit();
    }

    public String getDOB() {
        return sharedPreferences.getString(dobKey, "");
    }

    public void setDOB(String dob) {
        editor.putString(dobKey, dob);
        editor.commit();
    }

    public String getSPin() {
        return sharedPreferences.getString(SPinKey, "");
    }

    public void setSPin(String dob) {
        editor.putString(SPinKey, dob);
        editor.commit();
    }


    public Boolean getLoged() {
        return sharedPreferences.getBoolean(logedKey, false);
    }

    public void setLoged(Boolean loged) {
        editor.putBoolean(logedKey, loged);
        editor.commit();
    }

    public Boolean getPhoneLockEnabled() {
        return sharedPreferences.getBoolean(lockKey, false);
    }

    public void setPhoneLockEnabled(Boolean loged) {
        editor.putBoolean(lockKey, loged);
        editor.commit();
    }

    public void clearData() {
        editor.clear();
        editor.apply();
    }
}
