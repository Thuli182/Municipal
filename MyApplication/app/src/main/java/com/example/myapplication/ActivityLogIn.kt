package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class ActivityLogIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private  var rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val demoRef: DatabaseReference = rootRef.child("UserDatails")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = FirebaseAuth.getInstance()

        btnConfirmLogin.setOnClickListener {

            doLogin()

        }
    }



    private fun doLogin()
    {

        auth.signInWithEmailAndPassword(loginEmail.text.trim().toString(),Loginpassword.text.trim().toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                    // ...
                }

                // ...
            }
    }

    private  fun  updateUI(currentUser: FirebaseUser?)
    {
        if(currentUser!=null)
        {
            startActivity(Intent(this,ActivityConfirmedLogin::class.java))
            finish();
        }
        else{
            Toast.makeText(baseContext, "Login failed.",
                Toast.LENGTH_SHORT).show()
        }
    }
}