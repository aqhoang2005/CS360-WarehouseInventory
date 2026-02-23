package com.example.cs360warehouseinventory;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SMSActivity extends AppCompatActivity {

    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smsnotificationlayout); // your SMS XML filename

        status = findViewById(R.id.permissionStatus);
        updateStatus();

        findViewById(R.id.enableSMSBtn).setOnClickListener(v -> SMSHelper.requestSmsPermission(this));
    }

    private void updateStatus() {
        status.setText(SMSHelper.hasSmsPermission(this)
                ? "Permission status: Granted"
                : "Permission status: Not granted");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SMSHelper.REQ_SEND_SMS) {
            // Whether granted or denied, the app continues normally:
            boolean granted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
            updateStatus();

            // No crash, no blocking. If denied, you simply won't send SMS.
        }
    }
}