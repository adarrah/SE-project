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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

public class message_interface extends AppCompatActivity {
    static EditText phone_text, outgoing_message;
    static TextView sent_text, incoming_text;
    private static final int SEND_SMS_PERMISSIONS_REQUEST = 1;
    private static final int MODIFY_PHONE_STATE_PERMISSIONS_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_interface);
        sent_text = (TextView) findViewById(R.id.sent_text);
        incoming_text = (TextView) findViewById(R.id.incoming_text);
        Button send_message;
        phone_text = (EditText) findViewById(R.id.phoneText);
        send_message = (Button) findViewById(R.id.send_button);
        outgoing_message = (EditText) findViewById(R.id.outgoing_text);
        final Context context = this;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED) {
            getPermissionToReadSMS();
        }


        send_message.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String outgoing_message_string = outgoing_message.getText().toString();
                String phone_number_string = phone_text.getText().toString();
               if(message_backend.sendMessage(phone_number_string, outgoing_message_string)){
                   outgoing_message.setText("");
                   setSentText(outgoing_message_string);
                   setPhoneText(phone_number_string);
               }
               else{
                   Toast.makeText(context, "There was an error", Toast.LENGTH_SHORT).show();
               }



            }
        });
    }



    public static void pushReceiedMessageToScreen(String message, String phoneNo) {
        setPhoneText(phoneNo);
        setIncomingText(message);
    }

    private static void setSentText(String textToDisplay) {
        String temp = sent_text.getText().toString();
        sent_text.setText((temp + "\n" + textToDisplay));
    }

    private static void setIncomingText(String textToDisplay) {
        String temp = incoming_text.getText().toString();
        incoming_text.setText(temp + "\n" + textToDisplay);
    }

    private static void setPhoneText(String textToDisplay) {
        phone_text.setText(textToDisplay);
    }

    @TargetApi(23)
    public void getModifyPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.MODIFY_PHONE_STATE)) {
                Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.MODIFY_PHONE_STATE},
                    MODIFY_PHONE_STATE_PERMISSIONS_REQUEST);
        }
    }
    @TargetApi(23)
    public void getPermissionToReadSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.SEND_SMS)) {
                Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.SEND_SMS},
                    SEND_SMS_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == SEND_SMS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Send SMS permission granted", Toast.LENGTH_SHORT).show();
                //refreshSmsInbox();
            } else {
                Toast.makeText(this, "Send SMS permission denied", Toast.LENGTH_SHORT).show();
            }
            }
        if (requestCode == MODIFY_PHONE_STATE_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Modify Phone State permission granted", Toast.LENGTH_SHORT).show();
                //refreshSmsInbox();
            } else {
                Toast.makeText(this, "Modify Phone State permission denied", Toast.LENGTH_SHORT).show();
            }
        }
            else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



}


