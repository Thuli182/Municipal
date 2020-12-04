package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils.split
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_confirmed_login.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class ActivityConfirmedLogin : AppCompatActivity() {

    private val file_name = "example.txt"

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmed_login)
        val uid: String? = FirebaseAuth.getInstance().uid

        var database = FirebaseDatabase.getInstance().reference.child("UserDatails").orderByChild("uid").equalTo(
            uid.toString()
        )

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
                var Total1= StringBuilder()


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
                    var Total=i.child("Sub-Total").getValue()

                    Idnumber1.append("$Idnumber")
                    name1.append("$name $surname")
                    email1.append("$email")

                    water1.append("$water")
                    sewer1.append("$sewer")
                    electricity1.append("$electricity")
                    OpenBalance1.append("$OpenBalance")
                    Refus1.append("$Refus")
                    Total1.append("$Total")








                }
                val sdf = SimpleDateFormat("dd-MM-yyyy")
                val currentDate = sdf.format(Date())
                SubTottextView.setText(Total1)
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
        ActivityCompat.requestPermissions(
            this@ActivityConfirmedLogin,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )
        btndownload.setOnClickListener {
          //  save()
            createMyPDF()
        }

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    public fun createMyPDF(){
        val pagewidth=1200

        val myPdfDocument = PdfDocument();
        val myPageInfo =  PdfDocument.PageInfo.Builder(300,600,1).create();
        val myPage = myPdfDocument.startPage(myPageInfo);
        val myPaint =  Paint();
        val titlePaint= Paint();
       val canvas= myPage.canvas


        titlePaint.textAlign=Paint.Align.CENTER
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD_ITALIC))
        titlePaint.textSize=12F
        canvas.drawText("Invoice of the Month", 25F, 10F,titlePaint)

        myPaint.textAlign=Paint.Align.LEFT
        myPaint.textSize=10F
        myPaint.setColor(Color.BLACK)
        canvas.drawText("Name and Surname", 20F, 50F,myPaint)
        canvas.drawText("ID Number", 20F, 80F,myPaint)
        canvas.drawText("Statement of the month:", 20F, 100F,myPaint)
        canvas.drawText("Opening Balance", 20F, 120F,myPaint)
        canvas.drawText("Electricity", 20F, 140F,myPaint)
        canvas.drawText("Water", 20F, 160F,myPaint)
        canvas.drawText("Sewer", 20F, 180F,myPaint)
        canvas.drawText("Rufus", 20F, 200F,myPaint)

        myPaint.textAlign=Paint.Align.RIGHT
        canvas.drawText(nameandsurname.text.toString(), 250F, 50F,myPaint)
        canvas.drawText(textDate.text.toString(), 250F, 80F,myPaint)
        canvas.drawText(OpenBalancetextView.text.toString(), 250F, 100F,myPaint)
        canvas.drawText(electricitytextView.text.toString(), 250F, 120F,myPaint)
        canvas.drawText(watertextView.text.toString(), 250F, 160F,myPaint)
        canvas.drawText(sewertView37.text.toString(), 250F, 180F,myPaint)
        canvas.drawText(RefustextView.text.toString(), 250F, 200F,myPaint)


        myPdfDocument.finishPage(myPage);



        val myFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/Statement.pdf";
        val myFile = File(myFilePath);

        Log.e("path", myFile.toString())

        myPdfDocument.writeTo( FileOutputStream(myFile));


        myPdfDocument.close();
        Toast.makeText(
            this, "Check The file under downloads",
            Toast.LENGTH_LONG
        ).show()

    }

    fun save() {
        val text: String = "Be Strong"
        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput(file_name, MODE_PRIVATE)
            fos.write(text.toByteArray())

            Toast.makeText(
                this, "Saved to $filesDir/$file_name",
                Toast.LENGTH_LONG
            ).show()

            Log.e("Its Time", filesDir.toString())
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

}