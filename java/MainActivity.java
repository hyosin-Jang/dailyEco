package com.example.dailyeco;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DBHelper mDBHelper;
    private ArrayList<Dailylist> mDailylist;
    private ArrayList<RecyclerData> arrayList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DBHelper(this);
        mDailylist = new ArrayList<>();

        //map recyclerview
        recyclerView = findViewById(R.id.list_item);

        //제한된 사이즈임을 알림.
        recyclerView.setHasFixedSize(true);

        //리사이클러뷰에 LinearLayoutManager 객체 지정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        //RecyclerData recyclerData = new RecyclerData("텀블러 사용하기","2");
        //arrayList.add(recyclerData);
        //RecyclerData recyclerData2 = new RecyclerData("장바구니 사용하기","3");
        //arrayList.add(recyclerData2);
        //RecyclerData recyclerData3 = new RecyclerData("분리수거","5");
        //arrayList.add(recyclerData3);


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
                new EventDecorator(Color.RED, Collections.singleton(CalendarDay.today()))
        );

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year, month, day;
                year=date.getYear();
                month=date.getMonth()+1;
                day=date.getDay();
                textView.setText(year+"년 "+month+"월 "+day+"일");

                Date datetext;
                datetext= date.getDate();

                //mDBHelper.getDailylist(datetext, 1);

                /*
                String namegoal;
                String num;
                for(int i=0;i<mDailylist.size();i++){
                    namegoal=mDailylist.get(i).getName_goal();
                    num=mDailylist.get(i).getDaily_count();
                    RecyclerData recyclerData = new RecyclerData(namegoal,num);
                    arrayList.add(recyclerData);
                }

                 */

            }
        });
    }


}