/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.siin.One;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import org.apache.cordova.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CordovaActivity
{
    String shared = "file";
    public static Context contextOfApplication;
    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    private String[] permissions ={
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_MMS,
            Manifest.permission.RECEIVE_WAP_PUSH
    };
    private List permissionList;
    
    Python py = Python.getInstance();

    PyObject pyo = py.getModule("init");
    String a = "엉엉";

    PyObject pyo2 = py.getModule("check");
    String b = pyo2.callAttr("main",a).toString();


    void getPermissions(){
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_MMS,
                        Manifest.permission.RECEIVE_WAP_PUSH
                },
                1000);
        goSetting();
    }

    void showDialog() {
        AlertDialog.Builder msgBuilder =
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("권한 받기")
                    .setMessage("다른 앱 위에 그리기 권한에서 One앱을 허용해주세요")
                    .setPositiveButton("권한 받기", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                            getPermissions();
                        }
                    }) .setNegativeButton("앱 종료", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
                    });
        AlertDialog msgDlg = msgBuilder.create(); msgDlg.show();
    }


    public void goSetting() {  //버튼을 클릭 했을 때
        Intent intent= new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        startActivityForResult(intent,0);   //startActivityForResult() 는 호출한 Activity로 부터 결과를 받을 경우 사용.
    }

    // 설정 화면을 갔다오면 실행되는 메소드
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();

        int result;
        permissionList = new ArrayList<>();
        for(String pm : permissions){
            result = ContextCompat.checkSelfPermission(this, pm);
            if(result != PackageManager.PERMISSION_GRANTED){
                permissionList.add(pm);
            }
        }

        if(!permissionList.isEmpty()){
            showDialog();
        }

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        String number = "";
        String text = "";
        ArrayList<SMSBook> list = new ArrayList<SMSBook>();
        list = DataCenter.getInstance().getSmsBookList();
        int i = 0;
        int max = list.size();
        while(i<max){
            number = number + list.get(i).getNumber() + "#";
            text = text + list.get(i).getText() + "#";
            i++;
        }

        PreferenceManager.setString(this,"number", number);
        PreferenceManager.setString(this,"text", text);
        PreferenceManager.setInt(this,"size", list.size());
        list.clear();
    }

//    private long time= 0;
//
//    @Override
//
//    public void onBackPressed(){
//
//        if(System.currentTimeMillis() - time >= 2000){
//            time=System.currentTimeMillis();
//            Toast.makeText(getApplicationContext(),"한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
//        } else if(System.currentTimeMillis() - time < 2000 ){
//            finish();
//        }
//    }
}
