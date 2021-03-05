package com.yashswi.dilpay.Api_interface;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface cashFree {

//    String JSONURL="https://payout-gamma.cashfree.com/";
    String JSONURL="https://payout-api.cashfree.com/";


    @Headers({"Accept: application/json"})
    @POST("payout/v1/authorize")
    Call<String> getToken(@Header("X-Client-Id") String id, @Header("X-Client-Secret") String secret);

    @Headers({"Accept: application/json"})
    @POST("payout/v1/addBeneficiary")
    Call<String> addBeneficiary(@Header("Authorization") String token, @Body String body);

    @Headers({"Accept: application/json"})
    @POST("/payout/v1/removeBeneficiary")
    Call<String> removeBeneficiary(@Header("Authorization") String token, @Body String body);

    @Headers({"Accept: application/json"})
    @POST("/payout/v1/requestTransfer")
    Call<String> walletWithdraw(@Header("Authorization") String token, @Body String body);

//    @Headers({"Accept: application/json"})
//    @POST("/payout/v1/requestAsyncTransfer")
//    Call<String> walletWithdraw(@Header("Authorization") String token, @Body String body);
}
