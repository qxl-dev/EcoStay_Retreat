package com.example.ecostayretreat.activities;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class ActivityListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("On-Site Activities");
    }
}
    