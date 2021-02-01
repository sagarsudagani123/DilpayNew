package com.yashswi.dilpay.Api_interface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api_interface {

    String JSONURL = " http://dilbus.in/api/";

    @GET("bustickets.php")
    Call<String> available_buses(
            @Query("Source") String Source,
            @Query("Destination") String Destination,
            @Query("DOJ") String DOJ);

    @GET("seatbooking.php")
    Call<String> trip_details(@Query("id") String id, @Query("OperatorCode") String OperatorCode, @Query("OperatorName") String OperatorName, @Query("SourceIDJ") String SourceIDJ,
                              @Query("DestinationIDJ") String DestinationIDJ, @Query("DateDOJ") String DateDOJ);

    @POST("routines.php")
    @FormUrlEncoded
    Call<String> login(@Field("username") String username, @Field("password") String password);

    @GET("TicketBlock.php")
    Call<String> bookingDetails(@Query("TicketBlock") String data);

    @GET("FreeRegister.php")
    Call<String> register(@Query("My_mobile") String name);

    @GET("FreeRegister1.php")
    Call<String> register1(@Query("data") String name);

    @GET("BusSources.php")
    Call<String> getPlaces();


}
