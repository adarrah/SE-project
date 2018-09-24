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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_interface);
        final Context ctx = this;
        EditText outgoing_message;
        final TextView sent_text, incoming_message;
        Button send_message;

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        sent_text = (TextView) findViewById(R.id.sent_text);
        send_message =(Button) findViewById(R.id.send_button);
        outgoing_message = (EditText) findViewById(R.id.outgoing_text);
        final String outgoing_message_string = outgoing_message.getText().toString();
        send_message.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sendText(outgoing_message_string)){
                    sent_text.setText(outgoing_message_string);
                }
                else{
                    Toast.makeText(ctx, "Message Failed to send",Toast.LENGTH_SHORT);
                }

            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_interface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public static boolean sendText(String message){
        if(message.isEmpty())
            return false;
        else
            return true;
    }

}
