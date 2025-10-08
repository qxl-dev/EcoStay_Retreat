package com.example.ecostayretreat.auth;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("User Profile");
    }
}
    