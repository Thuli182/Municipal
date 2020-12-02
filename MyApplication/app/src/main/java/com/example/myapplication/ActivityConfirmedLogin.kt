package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_confirmed_login.*
import kotlinx.android.synthetic.main.activity_log_in.*
import java.text.SimpleDateFormat
import java.util.*

class ActivityConfirmedLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmed_login)
        val uid: String? = FirebaseAuth.getInstance().uid

        var database = FirebaseDatabase.getInstance().reference.child("UserDatails").orderByChild("uid").equalTo(uid.toString())

        var getdata= object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                var name1= StringBuilder()
                var Idnumber1= StringBuilder()
                var email1= StringBuilder()
                var Surname1= StringBuilder()

                var water1= StringBuilder()
                var sewer1= StringBuilder()
                var electricity1= StringBuilder()
                var OpenBalance1= StringBuilder()
                var Refus1= StringBuilder()


                for(i in p0.children)
                {
                    var Idnumber =i.child("Idnumber").getValue()
                    var email =i.child("EmailAddress").getValue()
                    var name =i.child("Name").getValue()
                    var surname =i.child("Surname").getValue()

                    var water= i.child(" water").getValue()
                    var sewer=i.child("sewer").getValue()
                    var electricity= i.child("electricity").getValue()
                    var OpenBalance= i.child("OpenBalace").getValue()
                    var Refus= i.child("Refus").getValue()

                    Idnumber1.append("$Idnumber")
                    name1.append("$name $surname")
                    email1.append("$email")

                    water1.append("$water")
                    sewer1.append("$sewer")
                    electricity1.append("$electricity")
                    OpenBalance1.append("$OpenBalance")
                    Refus1.append("$Refus")


                }
                val sdf = SimpleDateFormat("dd-MM-yyyy")
                val currentDate = sdf.format(Date())

                nameandsurname.setText(name1)
                textViewID.setText(Idnumber1)
                textDate.setText(currentDate)
                electricitytextView.setText(electricity1)
                watertextView.setText(water1)
                RefustextView.setText(Refus1)
                OpenBalancetextView.setText(OpenBalance1)
                PaymentstextView.setText(OpenBalance1)

            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

        btnPayment.setOnClickListener {
            val url = "https://new.easypay.co.za/Home.aspx?REDIout=1"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

    }
}