package com.yashswi.dilpay.models;

import android.content.Context;
import android.content.SharedPreferences;

public class bankDetails {

    String acntNumber, ifscCode, BeneficiaryID, Emial, Address;

    public String getAcntNumber() {
        return acntNumber;
    }

    public void setAcntNumber(String acntNumber) {
        this.acntNumber = acntNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBeneficiaryID() {
        return BeneficiaryID;
    }

    public void setBeneficiaryID(String beneficiaryID) {
        BeneficiaryID = beneficiaryID;
    }

    public String getEmial() {
        return Emial;
    }

    public void setEmial(String emial) {
        Emial = emial;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
