package com.yashswi.dilpay.Api_interface;

import android.content.Intent;


    interface SmsBroadcastReceiverListener {
        void onSuccess(Intent intent);

        void onFailure();
    }
