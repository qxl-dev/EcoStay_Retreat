package com.ecostay.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.data.dao.UserDao;
import com.ecostay.data.model.User;
import com.ecostay.ui.MainActivity;
import com.ecostay.util.SessionManager;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            setContentView(R.layout.activity_login);

            etEmail = findViewById(R.id.etEmail);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);
            btnRegister = findViewById(R.id.btnGoRegister);

            userDao = new UserDao(this);

            btnLogin.setOnClickListener(v -> {
                try {
                    String email = etEmail.getText().toString().trim();
                    String pass = etPassword.getText().toString().trim();

                    User user = userDao.loginUser(email, pass);

                    if (user != null) {
                        SessionManager.setUserId(this, user.id);
                        SessionManager.setUserName(this, user.name);
                        SessionManager.setUserEmail(this, user.email);
                        Toast.makeText(this, "Welcome, " + user.name + "!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Login error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            btnRegister.setOnClickListener(v -> {
                try {
                    startActivity(new Intent(this, RegisterActivity.class));
                } catch (Exception e) {
                    Toast.makeText(this, "Error opening registration", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            // If there's any error, show a simple message
            Toast.makeText(this, "App initialization error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
