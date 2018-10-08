package edu.umkc.se.messager;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

public class message_backend {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    public static boolean sendMessage(String phoneNo, String message){
        try{
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNo, null, message,null,null);
            return true;

        }
        catch(Exception e){
            return false;
        }
    }
}
