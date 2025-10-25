package com.ecostay.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.ui.MainActivity;
import com.ecostay.util.SessionManager;

public class SimpleLoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            // Create a simple layout programmatically
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(50, 50, 50, 50);
            
            TextView title = new TextView(this);
            title.setText("🌿 EcoStay Retreat\nLogin");
            title.setTextSize(24);
            title.setPadding(0, 0, 0, 30);
            layout.addView(title);
            
            etEmail = new EditText(this);
            etEmail.setHint("Email");
            etEmail.setPadding(20, 20, 20, 20);
            layout.addView(etEmail);
            
            etPassword = new EditText(this);
            etPassword.setHint("Password");
            etPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etPassword.setPadding(20, 20, 20, 20);
            layout.addView(etPassword);
            
            btnLogin = new Button(this);
            btnLogin.setText("Login");
            btnLogin.setPadding(20, 20, 20, 20);
            layout.addView(btnLogin);
            
            btnRegister = new Button(this);
            btnRegister.setText("Register");
            btnRegister.setPadding(20, 20, 20, 20);
            layout.addView(btnRegister);
            
            setContentView(layout);
            
            // Simple login logic
            btnLogin.setOnClickListener(v -> {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                // For testing, accept any non-empty credentials
                SessionManager.setUserId(this, 1);
                SessionManager.setUserName(this, "Test User");
                SessionManager.setUserEmail(this, email);
                
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            });
            
            btnRegister.setOnClickListener(v -> {
                Toast.makeText(this, "Registration not implemented yet", Toast.LENGTH_SHORT).show();
            });
            
        } catch (Exception e) {
            // If even this fails, show error
            TextView errorView = new TextView(this);
            errorView.setText("Login Error: " + e.getMessage());
            errorView.setTextSize(16);
            errorView.setPadding(20, 20, 20, 20);
            setContentView(errorView);
        }
    }
}
