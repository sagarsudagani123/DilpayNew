package com.yashswi.dilpay.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class userDetails {
    String name1,number1,wallet1;
    Boolean isLoged;
    Context context;
    String nameKey="name",numberKey="number",walletKey="wallet",logedKey="isLoged",membershipKey="membership",comissionKey="Comission";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public userDetails(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public String getMembership() {
        return sharedPreferences.getString(membershipKey,"");
    }

    public void setMembership(String name) {
        editor.putString(membershipKey,name);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(nameKey,"");
    }

    public void setName(String name) {
        editor.putString(nameKey,name);
        editor.commit();
    }

    public String getNumber() {
        return sharedPreferences.getString(numberKey,"");
    }

    public void setNumber(String number) {
        editor.putString(numberKey,number);
        editor.commit();
    }

    public String getWallet() {
        return sharedPreferences.getString(walletKey,"");
    }

    public void setWallet(String wallet) {
        editor.putString(walletKey,wallet);
        editor.commit();
    }

    public String getComission() {
        return sharedPreferences.getString(comissionKey,"");
    }

    public void setComission(String wallet) {
        editor.putString(comissionKey,wallet);
        editor.commit();
    }

    public Boolean getLoged() {
        return sharedPreferences.getBoolean(logedKey,false);
    }

    public void setLoged(Boolean loged) {
        editor.putBoolean(logedKey,loged);
        editor.commit();
    }

    public void clearData(){
        editor.clear();
        editor.apply();
    }
}
