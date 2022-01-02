package info.androidwekat.plugins.custom;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.siin.One.MainActivity;
import com.siin.One.PreferenceManager;

/**
 * This class echoes a string called from JavaScript.
 */
public class CordovaCustomAuthPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("CordovaUidSave")) {
            //uid저장하기
            String uid = args.getString(0);
            PreferenceManager.setString(MainActivity.getContextOfApplication(),"uid",uid);
            this.CordovaUidSave(uid, callbackContext);
            return true;

        }else if (action.equals("CordovaUidCheck")) {
            //uid체크하기
            boolean checkAccountExist = false;
            String uid = "";
            if(PreferenceManager.checkKeyContain(MainActivity.getContextOfApplication(),"uid")){
                checkAccountExist = true;
                uid = PreferenceManager.getString(MainActivity.getContextOfApplication(),"uid");
            }
            this.CordovaUidCall(checkAccountExist, uid,callbackContext);
            return true;
        }
        return false;
    }

    private void CordovaUidSave(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void CordovaUidCall(boolean check, String uid,CallbackContext callbackContext) {
        if(check == true){
            callbackContext.success(uid);
        }else{
            callbackContext.success("false");
        }
    }

}
