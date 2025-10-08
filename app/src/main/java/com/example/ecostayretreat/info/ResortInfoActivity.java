package com.example.ecostayretreat.info;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class ResortInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("About Our Resort");
    }
}
