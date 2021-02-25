package com.yashswi.dilpay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.bank.BankAccounts;
import com.yashswi.dilpay.bus.My_bookings;
import com.yashswi.dilpay.models.userDetails;
import com.yashswi.dilpay.utils.CheckNetworkStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Profile extends AppCompatActivity {
    ImageView back, profilePic;
    CircularImageView customer_profilepic;
    LinearLayout editProfLayout, logout, my_bookings, withdraw;
    AppCompatButton upgradeBtn;
    RelativeLayout my_wallet, bank_accounts;
    TextView customer_name, customer_mobile, amount, paidText;
    com.yashswi.dilpay.models.userDetails userDetails;
    String addr, walletAmount = "", commissionAmount = "";
    RelativeLayout progress;
    String val_img;
    boolean walletUpdated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        userDetails = new userDetails(Profile.this);
        getWalletAmount(userDetails.getNumber());

        if (!CheckNetworkStatus.getConnectivityStatusString(Profile.this)) {
            Toast.makeText(Profile.this, "No internet connection", Toast.LENGTH_SHORT).show();
        } else {
//            if(getIntent().getStringExtra("toProfile").equalsIgnoreCase("fromEditProfile")){
            updateUserDetails();
//            }
        }


        back = findViewById(R.id.back);
        logout = findViewById(R.id.logout);
        editProfLayout = findViewById(R.id.editProfLayout);
        my_bookings = findViewById(R.id.lin3);
        my_wallet = findViewById(R.id.lin4);
        bank_accounts = findViewById(R.id.lin5);
        customer_name = findViewById(R.id.customer_name);
        customer_mobile = findViewById(R.id.customer_mobile);
        withdraw = findViewById(R.id.withdraw);
        upgradeBtn = findViewById(R.id.membership);
        profilePic = findViewById(R.id.profilePic);
        customer_profilepic = findViewById(R.id.customer_profilepic);
        progress = findViewById(R.id.progress);
        amount = findViewById(R.id.amount);
        paidText = findViewById(R.id.membershipText);

//        String updateTime = String.valueOf(System.currentTimeMillis());
        Glide.with(Profile.this)
                .load(userDetails.getProfilePic())
                .fitCenter()
                .signature(new ObjectKey(System.currentTimeMillis()))
                .placeholder(R.drawable.profile_pic)
                .error(R.drawable.profile_pic)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(customer_profilepic);
//        Picasso.get().load(userDetails.getProfilePic()).into(customer_profilepic);
//        Toast.makeText(Profile.this,userDetails.getProfilePic(),Toast.LENGTH_SHORT).show();

        amount.setText("Loading...");
        customer_name.setText(userDetails.getName());
        customer_mobile.setText(userDetails.getNumber());

        if (userDetails.getMembership().equalsIgnoreCase("Paid")) {
            upgradeBtn.setVisibility(View.GONE);
            paidText.setVisibility(View.VISIBLE);
        } else {
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
                i.putExtra("title", "Select Bank");
                startActivity(i);
            }
        });

        upgradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Upgrade_membership.class);
                intent.putExtra("number", userDetails.getNumber());
                startActivity(intent);
                finish();
            }
        });

        editProfLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, EditProfile.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Login_screen.class);
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
                if (!walletAmount.equalsIgnoreCase("") && !commissionAmount.equalsIgnoreCase("")) {
                    Intent i = new Intent(Profile.this, MyWallet.class);
                    i.putExtra("walletAmt", walletAmount);
                    i.putExtra("commissionAmt", commissionAmount);
                    startActivity(i);
                }

            }
        });

        bank_accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, BankAccounts.class);
                i.putExtra("title", "My Accounts");
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

    private void updateUserDetails() {


        JSONObject createData = new JSONObject();
        try {
            createData.put("username", new userDetails(Profile.this).getNumber());
            createData.put("Method", "GetDetails");
            Log.e("EditProfile", createData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.updateDetails(createData.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("EEEEEEE", response.body());
                progress.setVisibility(View.GONE);
                try {
                    JSONObject data = new JSONObject(response.body());
                    JSONArray dataArray = data.getJSONArray("Data");
                    if (data.getString("Status").equalsIgnoreCase("True")) {
                        JSONObject details = dataArray.getJSONObject(0);
                        userDetails = new userDetails(Profile.this);
                        userDetails.setCity(details.getString("fcity"));
                        userDetails.setAddress(details.getString("faddress"));
                        userDetails.setState(details.getString("fstate"));
                        userDetails.setZip(details.getString("fzip"));
                        userDetails.setCountry(details.getString("fcountry"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Profile.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(Profile.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getWalletAmount(String number) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);

        Call<String> call = api.WalletUpdate(number);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null || !response.body().isEmpty()) {
                    try {
                        JSONObject data = new JSONObject(response.body());
                        if (data.getString("Status").equalsIgnoreCase("SUCCESS")) {
                            userDetails.setWallet(data.getString("WALLET"));
                            userDetails.setComission(data.getString("COMISSION"));
                            walletAmount = data.getString("WALLET");
                            commissionAmount = data.getString("COMISSION");
                            amount.setText(data.getString("WALLET"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(Profile.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage() {
        if (customer_profilepic.getDrawable() == null) {
            val_img = "";
            Toast.makeText(Profile.this, "Please select an image", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap imageBit = ((BitmapDrawable) customer_profilepic.getDrawable()).getBitmap();
            ByteArrayOutputStream byteA = new ByteArrayOutputStream();
            int currSize = 0;
            int currQuality = 100;
            imageBit.compress(Bitmap.CompressFormat.JPEG, currQuality, byteA);
            if (byteA.toByteArray().length > 50000) {
                progress.setVisibility(View.VISIBLE);
                do {
                    if (currQuality >= 0 && currQuality <= 100) {
                        byteA.reset();
                        imageBit.compress(Bitmap.CompressFormat.JPEG, currQuality, byteA);
                        currSize = byteA.toByteArray().length;
                        Log.e("currenctqty", currQuality + "=" + currSize);
                        // limit quality by 5 percent every time
                        currQuality = currQuality - 2;
                    }
                } while (currSize >= 50000);
            }
            Log.e("currenctqty=aftr", "" + byteA.toByteArray().length);
            val_img = Base64.encodeToString(byteA.toByteArray(), Base64.DEFAULT);
            getResponse();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            uri.getPath();
            addr = uri.getPath();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(Profile.this.getContentResolver(), uri);
                customer_profilepic.setImageBitmap(bitmap);
                uploadImage();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(Profile.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Profile.this, "Try other option", Toast.LENGTH_SHORT).show();
        }


    }

    private void getResponse() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        JSONObject data = new JSONObject();
        try {
            data.put("username", userDetails.getNumber());
            data.put("image", val_img);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("imageUpload", data.toString());

        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.uploadPic(data.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("imageUpload", response.body());
                if (response.body() == null) {
                    Toast.makeText(Profile.this, "null response", Toast.LENGTH_SHORT).show();
                }

                if (response.body().isEmpty()) {
                    Toast.makeText(Profile.this, "empty response", Toast.LENGTH_SHORT).show();
                }
                progress.setVisibility(View.GONE);
                try {
                    Toast.makeText(Profile.this, "/"+response.body(), Toast.LENGTH_SHORT).show();
                    JSONObject obj = new JSONObject(response.body());
                    if (obj.getBoolean("success")) {
                        String imgUrl = "http://www.dilbus.in/api/uploads/" + obj.getString("UserImage");
                        Log.e("urlImage", imgUrl);
                        userDetails.setProfilePic(imgUrl);
                        Glide.with(Profile.this)
                                .load(imgUrl)
                                .signature(new ObjectKey(System.currentTimeMillis()))
                                .error(R.drawable.profile_pic)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(customer_profilepic);
                    }

                    Toast.makeText(Profile.this, obj.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Profile.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(Profile.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}