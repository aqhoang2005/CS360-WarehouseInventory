package com.example.cs360warehouseinventory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private DatabaseActivity db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);

        db = new DatabaseActivity(this);

        EditText userEt = findViewById(R.id.usernameInput);
        EditText passEt = findViewById(R.id.passwordInput);

        findViewById(R.id.loginButton).setOnClickListener(v -> {
            String username = userEt.getText().toString().trim();
            String password = passEt.getText().toString().trim();

            if (username.isEmpty()) { userEt.setError("Required"); return; }
            if (password.isEmpty()) { passEt.setError("Required"); return; }

            if (db.validateLogin(username, password)) {

                Intent i = new Intent(LoginActivity.this, MainActivity.class);

                // Pass username to MainActivity
                i.putExtra("username", username);

                startActivity(i);
                finish();

            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.createAccountButton).setOnClickListener(v -> {
            String username = userEt.getText().toString().trim();
            String password = passEt.getText().toString().trim();

            if (username.isEmpty()) { userEt.setError("Required"); return; }
            if (password.isEmpty()) { passEt.setError("Required"); return; }

            if (db.userExists(username)) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            long id = db.createUser(username, password);
            if (id == -1) {
                Toast.makeText(this, "Could not create account", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Account created! You can log in now.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}