package com.siin.One;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.text.SimpleDateFormat;
import java.util.Date;

public class popUpService extends Service {
  private static popUpService pus = null;
  private String body;
  private String number;
  private WindowManager wm;
  private View fancyLl;
  private Button stopBtn;
  private TextView bodyText;
  private TextView numberText;
  private TextView checkText;
  private RelativeLayout alert_layout;
  private TextView alert_textview;
  private TextView timeText;
  public popUpService() {
  }

  @Override
  public IBinder onBind(Intent intent) {
    // TODO: Return the communication channel to the service.
    throw new UnsupportedOperationException("Not yet implemented");
  }
  @Override
  public void onCreate() {

    wm = (WindowManager) getSystemService(WINDOW_SERVICE);

    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
    fancyLl = layoutInflater.inflate(R.layout.background_view, null);
    stopBtn = (Button)fancyLl.findViewById(R.id.closeBtn);
    bodyText = (TextView)fancyLl.findViewById(R.id.bodyText);
    numberText = (TextView)fancyLl.findViewById(R.id.numberText);
    checkText = (TextView)fancyLl.findViewById(R.id.checkText);
    alert_layout = (RelativeLayout)fancyLl.findViewById(R.id.alert_layout);
    alert_textview = (TextView)fancyLl.findViewById(R.id.alert_textview);
    timeText = (TextView)fancyLl.findViewById(R.id.timeText);

    WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(800,700,
      WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
      ,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);

    parameters.x = 0;
    parameters.y = -400;
    parameters.gravity = Gravity.CENTER | Gravity.CENTER;

    stopBtn.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        removePopup();
      }
    });


    wm.addView(fancyLl, parameters);
    super.onCreate();

  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    body = intent.getStringExtra("text");
    number = intent.getStringExtra("number");
    long timestamp = intent.getLongExtra("time",0);

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    String dateString = formatter.format(new Date(timestamp));

    bodyText.setText(body);
    numberText.setText(number);
    timeText.setText(dateString);

    if(number.equals("Information")
            || number.equals("15884000")
            || number.equals("0216666410")
            || number.equals("0220338500")
            || number.equals("114")
            || number.equals("16444174")
            || number.equals("15882588")
            || number.equals("16001522")
            || number.equals("007774477814706")
            || number.equals("15449000")
            || number.equals("18003400")
            || number.equals("15882588")
            || number.equals("15663355")
            || number.equals("15885000")
            || number.contains("한동대")
            || number.contains("<#>")
    ){

    }else{
      boolean checkMachine = false;

//      //Url
//      if(body.contains("www") || body.contains("http") ){
//        System.out.println("$$URL$$");
//        checkMachine = true;
//      }
//      //070 번호 문자
//      if(body.contains("070")){
//        System.out.println("$$070$$");
//        checkMachine = true;
//      }
//      //[Web 발신]
//      if(body.contains("[Web발신]")){
//        System.out.println("$$[Web발신]$$");
//        checkMachine = true;
//      }
//      //(광고)
//      if(body.contains("(광고)")){
//        System.out.println("$$광고$$");
//        checkMachine = true;
//      }
//      //[국외발신]
//      if(body.contains("[국외발신]")){
//        System.out.println("$$국외발신$$");
//        checkMachine = true;
//      }

      if(!Python.isStarted())
        Python.start(new AndroidPlatform(this));
      Python py = Python.getInstance();
      PyObject pyo2 = py.getModule("check");

      String b = pyo2.callAttr("main",body).toString();

      if(!b.equals("피싱이 아닙니다.")){
        Drawable drawable = getResources().getDrawable(R.drawable.layout_bg_only_top_radius_red);
        alert_layout.setBackground(drawable);
        alert_textview.setText(b);
        PreferenceManager.setString(this,""+timestamp,b);
        System.out.println("지ㅣㅣㅣㅣㅣㅣ야  " + ""+timestamp+ " "+ b);
//        if(checkMachine == false){
//          
//        }
      }
    }

//    SMSBook sb = new SMSBook();
//    sb.setNumber(number);
//    sb.setText(body);
//    DataCenter.getInstance().getSmsBookList().add(sb);

    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  public void removePopup() {
    wm.removeView(fancyLl);
    stopSelf();
  }
}
