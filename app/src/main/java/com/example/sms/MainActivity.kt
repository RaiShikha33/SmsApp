package com.example.sms

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private lateinit var number : EditText
    private lateinit var message : EditText
    private lateinit var send: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        number= findViewById(R.id.number)
        message= findViewById(R.id.msg)
        send=findViewById(R.id.send)

        send.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){
                        sendSMS()
                    }else{
                        requestPermissions(Array<String>(1){Manifest.permission.SEND_SMS},1)
                    }
                }
            }

        })
    }
    private fun sendSMS(){
        val phoneNo =number.text.toString()
        val sms =message.text.toString()
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo,null ,sms,null,null)
            Toast.makeText(this,"Message Sent", Toast.LENGTH_LONG).show()
        } catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(this,"Message Failed to Send", Toast.LENGTH_SHORT).show()
        }

    }
}


