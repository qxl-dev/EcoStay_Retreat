package com.ecostay.ui.auth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.data.dao.UserDao;
import com.ecostay.data.model.User;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword, etPreferences;
    Button btnRegister;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPreferences = findViewById(R.id.etPreferences);
        btnRegister = findViewById(R.id.btnRegister);

        userDao = new UserDao(this);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userDao.isEmailExists(email)) {
                Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.name = name;
            user.email = email;
            user.password = pass;
            user.preferences = etPreferences.getText().toString().trim();

            if (userDao.registerUser(user)) {
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
