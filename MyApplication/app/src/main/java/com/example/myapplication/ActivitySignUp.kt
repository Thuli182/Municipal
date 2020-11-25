package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*


class ActivitySignUp : AppCompatActivity() {


    lateinit var  regUsername:EditText
    lateinit var  regEmail:EditText
    lateinit var  regIdnumber:EditText
    lateinit var  regPassword:EditText
    lateinit var regName : EditText
    lateinit var buttonSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        regName = findViewById(R.id.personName);
        regUsername = findViewById(R.id.personSurname);
        regEmail = findViewById(R.id.editTextTextEmailAddress2);
        regIdnumber = findViewById(R.id.idnumber);
        regPassword = findViewById(R.id.editTextTextPassword);
        buttonSave= findViewById(R.id.btnConfirmSign);


        btnConfirmSign.setOnClickListener {
           // val intent = Intent(this, ConfirmSignIn::class.java)
         //  startActivity(intent)
          saveDate()
        }

    }

    private fun saveDate()
    {

        val name = regName.text.toString().trim();
        val  surname  = regUsername.text.toString().trim();
        val  email  =regEmail.text.toString().trim();
        val  Idnumber =regIdnumber.text.toString().trim();
        val  password =regPassword.text.toString().trim();




        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userIds= ref.push().key

        val user= userIds?.let { Datasave(it,name,surname,Idnumber,email,password) }
        ref.child(userIds.toString()).setValue(user).addOnCompleteListener{ Toast.makeText(applicationContext,"its saved",Toast.LENGTH_LONG)}

    }
}