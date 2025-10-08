package com.example.ecostayretreat.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecostayretreat.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layouttest.activity_login);

        // --- Basic Logic ---
        EditText etEmail = findViewById(R.id.etLoginEmail);
        EditText etPassword = findViewById(R.id.etLoginPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvGoToRegister = findViewById(R.id.tvGoToRegister);

        // TODO: Implement actual login logic (e.g., with Firebase)
        // For now, this is just a placeholder.
        btnLogin.setOnClickListener(v -> {
            // In a real app, you would validate credentials here.
            // For now, we'll just go back to the main activity.
            finish(); // Closes the login screen
        });

        tvGoToRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
