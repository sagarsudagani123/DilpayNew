package com.yashswi.dilpay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.bank.BankAccounts;
import com.yashswi.dilpay.bus.My_bookings;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Profile extends AppCompatActivity {
    ImageView back,profilePic;
    CircularImageView customer_profilepic;
    LinearLayout editProfLayout,logout,my_bookings,withdraw;
    AppCompatButton upgradeBtn;
    RelativeLayout my_wallet,bank_accounts;
    TextView customer_name,customer_mobile,amount, paidText;
    com.yashswi.dilpay.models.userDetails userDetails;
    String addr,walletAmount="",commissionAmount="";
    RelativeLayout progress;
    boolean walletUpdated=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        userDetails=new userDetails(Profile.this);
        getWalletAmount(userDetails.getNumber());

        back=findViewById(R.id.back);
        logout=findViewById(R.id.logout);
        editProfLayout=findViewById(R.id.editProfLayout);
        my_bookings=findViewById(R.id.lin3);
        my_wallet=findViewById(R.id.lin4);
        bank_accounts=findViewById(R.id.lin5);
        customer_name=findViewById(R.id.customer_name);
        customer_mobile=findViewById(R.id.customer_mobile);
        withdraw=findViewById(R.id.withdraw);
        upgradeBtn =findViewById(R.id.membership);
        profilePic=findViewById(R.id.profilePic);
        customer_profilepic=findViewById(R.id.customer_profilepic);
        progress=findViewById(R.id.progress);
        amount=findViewById(R.id.amount);
        paidText =findViewById(R.id.membershipText);

        amount.setText("Loading...");
        customer_name.setText(userDetails.getName());
        customer_mobile.setText(userDetails.getNumber());

        if(userDetails.getMembership().equalsIgnoreCase("Paid")){
            upgradeBtn.setVisibility(View.GONE);
            paidText.setVisibility(View.VISIBLE);
        }
        else{
            upgradeBtn.setVisibility(View.VISIBLE);
            paidText.setVisibility(View.GONE);
        }

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(Profile.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, BankAccounts.class);
                i.putExtra("title","Select Bank");
                startActivity(i);
            }
        });

        upgradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,Upgrade_membership.class);
                intent.putExtra("number",userDetails.getNumber());
                startActivity(intent);
                finish();
            }
        });

        editProfLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,EditProfile.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this,Login_screen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                userDetails.setLoged(false);
                userDetails.clearData();
                startActivity(i);
                finish();
            }
        });

        my_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, My_bookings.class);
                startActivity(i);
            }
        });

        my_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!walletAmount.equalsIgnoreCase("") && !commissionAmount.equalsIgnoreCase("")){
                    Intent i = new Intent(Profile.this, MyWallet.class);
                    i.putExtra("walletAmt",walletAmount);
                    i.putExtra("commissionAmt",commissionAmount);
                    startActivity(i);
                }

            }
        });

        bank_accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, BankAccounts.class);
                i.putExtra("title","My Accounts");
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            Uri uri = data.getData();
            uri.getPath();
            addr=uri.getPath();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(Profile.this.getContentResolver(), uri);
                customer_profilepic.setImageBitmap(bitmap);
//                uploadFile();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(Profile.this,e.toString(),Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Profile.this,"Try other option",Toast.LENGTH_SHORT).show();
        }


    }
    void getWalletAmount(String number){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);

        Call<String> call = api.WalletUpdate(number);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null || !response.body().isEmpty()){
                    try {
                        JSONObject data=new JSONObject(response.body());
                        if(data.getString("Status").equalsIgnoreCase("SUCCESS")){
                            userDetails.setWallet(data.getString("WALLET"));
                            userDetails.setComission(data.getString("COMISSION"));
                            walletAmount=data.getString("WALLET");
                            commissionAmount=data.getString("COMISSION");
                            amount.setText(data.getString("WALLET"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    private void uploadFile() {
        progress.setVisibility(View.VISIBLE);

        File file = new File(addr);
        ArrayList<File> files=new ArrayList<>();

//        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api=retrofit.create(Api_interface.class);
        Call<String> call=api.uploadPic(fileToUpload,filename);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(Profile.this,response.body(),Toast.LENGTH_SHORT).show();

                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Profile.this,t.toString(),Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }
        });
    }
}