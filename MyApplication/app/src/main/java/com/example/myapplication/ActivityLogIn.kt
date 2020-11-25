package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class ActivityLogIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = FirebaseAuth.getInstance()

        btnConfirmLogin.setOnClickListener {
            /*val intent = Intent(this, ActivityConfirmedLogin::class.java)
            startActivity(intent)*/
            doLogin()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun doLogin()
    {


        Log.e("Time",loginEmail.text.trim().toString())
        Log.e("Time",Loginpassword.text.trim().toString())


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