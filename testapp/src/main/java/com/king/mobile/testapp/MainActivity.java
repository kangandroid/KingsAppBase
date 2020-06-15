package com.king.mobile.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.king.mobile.component.Component;

@Component
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
