package com.example.setting2;
//background에서 push alarm Service동작하는 코드입니다.
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmIntentService extends IntentService {
    public final int NOTIFICATION_ID = 1001;
    public AlarmIntentService() {
        super("AlarmIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        new Intent(getApplicationContext().getApplicationContext(), /*기록추가화면 이름*/MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),0); //여기에 MainActivity.class를 기록추가 화면 자바파일.class로 바꾸면 될 것 같습니다.
        Bitmap mLargeIconForNoti = BitmapFactory.decodeResource(this.getResources(),R.drawable.launcher_icon);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.icon)
                .setLargeIcon(mLargeIconForNoti)
                .setContentTitle("오늘의 환경 실천 습관을 기록해주세요!")
                .setContentText("오늘의 색을 초록색으로 만들어보세요!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}

