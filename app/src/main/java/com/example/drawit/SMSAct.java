package com.example.drawit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SMSAct extends AppCompatActivity {

    TextInputEditText phone_number,message;

    Button sendBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsact);
        phone_number=findViewById(R.id.phone_num);
        message=findViewById(R.id.text_msg);
        sendBtn=findViewById(R.id.send_btn);
        toolbar=findViewById(R.id.toolbar_sms);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phone_number.setText(getString(R.string.myPhoneNumber));
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(SMSAct.this, android.Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }else{
                    ActivityCompat.requestPermissions(SMSAct.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });

    }
    private void sendSMS() {
        String phone_num_str=phone_number.getText().toString();
        String msg=message.getText().toString();

        if(!msg.isEmpty()){
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(phone_num_str,null,msg,null,null);
            Toast.makeText(SMSAct.this,"Sent",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(SMSAct.this,"Enter Message",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }else{
            Toast.makeText(SMSAct.this,"Permission is denied",Toast.LENGTH_SHORT).show();
        }

    }
}