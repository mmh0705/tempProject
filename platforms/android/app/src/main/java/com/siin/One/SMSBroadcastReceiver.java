package com.siin.One;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSBroadcastReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {

    if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
      Bundle bundle = intent.getExtras();
      SmsMessage[] msgs = null;
      String msg_from;
      long msg_timestamp;
      if(bundle != null){
        try {
          Object[] pdus = (Object[]) bundle.get("pdus");
          msgs = new SmsMessage[pdus.length];
          for(int i = 0; i<msgs.length; i++){
            msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            msg_from = msgs[i].getOriginatingAddress();
            msg_timestamp = msgs[i].getTimestampMillis();

            String msgBody = msgs[i].getMessageBody();
            Intent serviceIntent = new Intent(context, popUpService.class);
            serviceIntent.putExtra("text", msgBody);
            serviceIntent.putExtra("number", msg_from);
            serviceIntent.putExtra("time",msg_timestamp);
            context.startService(serviceIntent);
          }
        }catch (Exception e){
          e.printStackTrace();
        }
      }
    }



  }
}
