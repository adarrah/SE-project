package edu.umkc.se.messager;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

public class message_interface extends AppCompatActivity {
    static TextView sent_text, incoming_text;
    static EditText phone_text, outgoing_message;
    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;
    static String outgoing_message_string, phone_number_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_interface);
        Button send_message;
        phone_text = (EditText) findViewById(R.id.phoneText);
        sent_text = (TextView) findViewById(R.id.sent_text);
        send_message = (Button) findViewById(R.id.send_button);
        outgoing_message = (EditText) findViewById(R.id.outgoing_text);
        incoming_text = (TextView) findViewById(R.id.incoming_text);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED) {
            getPermissionToReadSMS();
        }


        send_message.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                outgoing_message_string = outgoing_message.getText().toString();
                phone_number_string = phone_text.getText().toString();
               message_backend.sendMessage(phone_number_string, outgoing_message_string);
               setSentText(outgoing_message_string);
               setPhoneText(phone_number_string);


            }
        });
    }

    public static void pushReceiedMessageToScreen(String message, String phoneNo) {
        setPhoneText(phoneNo);
        setIncomingText(message);
    }

    private static void setSentText(String textToDisplay) {
        sent_text.setText(textToDisplay);
    }

    private static void setIncomingText(String textToDisplay) {
        incoming_text.setText(textToDisplay);
    }

    private static void setPhoneText(String textToDisplay) {
        phone_text.setText(textToDisplay);
    }


    @TargetApi(23)
    public void getPermissionToReadSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_SMS)) {
                Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_SMS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read SMS permission granted", Toast.LENGTH_SHORT).show();
                //refreshSmsInbox();
            } else {
                Toast.makeText(this, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



}


