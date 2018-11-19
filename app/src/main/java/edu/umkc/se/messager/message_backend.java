package edu.umkc.se.messager;


import android.telephony.SmsManager;


public class message_backend {
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
