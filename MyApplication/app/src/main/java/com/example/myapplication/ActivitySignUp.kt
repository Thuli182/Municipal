package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class ActivitySignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()



        btnConfirmSign.setOnClickListener {
            /*val intent = Intent(this, ConfirmSignIn::class.java)
            startActivity(intent)*/

            if (editTextTextEmailAddress.text.trim().toString().isNotEmpty()
                && editTextTextPassword.text.trim().toString().isNotEmpty()
                &&personName.text.trim().toString().isNotEmpty()
                &&personSurname.text.trim().toString().isNotEmpty()
                &&editTextIdnumber.text.trim().isNotEmpty())

            {

                Toast.makeText(this, "Input provided",Toast.LENGTH_LONG).show()
                signUpuser()

            }
            else
            {
                Toast.makeText(this, "Input is Required",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun signUpuser()
    {

        auth.createUserWithEmailAndPassword(editTextTextEmailAddress.text.trim().toString(), editTextTextPassword.text.trim().toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this,ConfirmSignIn::class.java))
                    finish();
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }

                // ...
            }

    }


}