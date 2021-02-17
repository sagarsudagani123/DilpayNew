package com.yashswi.dilpay.Api_interface;

import com.yashswi.dilpay.models.Add_account_model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    Call<String> login(@Field("username") String username, @Field("password") String password,@Field("Token") String Token);

    @GET("TicketBlock.php")
    Call<String> bookingDetails(@Query("TicketBlock") String data);

    @GET("FreeRegister.php")
    Call<String> register(@Query("My_mobile") String name);

    @GET("FreeRegister1.php")
    Call<String> register1(@Query("data") String name);

    @GET("BusSources.php")
    Call<String> getPlaces();

    @GET("BusTicketConfirmation.php")
    Call<String> confirmTicket(@Query("BusTicketConfirmation") String data);

    @GET("WalletUpdate.php")
    Call<String> WalletUpdate(@Query("username") String number);

    @POST("Paid_Register.php")
    @FormUrlEncoded
    Call<String> upgradeUser(@Field("username") String username, @Field("Refferal_User") String refCode);

    @POST("CashfreeToken.php")
    @FormUrlEncoded
    Call<String> generateToken(@Field("MyOrderId") String orderID, @Field("MyorderAmount") String amount);

    @POST("NotificationsReport.php")
    @FormUrlEncoded
    Call<String> getNotifications(@Field("username") String userNumber);


    //test upload image
    @Multipart
    @POST("test2.php")
    Call<String> uploadPic(@Part MultipartBody.Part file, @Part("file") RequestBody name);
}
