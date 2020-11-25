package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_log_in.*

class ActivityLogIn : AppCompatActivity() {

    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

         auth = FirebaseAuth.getInstance()


        btnConfirmLogin.setOnClickListener {


             fun login()
            {
                btnConfirmLogin.setOnClickListener {

                    if(TextUtils.isEmpty(editTextTextEmailAddress3.text.toString()))
                    {
                        editTextTextEmailAddress3.setError("Enter email")
                        return@setOnClickListener
                    }

                    else if(TextUtils.isEmpty(editTextTextPassword2.text.toString()))
                    {
                        editTextTextPassword2.setError("Enter the password")
                        return@setOnClickListener
                    }

                    auth.signInWithEmailAndPassword(editTextTextEmailAddress3.text.toString(),editTextTextPassword2.text.toString())
                        .addOnCompleteListener{
                            if(it.isSuccessful){
                                val intent = Intent(this, ActivityConfirmedLogin::class.java)
                                startActivity(intent)
                            }else
                            {
                                Toast.makeText(applicationContext,"you are an invaild user", Toast.LENGTH_LONG)

                            }
                        }

                }
            }

    }
   /* private fun login()
    {
        btnConfirmLogin.setOnClickListener {

        if(TextUtils.isEmpty(editTextTextEmailAddress3.text.toString()))
        {
            editTextTextEmailAddress3.setError("Enter email")
            return@setOnClickListener
        }

            else if(TextUtils.isEmpty(editTextTextPassword2.text.toString()))
        {
            editTextTextPassword2.setError("Enter the password")
            return@setOnClickListener
        }

            auth.signInWithEmailAndPassword(editTextTextEmailAddress3.text.toString(),editTextTextPassword2.text.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, ActivityConfirmedLogin::class.java)
                        startActivity(intent)
                    }else
                    {
                        Toast.makeText(applicationContext,"you are an invaild user", Toast.LENGTH_LONG)

                    }
                }

        }
    }*/
    }
}