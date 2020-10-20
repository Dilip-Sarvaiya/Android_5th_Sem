package com.rku.tutorial_13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Phone;
    EditText Sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Phone=findViewById(R.id.phone);
        Sms=findViewById(R.id.sms);
    }
    private  void sms()
    {
        String Phonenumber=Phone.getText().toString();
        String sms=Sms.getText().toString();
        SmsManager sms_to_be_send=SmsManager.getDefault();
        sms_to_be_send.sendTextMessage(Phonenumber,null,sms,null,null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    }
    public void callToNumber(View view) {
        if(Phone.getText().toString().length()==10) {
            if (isCallPermissonAllowed()) {
                Call();
            }
        }
        else 
        {
            Toast.makeText(this, "Please enter valid Mobile Number", Toast.LENGTH_SHORT).show();
        }
    }
    private void Call()
    {
        String Phonenumber = Phone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + Phonenumber));
        startActivity(intent);
    }
    private boolean isSmsPermissonAllowed() {
        if(Build.VERSION.SDK_INT>=23) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_GRANTED)
            {
                Log.v("TAG", "Permission Granted");
                sms();
                return true;
            }
            else
            {
                Log.v("TAG","Persmisson revoked");
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},12);
                return false;
            }
        }
        else
        {
            Log.v("TAG","Permisson Granted");
            return true;
        }
    }

    private boolean isCallPermissonAllowed() {
        if(Build.VERSION.SDK_INT>=23) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED)
            {
                Log.v("TAG", "Permission Granted");
                Call();
                return true;
            }
            else
            {
                Log.v("TAG","Persmisson revoked");
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},11);
                return false;
            }
        }
        else
        {
            Log.v("TAG","Permisson Granted");
            return true;
        }
    }

    public void sendTextMessage(View view) {
        if(Phone.getText().toString().equals("")  || Phone.getText().toString().length()!=10)
        {
            Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show();
        }
        else if(Sms.getText().toString().equals("")) {
            Toast.makeText(this, "Please type a message", Toast.LENGTH_SHORT).show();
        }
       else if(isSmsPermissonAllowed())
       {
           sms();
       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 11:
                if(grantResults.length>0
                        && grantResults[0]  == PackageManager.PERMISSION_GRANTED){
                    Call();
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;

            case 12:
                if(grantResults.length>0
                        && grantResults[0]  == PackageManager.PERMISSION_GRANTED){
                    sms();
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}