package info.androidabcd.plugins.custom;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import com.siin.One.MainActivity;
import com.siin.One.DataCenter;
import com.siin.One.SMSBook;
import com.siin.One.HashMapDetail.HashMapDetail_SMS;
import com.siin.One.HashMapDetail.HashMapDetail_PhoneBook;

import android.content.Context;
import com.siin.One.PreferenceManager;

import java.util.Map;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * This class echoes a string called from JavaScript.
 */
public class CordovaCustomPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getPhonebookList")) {
          
        }
        else if(action.equals("getListSize")){
            int result =0;
            ArrayList<SMSBook> list = DataCenter.getInstance().getSmsBookList();
            result=list.size();
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getSMS")){
            String numbers="";
            String texts="";
            int i = 0;
            ArrayList<SMSBook> list = DataCenter.getInstance().getSmsBookList();

            while(i < list.size()){
                numbers = numbers +"@"+list.get(i).getNumber();
                texts = texts +"@"+list.get(i).getText();
                i++;
            }

            callbackContext.success(numbers + "#" + texts);
            return true;
        }
        else if(action.equals("refreshPhoneBookDataBase")){
            String result = "";
            DataCenter.getInstance().refreshPhoneBookDataBase(MainActivity.getContextOfApplication());
            for(Map.Entry<String , HashMapDetail_PhoneBook> entrySet : DataCenter.getInstance().getPhoneBookHashMap().entrySet()){
                result = result + entrySet.getKey() +"--"+ entrySet.getValue().getName()+"@";
            }
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getTotalScore")){
            float result = 0;
            result = DataCenter.getInstance().getTotalScore();
            callbackContext.success(result + "");
            return true;
        }
        else if(action.equals("refreshSMSDataBase")){
            String result = "";
            DataCenter.getInstance().refreshSMSDataBase(MainActivity.getContextOfApplication());
            for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet: DataCenter.getInstance().getSmsHashMap().entrySet()){
                String temp = entrySet.getKey();
                ArrayList<HashMapDetail_SMS> tl = entrySet.getValue();
                int i = 0;
                while(i<tl.size()){
                    result = result + temp + "--" + tl.get(i).getBody()+"--"+ tl.get(i).getTimestamp() + "^&";
                    i++;
                }
            }
            callbackContext.success(result);
            return true;
        } 
        else if(action.equals("getBookedSMS")){
            String result = "";
            for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet : DataCenter.getInstance().getBookedSmsHashMap().entrySet()){
                String temp = entrySet.getKey();
                ArrayList<HashMapDetail_SMS> tl = entrySet.getValue();
                int i = 0;
                while(i<tl.size()){
                    result = result + temp + "--" + tl.get(i).getBody()+"--"+ tl.get(i).getTimestamp() + "^&";
                    i++;
                }
            }
            callbackContext.success(result);
            return true;
        }

        else if(action.equals("getNotBookedSMS")){
            String result = "";
            for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet : DataCenter.getInstance().getNotBookedSmsHashMap().entrySet()){
                String temp = entrySet.getKey();
                ArrayList<HashMapDetail_SMS> tl = entrySet.getValue();
                int i = 0;
                while(i<tl.size()){
                    result = result + temp + "--" + tl.get(i).getBody()+"--"+ tl.get(i).getTimestamp() + "^&";
                    i++;
                }
            }
            callbackContext.success(result);
            return true;
        }


        else if(action.equals("getURLSmsHashMap")){
            String result = "";
            for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet : DataCenter.getInstance().getURLSmsHashMap().entrySet()){
                String temp = entrySet.getKey();
                ArrayList<HashMapDetail_SMS> tl = entrySet.getValue();
                int i = 0;
                while(i<tl.size()){
                    result = result + temp + "--" + tl.get(i).getBody()+"--"+ tl.get(i).getTimestamp() + "^&";
                    i++;
                }
            }
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getWebSmsHashMap")){
            String result = "";
            for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet : DataCenter.getInstance().getWebSmsHashMap().entrySet()){
                String temp = entrySet.getKey();
                ArrayList<HashMapDetail_SMS> tl = entrySet.getValue();
                int i = 0;
                while(i<tl.size()){
                    result = result + temp + "--" + tl.get(i).getBody()+"--"+ tl.get(i).getTimestamp() + "^&";
                    i++;
                }
            }
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getZeroSevenSmsHashMap")){
            String result = "";
            for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet : DataCenter.getInstance().getZeroSevenSmsHashMap().entrySet()){
                String temp = entrySet.getKey();
                ArrayList<HashMapDetail_SMS> tl = entrySet.getValue();
                int i = 0;
                while(i<tl.size()){
                    result = result + temp + "--" + tl.get(i).getBody()+"--"+ tl.get(i).getTimestamp() + "^&";
                    i++;
                }
            }
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getAdSmsHashMap")){
            String result = "";
            for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet : DataCenter.getInstance().getAdSmsHashMap().entrySet()){
                String temp = entrySet.getKey();
                ArrayList<HashMapDetail_SMS> tl = entrySet.getValue();
                int i = 0;
                while(i<tl.size()){
                    result = result + temp + "--" + tl.get(i).getBody()+"--"+ tl.get(i).getTimestamp() + "^&";
                    i++;
                }
            }
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getOverseaSmsHashMap")){
            String result = "";
            for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet : DataCenter.getInstance().getOverseaSmsHashMap().entrySet()){
                String temp = entrySet.getKey();
                ArrayList<HashMapDetail_SMS> tl = entrySet.getValue();
                int i = 0;
                while(i<tl.size()){
                    result = result + temp + "--" + tl.get(i).getBody()+"--"+ tl.get(i).getTimestamp() + "^&";
                    i++;
                }
            }
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getBadSmsHashMap")){
            String result = "";
            for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet : DataCenter.getInstance().getBadSmsHashMap().entrySet()){
                String temp = entrySet.getKey();
                ArrayList<HashMapDetail_SMS> tl = entrySet.getValue();
                int i = 0;
                while(i<tl.size()){
                    result = result + temp + "--" + tl.get(i).getBody()+"--"+ tl.get(i).getTimestamp() + "^&";
                    i++;
                }
            }
            callbackContext.success(result);
            return true;
        }
        return false;
    }


    private void getPhonebookList(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void getListSize(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void getSMS(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void refreshSMSDataBase(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void refreshPhoneBookDataBase(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void getBookedSMS(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void getNotBookedSMS(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }




    private void getURLSmsHashMap(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void getWebSmsHashMap(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void getZeroSevenSmsHashMap(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void getAdSmsHashMap(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void getOverseaSmsHashMap(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void getBadSmsHashMap(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
