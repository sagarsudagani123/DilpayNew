package com.yashswi.dilpay.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class CheckNetworkStatus {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Boolean getConnectivityStatusString(Context context) {
        boolean status = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();//getActiveNetworkInfo()
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = true;
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = true;
                return status;
            }
        } else {
            return status;
        }
        return status;
    }
}
