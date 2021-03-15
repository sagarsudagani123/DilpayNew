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

    String JSONURL = "http://dilbus.in/api/";

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
    Call<String> login(@Field("username") String username, @Field("password") String password, @Field("Token") String Token);

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

    //payment gateway token generation
    @POST("CashfreeToken.php")
    @FormUrlEncoded
    Call<String> generateToken(@Field("MyOrderId") String orderID, @Field("MyorderAmount") String amount);

    @POST("NotificationsReport.php")
    @FormUrlEncoded
    Call<String> getNotifications(@Field("username") String userNumber);


    @POST("StatementReport.php")
    @FormUrlEncoded
    Call<String> getTransactions(@Field("username") String userNumber);


    @POST("BankDetailsPost.php")
    @FormUrlEncoded
    Call<String> addBankDetails(@Field("data") String data);


    @POST("GetUserBankDetails.php")
    @FormUrlEncoded
    Call<String> getBanks(@Field("username") String userNumber);

    @GET("MyBusBookingInfo.php")
    Call<String> myBookings(@Query("username") String number);

    @POST("ProfilEdit.php")
    @FormUrlEncoded
    Call<String> updateDetails(@Field("data") String data);

    @GET("PayoutToken.php")
    Call<String> generatePayoutToken();

    @GET("PayoutToken.php")
    Call<String> generatePayoutWithdrawToken(@Query("data") String data);

    @POST("VerifyAmountEligibility.php")
    @FormUrlEncoded
    Call<String> verifyAmount(@Field("Data") String data);

    @POST("ImageUpload.php")
    @FormUrlEncoded
    Call<String> uploadPic(@Field("ImageUpload") String data);

    @POST("PayoutCompleted.php")
    @FormUrlEncoded
    Call<String> updateTransaction(@Field("data") String data);

    @POST("AvailbilityPoints.php")
    @FormUrlEncoded
    Call<String> getRewardDetails(@Field("username") String username);

    @GET("Convertpoints.php")
    Call<String> convertRewards(@Query("data") String data);

    @POST("MobileRecharge.php")
    @FormUrlEncoded
    Call<String> MobileRecharge(@Field("data") String data);


    @POST("RechargeReport.php")
    @FormUrlEncoded
    Call<String> rechargeHistory(@Field("data") String data);


    @POST("AddMoney.php")
    @FormUrlEncoded
    Call<String> addWalletAmount(@Field("data") String data);

    @GET("Notifications.php")
    Call<String> Notification();

    @POST("RechargeReportCheck.php")
    @FormUrlEncoded
    Call<String> RechargeReportCheck(@Field("data") String data);
}
