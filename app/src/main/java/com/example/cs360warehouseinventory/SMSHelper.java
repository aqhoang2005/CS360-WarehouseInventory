package com.example.cs360warehouseinventory;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SMSHelper {

    public static final int REQ_SEND_SMS = 2001;

    public static boolean hasSmsPermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestSmsPermission(Activity activity) {
        ActivityCompat.requestPermissions(
                activity,
                new String[]{Manifest.permission.SEND_SMS},
                REQ_SEND_SMS
        );
    }

    public static boolean sendSmsIfPermitted(Activity activity, String phone, String message) {
        if (!hasSmsPermission(activity)) return false;
        SmsManager.getDefault().sendTextMessage(phone, null, message, null, null);
        return true;
    }
}