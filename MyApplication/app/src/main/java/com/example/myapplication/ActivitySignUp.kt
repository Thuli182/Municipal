package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class ActivitySignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var refUsers: DatabaseReference
    private  var firebaseUserID: String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        signUpuser()
    }

    private fun signUpuser()
    {
        btnConfirmSign.setOnClickListener {
            /*val intent = Intent(this, ConfirmSignIn::class.java)
            startActivity(intent)*/
            if(TextUtils.isEmpty(personName.text.toString())) {
                personName.setError("Please enter first name ")
                return@setOnClickListener
            } else if(TextUtils.isEmpty(personSurname.text.toString())) {
                personSurname.setError("Please enter last Surname")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(editTextIdnumber.text.toString())) {
                editTextIdnumber.setError("Please enter user Id number ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(editTextTextEmailAddress.text.toString())) {
                editTextTextEmailAddress.setError("Please enter Email Address ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(editTextTextPassword.text.toString())) {
                editTextTextPassword.setError("Please enter password ")
                return@setOnClickListener

            }
            auth.createUserWithEmailAndPassword(editTextTextEmailAddress.text.trim().toString(), editTextTextPassword.text.trim().toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val sew = (1..100).shuffled().first()
                        val wat= sew*2
                        val elec=(1..100).shuffled().first()*1.5
                        val open=sew*10
                        val ru=wat-10
                        val total=sew+wat+elec+open+ru

                        firebaseUserID= auth.currentUser!!.uid
                        refUsers= FirebaseDatabase.getInstance().reference.child("UserDatails").child(firebaseUserID)

                        val userHashMap = HashMap<String,Any>()
                        userHashMap["uid"] =firebaseUserID
                        userHashMap["Name"]=personName.text.toString()
                        userHashMap["Surname"]=personSurname.text.toString()
                        userHashMap["Idnumber"]=editTextIdnumber.text.toString()
                        userHashMap["EmailAddress"]=editTextTextEmailAddress.text.toString()
                        userHashMap["Password"]=editTextTextPassword.text.toString()
                        userHashMap["OpenBalace"]=open.toString()
                        userHashMap["Refus"]=ru.toString()
                        userHashMap["electricity"]=elec.toString()
                        userHashMap["sewer"]= sew.toString()
                        userHashMap["water"]= wat.toString()
                        userHashMap["Sub-Total"]= total.toString()


                        refUsers.updateChildren(userHashMap).addOnCompleteListener { task -> if(task.isSuccessful)
                        {
                            startActivity(Intent(this,ConfirmSignIn::class.java))
                            finish();
                        }  }

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }

                    // ...
                }

        }


    }
}