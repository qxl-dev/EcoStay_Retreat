package com.ecostay.ui.auth;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TestLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            // Create a simple layout programmatically
            TextView textView = new TextView(this);
            textView.setText("Test Login Activity\n\nThis is a minimal login test.\n\nIf you see this, the LoginActivity can be created.\n\nThe issue might be in:\n- Layout inflation\n- Resource loading\n- Database operations");
            textView.setTextSize(16);
            textView.setPadding(30, 30, 30, 30);
            setContentView(textView);
            
        } catch (Exception e) {
            // If even this fails, show error
            TextView errorView = new TextView(this);
            errorView.setText("LoginActivity Error: " + e.getMessage() + "\n\nStack trace:\n" + android.util.Log.getStackTraceString(e));
            errorView.setTextSize(12);
            errorView.setPadding(20, 20, 20, 20);
            setContentView(errorView);
        }
    }
}
