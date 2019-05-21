package com.example.sidra.messeging;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText number,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number= (EditText) findViewById(R.id.number);
        message= (EditText) findViewById(R.id.message);
    }

    public void click(View view) {
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissioncheck== PackageManager.PERMISSION_GRANTED){
            MyMessage();
        }else
        {
            ActivityCompat.requestPermissions(this, new  String[] {Manifest.permission.SEND_SMS},0);

        }
    }

    private void MyMessage() {
        String phonenumber = number.getText().toString().trim();
        String phonemessage = message.getText().toString().trim();
        if (!number.getText().toString().equals("")||!message.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber, null, phonemessage, null, null);
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this," enter number or message" ,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if(grantResults.length>=0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                } else {
                    Toast.makeText(this,"you dont granted",Toast.LENGTH_SHORT).show();
                }
            break;
        }
    }
}
