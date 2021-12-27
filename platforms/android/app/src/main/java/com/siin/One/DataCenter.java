package com.siin.One;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import com.siin.One.HashMapDetail.HashMapDetail_PhoneBook;
import com.siin.One.HashMapDetail.HashMapDetail_SMS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DataCenter {
  ArrayList<SMSBook> smsBookList = new ArrayList<SMSBook>();
  HashMap<String, HashMapDetail_PhoneBook> phoneBookHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS> > smsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> bookedSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> notBookedSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> URLSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> webSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> zeroSevenSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> adSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> overseaSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> badSmsHashMap = new HashMap<>();


  float totalScore = 0;
  float tempSingleScore = 0;
  private int totalSMSNumber=0;
  private float singleFullScore = 100;

  public static DataCenter getInstance() {
    return instance;
  }
  private static DataCenter instance = new DataCenter();
  private DataCenter() {
  }
  public float getTotalScore(){
    return totalScore;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getURLSmsHashMap() {
    return URLSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getWebSmsHashMap() {
    return webSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getZeroSevenSmsHashMap() {
    return zeroSevenSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getAdSmsHashMap() {
    return adSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getOverseaSmsHashMap() {
    return overseaSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getSmsHashMap() {
    return smsHashMap;
  }
  public HashMap<String, HashMapDetail_PhoneBook> getPhoneBookHashMap() {
    return phoneBookHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getBookedSmsHashMap() {
    return bookedSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getNotBookedSmsHashMap() {
    return notBookedSmsHashMap;
  }

  public HashMap<String, ArrayList<HashMapDetail_SMS>> getBadSmsHashMap(){
    return badSmsHashMap;
  }
  public ArrayList<SMSBook> getSmsBookList() {
    return smsBookList;
  }
  public void refreshPhoneBookDataBase(Context context){
    phoneBookHashMap.clear();
    ContentResolver cr = context.getContentResolver();
    Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI , null ,null, null, null);

    if(cur.getCount()>0){
      while(cur.moveToNext()){
        int i = 0;
        HashMapDetail_PhoneBook pb = new HashMapDetail_PhoneBook();
        int id = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts._ID));
//                line = String.format("%4d",id);
        pb.setId(String.format("%4d",id));

        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                line += " " + name;
        pb.setName(name);

        if(("1").equals(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))) {
          Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{String.valueOf(id)}, null);

          int pCount = pCur.getCount();
          String[] phoneNum = new String[pCount];
          String[] phoneType = new String[pCount];

          while (pCur.moveToNext()) {
            phoneNum[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        line += " " + phoneNum[i];
            pb.setTel(phoneNum[i].replace("-", ""));
            phoneType[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
            pb.setPhoneType(phoneType[i]);
            i++;
          }
        }
        phoneBookHashMap.put(pb.getTel(), pb);
      }
    }
  }
  public void countSMSDataBase(Context context){
    long now = System.currentTimeMillis();
    long threeMonthBefore = now - 788940000; //1
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000; //5
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;//10

    totalScore = 0;
    Uri allMessage = Uri.parse("content://sms");
    ContentResolver cr = context.getContentResolver();
    Cursor c = cr.query(allMessage,
            new String[]{"_id", "thread_id", "address", "person", "date", "body"},
            null, null,
            "date DESC");
    while (c.moveToNext()) {
      //공지문자는 걸러주고
      if(c.getString(2).equals("Information")){
        continue;
      }
      //3개월 전이면 스킵해주고
      if(threeMonthBefore > c.getLong(4)){
        continue;
      }
      totalSMSNumber++;
    }
  }
  public void refreshSMSDataBase(Context context){
    int badCount = 0;
    long now = System.currentTimeMillis();

    //3달 전 날짜
    long threeMonthBefore = now - 788940000; //1
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000; //5
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;
    threeMonthBefore = threeMonthBefore - 788940000;//10

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    String dateString = formatter.format(new Date(threeMonthBefore));
//    Toast.makeText(context.getApplicationContext(), dateString, Toast.LENGTH_SHORT).show();


    if(!Python.isStarted())
      Python.start(new AndroidPlatform(MainActivity.getContextOfApplication()));
    Python py = Python.getInstance();
    PyObject pyo2 = py.getModule("check");

//    Toast.makeText(context.getApplicationContext(), b, Toast.LENGTH_SHORT).show();


    smsHashMap.clear();
    totalSMSNumber = 0;
    bookedSmsHashMap.clear();
    notBookedSmsHashMap.clear();
    URLSmsHashMap.clear();
    webSmsHashMap.clear();
    zeroSevenSmsHashMap.clear();
    adSmsHashMap.clear();
    overseaSmsHashMap.clear();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    Calendar cal = Calendar.getInstance();

    cal.add(Calendar.DATE,0);
    String today = sdf.format(cal.getTime());

    cal.add(Calendar.DATE, -7);
    String past = sdf.format(cal.getTime());

//    Toast.makeText(context.getApplicationContext(), today, Toast.LENGTH_SHORT).show();
//    Toast.makeText(context.getApplicationContext(), past, Toast.LENGTH_SHORT).show();

    //문자 갯수 세어준다 (
    countSMSDataBase(MainActivity.getContextOfApplication());
    System.out.println("!!!!!!!!!!"+ totalSMSNumber);
//    Toast.makeText(context.getApplicationContext(), totalSMSNumber, Toast.LENGTH_SHORT).show();

    Uri allMessage = Uri.parse("content://sms");
    ContentResolver cr = context.getContentResolver();
    Cursor c = cr.query(allMessage,
            new String[]{"_id", "thread_id", "address", "person", "date", "body"},
            null, null,
            "date DESC");

    int shitshit = 0;
    while (c.moveToNext()) {
      //3개월보다 오래되었으면 그냥 넘긴다.
      if(threeMonthBefore > c.getLong(4)){
        continue;
      }
      //긴급 문자도 걸러주고
      if(c.getString(2).equals("Information")){
        continue;
      }
      //정상적인 번호도 걸러주지만 점수는 더해준다.
      if( c.getString(2).equals("15884000")
              || c.getString(2).equals("0216666410")
              || c.getString(2).equals("0220338500")
              || c.getString(2).equals("114")
              || c.getString(2).equals("16444174")
              || c.getString(2).equals("15882588")
              || c.getString(2).equals("16001522")
              || c.getString(2).equals("007774477814706")
              || c.getString(2).equals("15449000")
              || c.getString(2).equals("18003400")
              || c.getString(2).equals("15882588")
              || c.getString(2).equals("15663355")
              || c.getString(2).equals("15885000")
              || c.getString(5).contains("한동대")
              || c.getString(5).contains("<#>")){
        shitshit++;
        totalScore = totalScore + singleFullScore;
        continue;
      }
      //만약 연락처에 있는 번호에서 온 문자라면,
      if(phoneBookHashMap.containsKey(c.getString(2))){
        //일단 문자 디테일 오브젝트 하나 만들고.
        HashMapDetail_SMS hmds = new HashMapDetail_SMS();

        long messageId = c.getLong(0);
        hmds.setMessageId(String.valueOf(messageId));

        long threadId = c.getLong(1);
        hmds.setThreadId(String.valueOf(threadId));

        String address = c.getString(2);
        hmds.setAddress(address);

        long contactId = c.getLong(3);
        hmds.setContactId(String.valueOf(contactId));

        String contactId_string = String.valueOf(contactId);
        hmds.setContactId_string(contactId_string);

        long timestamp = c.getLong(4);
        Date tempDay = new Date(timestamp);
        String formattedDate = new SimpleDateFormat("yyyy.MM.dd").format(tempDay);

        hmds.setTimestamp(String.valueOf(formattedDate));

        String body = c.getString(5);
        hmds.setBody(body);

        //####먼저 전체 문자 해쉬맵에 넣어준다####
          //만약 있는 번호에서 온 문자라면
        if(smsHashMap.containsKey(c.getString(2)) ){
          smsHashMap.get(c.getString(2)).add(hmds);
        }
          //새로운 번호에서 온 문자라면
        else {
          ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
          list.add(hmds);
          smsHashMap.put(c.getString(2),list);
        }


        //####그 다음 연락처에 있는 문자 해쉬맵에 넣어준다.####
        //만약 있는 번호에서 온 문자라면
        if(bookedSmsHashMap.containsKey(c.getString(2)) ){
          bookedSmsHashMap.get(c.getString(2)).add(hmds);
        }
        //새로운 번호에서 온 문자라면
        else {
          ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
          list.add(hmds);
          bookedSmsHashMap.put(c.getString(2),list);
        }

        //점수는 무조건 만점 더해준다.
        shitshit++;
        totalScore = totalScore + singleFullScore;
      }

      //만약 연락처에 없는 번호에서 온 문자라면, (전체 문자에 더해주고, 연락처에 없는 문자에 더해주며, 각종분류에 더해준다)
      else{
        boolean checkNoMachine = false;
        tempSingleScore = singleFullScore;

        //일단 문자 디테일 오브젝트 하나 만들고.
        HashMapDetail_SMS hmds = new HashMapDetail_SMS();

        long messageId = c.getLong(0);
        hmds.setMessageId(String.valueOf(messageId));

        long threadId = c.getLong(1);
        hmds.setThreadId(String.valueOf(threadId));

        String address = c.getString(2);
        hmds.setAddress(address);

        long contactId = c.getLong(3);
        hmds.setContactId(String.valueOf(contactId));

        String contactId_string = String.valueOf(contactId);
        hmds.setContactId_string(contactId_string);

        long timestamp = c.getLong(4);
        Date tempDay = new Date(timestamp);
        String formattedDate = new SimpleDateFormat("yyyy.MM.dd").format(tempDay);
        hmds.setTimestamp(String.valueOf(formattedDate));

        String body = c.getString(5);
        hmds.setBody(body);

        //####그 다음 전체 문자에 추가해준다.####
        //만약 있는 번호에서 온 문자라면
        if(smsHashMap.containsKey(c.getString(2)) ){
          smsHashMap.get(c.getString(2)).add(hmds);
        }
        //새로운 번호에서 온 문자라면
        else {
          ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
          list.add(hmds);
          smsHashMap.put(c.getString(2),list);
        }

        //####그 다음 연락처에 없는 문자 해쉬맵에 추가해준다.####
        //만약 있는 번호에서 온 문자라면
        if(notBookedSmsHashMap.containsKey(c.getString(2)) ){
          notBookedSmsHashMap.get(c.getString(2)).add(hmds);
        }
        //새로운 번호에서 온 문자라면
        else {
          ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
          list.add(hmds);
          notBookedSmsHashMap.put(c.getString(2),list);
        }

        //####그 다음 각종 특수 문자 계열에 넣어준다.####
        //Url
        if(hmds.getBody().contains("www") || hmds.getBody().contains("http") ){
          checkNoMachine = true;
          tempSingleScore = tempSingleScore * 5/10;
          if(URLSmsHashMap.containsKey(c.getString(2))){
            URLSmsHashMap.get(c.getString(2)).add(hmds);
          }else{
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            URLSmsHashMap.put(c.getString(2),list);
          }
        }
        //070 번호 문자
        if(hmds.getAddress().contains("070")){
          checkNoMachine = true;
          if(zeroSevenSmsHashMap.containsKey(c.getString(2))){
            zeroSevenSmsHashMap.get(c.getString(2)).add(hmds);
          }else{
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            zeroSevenSmsHashMap.put(c.getString(2),list);
          }
        }
        //[Web 발신]
        if(hmds.getBody().contains("[Web발신]")){
          checkNoMachine = true;
          tempSingleScore = tempSingleScore * 9/10;
          if(webSmsHashMap.containsKey(c.getString(2))){
            webSmsHashMap.get(c.getString(2)).add(hmds);
          }else {
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            webSmsHashMap.put(c.getString(2), list);
          }
        }
        //(광고)
        if(hmds.getBody().contains("(광고)")){
          checkNoMachine = true;
          tempSingleScore = 0;
          if(adSmsHashMap.containsKey(c.getString(2))){
            adSmsHashMap.get(c.getString(2)).add(hmds);
          }else {
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            adSmsHashMap.put(c.getString(2), list);
          }
        }
        //[국외발신]
        if(hmds.getBody().contains("[국외발신]")){
          checkNoMachine = true;
          tempSingleScore = tempSingleScore*5/10;
          if(overseaSmsHashMap.containsKey(c.getString(2))){
            overseaSmsHashMap.get(c.getString(2)).add(hmds);
          }else {
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            overseaSmsHashMap.put(c.getString(2), list);
          }
        }
        //머신러닝
        //        String a = "";

        if(PreferenceManager.checkKeyContain(MainActivity.getContextOfApplication(),""+c.getLong(4))){
          System.out.println("할러할러할러"+PreferenceManager.getString(MainActivity.getContextOfApplication(),""+c.getLong(4)));
          tempSingleScore = 0;
          hmds.setBody(hmds.getBody() + " ## " + PreferenceManager.getString(MainActivity.getContextOfApplication(),""+c.getLong(4)));
          if(badSmsHashMap.containsKey(c.getString(2))){
            badSmsHashMap.get(c.getString(2)).add(hmds);
          }else {
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            badSmsHashMap.put(c.getString(2), list);
          }
          badCount++;
        }else{
          System.out.println("아녀아녀아녀"+PreferenceManager.getString(MainActivity.getContextOfApplication(),""+c.getLong(4)));

          String b = pyo2.callAttr("main",hmds.getBody()).toString();
          if(!b.equals("피싱이 아닙니다.")){
            tempSingleScore = 0;
            hmds.setBody(hmds.getBody() + " ## " + b);
            if(badSmsHashMap.containsKey(c.getString(2))){
              badSmsHashMap.get(c.getString(2)).add(hmds);
            }else {
              ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
              list.add(hmds);
              badSmsHashMap.put(c.getString(2), list);
            }
            badCount++;
          }
        }
//        if(checkNoMachine == false){
//
//        }

        shitshit++;
        totalScore = totalScore+tempSingleScore;

      }
//      totalSMSNumber++;
//      Toast.makeText(context.getApplicationContext(), badCount, Toast.LENGTH_SHORT).show();

    }
    System.out.println("@@@@@@@@@@@"+shitshit);
    totalScore = totalScore/totalSMSNumber;
  }

}
