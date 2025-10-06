package com.example.ecostayretreat.auth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecostayretreat.R;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // --- Basic Logic ---
        EditText etName = findViewById(R.id.etRegisterName);
        EditText etEmail = findViewById(R.id.etRegisterEmail);
        EditText etPassword = findViewById(R.id.etRegisterPassword);
        Button btnRegister = findViewById(R.id.btnRegister);

        // TODO: Implement actual registration logic (e.g., with Firebase)
        // This is a placeholder for now.
        btnRegister.setOnClickListener(v -> {
            // In a real app, you would create a new user here.
            // For now, we'll just close the screen.
            finish(); // Closes the register screen and returns to login
        });
    }
}
