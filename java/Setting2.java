package com.example.dailyeco;
//메인액티비티
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.CompoundButton;


import com.example.dailyeco.R;

import java.util.Calendar;

public class Setting2 extends AppCompatActivity {

    public static TextView pushtext;
    public static Switch push;
    public static boolean isChecked;

    public static TextView env;
    public static TextView goalnum;
    public static TextView notice;
    public static AlarmManager alarmManager;
    public static PendingIntent pendingIntent;
    public static Calendar calendar;
    public static int hour, minute;
    public static ImageButton back;


    public static SharedPreferences sharedPreferences = null;
    public static SharedPreferences.Editor editor = null;
    public static String SHARE_NAME = "SHARE_PREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        pushtext = findViewById(R.id.pushtext);
        push = findViewById(R.id.push);
        notice = findViewById(R.id.notice);
        env = findViewById(R.id.env);
        goalnum = findViewById(R.id.goalnum);
        back = findViewById(R.id.back);

        //스위치 작동 관련
        push.setOnCheckedChangeListener(new pushListener());

        sharedPreferences = getSharedPreferences(SHARE_NAME,MODE_PRIVATE);
        isChecked = sharedPreferences.getBoolean("switch",isChecked);
        push.setChecked(isChecked);

        //환경 목표 횟수 변화 등 메뉴 클릭 시 화면 전환
        env.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), goal_color.class);
                startActivity(intent);
            }
        });
        goalnum.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), edit_goal_list.class);
                startActivity(intent);
            }
        });

        //뒤로 가기 버튼 클릭 시 화면 전환
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), MainActivity.class);
               startActivity(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        isChecked = push.isChecked();
        editor.putBoolean("switch",isChecked);
        editor.commit();

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void alarmBroadcastReceiver(){
        Intent alarmBroadcastReceiverIntent = new Intent(this, AlarmBroadCastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,alarmBroadcastReceiverIntent,0);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            hour = 10;

        }

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        long intervalDay = 12*60*60*1000; //12시간

        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, selectTime, intervalDay,pendingIntent);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+intervalDay,pendingIntent);
        //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+1000,pendingIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            CharSequence name = "알림설정에서의 제목";
            String description = "Oreo Version 이상을 위한 알림(알림설정에서의 설명)";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("channel_id", name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);


        }
    }

    public void unregist(){ //알림설정 해제
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmBroadcastReceiverIntent = new Intent(this, AlarmBroadCastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,alarmBroadcastReceiverIntent,0);
        alarmManager.cancel(pendingIntent);
    }

    class pushListener implements CompoundButton.OnCheckedChangeListener {


        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isChecked = push.isChecked();

            if (!isChecked) { //off 체크
                String disabledcolor = "#AEAEAE";
                unregist();

                pushtext.setTextColor(Color.parseColor(disabledcolor));
                notice.setTextColor(Color.parseColor(disabledcolor));
                env.setTextColor(Color.parseColor(disabledcolor));
                goalnum.setTextColor(Color.parseColor(disabledcolor));

            }
            else { //on 체크
                createNotificationChannel();
                alarmBroadcastReceiver();

                String enabledcolor = "#000000";
                String darkred = "#FFCC0000";
                pushtext.setTextColor(Color.parseColor(enabledcolor));
                notice.setTextColor(Color.parseColor(darkred));
                env.setTextColor(Color.parseColor(enabledcolor));
                goalnum.setTextColor(Color.parseColor(enabledcolor));


            }

        }
    }


}