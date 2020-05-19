package com.king.mobile.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.king.mobile.component.Component;
import com.king.mobile.component.DemoAnnotation;
@Component
@DemoAnnotation
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
