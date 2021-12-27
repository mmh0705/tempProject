package com.siin.One;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MMSBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "asdf";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.WAP_PUSH_RECEIVED")){


//            Bundle bundle = intent.getExtras();
//            SmsMessage[] msgs = null;
//            String msg_from;
//            if(bundle != null){
//                try {
//                    Object[] pdus = (Object[]) bundle.get("pdus");
//                    msgs = new SmsMessage[pdus.length];
//                    for(int i = 0; i<msgs.length; i++){
//                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
//                        msg_from = msgs[i].getOriginatingAddress();
//                        String msgBody = msgs[i].getMessageBody();
//                        Intent serviceIntent = new Intent(context, popUpService.class);
//                        serviceIntent.putExtra("text", msgBody);
//                        serviceIntent.putExtra("number", msg_from);
//                        context.startService(serviceIntent);
//                        Toast.makeText(context.getApplicationContext(), ""+msgBody, Toast.LENGTH_SHORT).show();
//                    }
//                }catch (Exception e){
//                    Toast.makeText(context.getApplicationContext(), "something Wrong!"+e.toString(), Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                }
//            }

            //            Uri uri = Uri.parse("content://mms-sms/conversations/");
//            String[] proj = {"*"};
//            ContentResolver cr = context.getContentResolver();
//            Cursor c = cr.query(uri,proj,null,null,null);


        }
    }
}
