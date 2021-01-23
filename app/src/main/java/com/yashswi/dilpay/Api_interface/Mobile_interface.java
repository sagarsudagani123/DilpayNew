package com.yashswi.dilpay.Api_interface;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface Mobile_interface {

    String BASEURL="https://myrc.in/recharge/";

    @POST("api")
    @FormUrlEncoded
    Call<String> mobile_recharge(@Field("username") String username, @Field("pwd") String pwd, @Field("circlecode") String circlecode, @Field("operatorcode") String operatorcode,
                                 @Field("number") String number, @Field("amount") String amount, @Field("orderid") String orderid,@Field("format") String format);



}
