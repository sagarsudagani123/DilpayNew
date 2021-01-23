package com.yashswi.dilpay.Api_interface;

public interface seatSelection {
    void selectSelected(float data,String seatNumber,Float amount);
    int numOfSeatsSelected();
    void deleteSeatSelected(int position,String seatNumber,Float amount);
}
