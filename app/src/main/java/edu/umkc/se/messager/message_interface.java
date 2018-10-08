package edu.umkc.se.messager;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    static EditText phone_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_interface);
        final Context ctx = this;
        EditText outgoing_message;
        Button send_message;

        phone_text = (EditText) findViewById(R.id.phoneText);
        sent_text = (TextView) findViewById(R.id.sent_text);
        send_message =(Button) findViewById(R.id.send_button);
        outgoing_message = (EditText) findViewById(R.id.outgoing_text);
        incoming_text = (TextView) findViewById(R.id.incoming_text);

        final String outgoing_message_string = outgoing_message.getText().toString();
        final String phone_number_string = phone_text.getText().toString();

        send_message.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(message_backend.sendMessage(phone_number_string, outgoing_message_string)){
                    setSentText(outgoing_message_string);
                }
                else{
                    Toast.makeText(ctx, "Message Failed to send",Toast.LENGTH_SHORT);
                }

            }
        });
    }

    public static void pushReceiedMessageToScreen(String message, String phoneNo){
        setPhoneText(phoneNo);
        setIncomingText(message);
    }

    private static void setSentText(String textToDisplay){
        sent_text.setText(textToDisplay);
    }

    private static void setIncomingText(String textToDisplay){
        incoming_text.setText(textToDisplay);
    }

    private static void setPhoneText(String textToDisplay){
        phone_text.setText(textToDisplay);
    }

}
