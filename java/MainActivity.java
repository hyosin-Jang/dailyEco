package com.example.dailyeco;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DBHelper mDBHelper;
    private ArrayList<Dailylist> mDailylist;
    private ArrayList<RecyclerData> arrayList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    public byte[] iconBitmap(int d) {
        Drawable drawable = ContextCompat.getDrawable(this,d);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
        byte[] img;
        img = byteArray.toByteArray();

        return img;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DBHelper(this);
        mDailylist = new ArrayList<>();

        //mDBHelper.Insert_tbl_dailynum("2021-03-24",1,6);

        //map recyclerview
        recyclerView = findViewById(R.id.list_item);

        //제한된 사이즈임을 알림.
        recyclerView.setHasFixedSize(true);

        //리사이클러뷰에 LinearLayoutManager 객체 지정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        //RecyclerData recyclerData = new RecyclerData("텀블러 사용하기",2);
        //arrayList.add(recyclerData);
        //RecyclerData recyclerData2 = new RecyclerData("장바구니 사용하기","3");
        //arrayList.add(recyclerData2);
        //RecyclerData recyclerData3 = new RecyclerData("분리수거","5");
        //arrayList.add(recyclerData3);



        byte[] icon = iconBitmap(R.drawable.ic_1);
        mDBHelper.Insert_tbl_goal("텀블러 사용하기",icon,1);
        icon=iconBitmap(R.drawable.ic_2);
        mDBHelper.Insert_tbl_goal("장바구니 사용하기",icon,1);
        icon=iconBitmap(R.drawable.ic_3);
        mDBHelper.Insert_tbl_goal("배달 시 일회용 수저, 포크 받지 않기",icon,1);
        icon=iconBitmap(R.drawable.ic_4);
        mDBHelper.Insert_tbl_goal("재활용 용기 씻어서 버리기",icon,1);
        icon=iconBitmap(R.drawable.ic_5);
        mDBHelper.Insert_tbl_goal("외식할 때 잔반 남기지 않기",icon,1);
        icon=iconBitmap(R.drawable.ic_6);
        mDBHelper.Insert_tbl_goal("가까운 층은 걸어서 올라가기",icon,1);
        icon=iconBitmap(R.drawable.ic_7_7);
        mDBHelper.Insert_tbl_goal("안쓰는 플러그 뽑기",icon,0);
        icon=iconBitmap(R.drawable.ic_8_8);
        mDBHelper.Insert_tbl_goal("양치컵 사용하기",icon,0);




        final TextView textView = findViewById(R.id.date);

        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2020,1,1))
                .setMaximumDate(CalendarDay.from(2100,12,31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        final OneDayDecorator oneDayDecorator = new OneDayDecorator();
        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator
        );




        materialCalendarView.addDecorator(

                new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(2021,3,24)))

        );

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year, month, day;
                year=date.getYear();
                month=date.getMonth()+1;
                day=date.getDay();
                //textView.setText(year+"년 "+month+"월 "+day+"일");

                String datetext;

                if(month<10 && day<10)
                    datetext=year+"-0"+month+"-0"+day;
                else if(month<10 && day>=10)
                    datetext=year+"-0"+month+"-"+day;
                else if(month>=10 && day<10)
                    datetext=year+"-"+month+"-0"+day;
                else
                    datetext=year+"-"+month+"-"+day;

                textView.setText(datetext);

                String namegoal;
                int num;

                for(int i=0;i<mDailylist.size();i++){
                    namegoal=mDailylist.get(i).getName_goal();
                    num=mDailylist.get(i).getDaily_count();
                    RecyclerData recyclerData = new RecyclerData(namegoal,num);
                    arrayList.add(recyclerData);
                }



            }
        });
    }


}