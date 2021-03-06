package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSignUp: Button = findViewById(R.id.btnSignIn);
        val btnLogIn: Button = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener {
            val intent = Intent(this, ActivitySignUp::class.java)
            startActivity(intent)
        }
        btnLogIn.setOnClickListener {
            val intent = Intent(this, ActivityLogIn::class.java)
            startActivity(intent)
        }
    }
}