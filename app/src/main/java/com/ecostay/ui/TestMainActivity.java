package com.ecostay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.ui.auth.TestLoginActivity;

public class TestMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            // Create a simple layout programmatically
            TextView textView = new TextView(this);
            textView.setText("EcoStay Retreat - Test Mode\n\nApp is working!\n\nTesting navigation to LoginActivity...");
            textView.setTextSize(16);
            textView.setPadding(30, 30, 30, 30);
            setContentView(textView);
            
            // After 2 seconds, try to navigate to TestLoginActivity
            textView.postDelayed(() -> {
                try {
                    startActivity(new Intent(this, TestLoginActivity.class));
                    finish();
                } catch (Exception e) {
                    textView.setText("Navigation Error: " + e.getMessage() + "\n\nStack trace:\n" + android.util.Log.getStackTraceString(e));
                }
            }, 2000);
            
        } catch (Exception e) {
            // If even this fails, show error
            TextView errorView = new TextView(this);
            errorView.setText("Critical Error: " + e.getMessage() + "\n\nStack trace:\n" + android.util.Log.getStackTraceString(e));
            errorView.setTextSize(12);
            errorView.setPadding(20, 20, 20, 20);
            setContentView(errorView);
        }
    }
}
