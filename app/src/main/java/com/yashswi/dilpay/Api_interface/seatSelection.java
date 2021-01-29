package com.yashswi.dilpay.Api_interface;

public interface seatSelection {
    void seatSelected(float data,String seatNumber,Float amount,Float seatAmount,Float serviceTax,Float serviceCharge);
    int numOfSeatsSelected();
    void deleteSeatSelected(int position,String seatNumber,Float amount);
}
