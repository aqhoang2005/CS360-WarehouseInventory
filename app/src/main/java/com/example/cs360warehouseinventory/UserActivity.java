package com.example.cs360warehouseinventory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        // Show back arrow
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView usernameText = findViewById(R.id.usernameText);
        Button logoutBtn = findViewById(R.id.logoutBtn);

        // Get username from intent
        String username = getIntent().getStringExtra("username");

        if (username != null) {
            usernameText.setText(username);
        }

        logoutBtn.setOnClickListener(v -> {
            // Clear activity stack and go back to login
            Intent i = new Intent(UserActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();  // Go back to MainActivity
        return true;
    }
}
