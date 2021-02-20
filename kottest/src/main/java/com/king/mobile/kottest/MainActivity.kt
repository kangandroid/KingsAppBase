package com.king.mobile.kottest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val findViewById = findViewById<TextView>(R.id.hello)
        findViewById.setOnClickListener { test("hello world")}

    }

    private fun test(name: String){
        Thread.sleep(5*1000L)
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show()
    }

}