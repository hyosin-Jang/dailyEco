
package com.example.dailyeco;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Adapter Adapter;
    private RecyclerView recyclerView;
    private GridLayoutManager GridLayout;
    private ImageView icon;
    private TextView text;
    private int number=0;
    private SQLiteDatabase db;



    ArrayList<ListViewItem> mList = new ArrayList<ListViewItem>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 리사이클러뷰에 표시할 데이터 리스트 생성
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler1); // 리사이클러뷰 참조
        text = (TextView) findViewById(R.id.num);
        icon = (ImageView) findViewById(R.id.icon);

        //그리드 레이아웃, 한 행에 2개씩 나타내기로 결정
        GridLayout = new GridLayoutManager(this, 2);
        //리사이클러뷰의 아이텀뷰들을 그리드레이아웃으로 표시
        recyclerView.setLayoutManager(GridLayout);
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        Adapter = new Adapter(mList); // Adapter 클래스에 할당당
        recyclerView.setAdapter(Adapter);


        //db 생성
        db = (new DBHelper(this)).getReadableDatabase();

        //true인 것만 뽑아옴.
        Cursor result = db.rawQuery("SELECT * FROM tbl_goal WHERE type='table' AND is_active='true'",null);

        result.moveToFirst();

        for(int i=0 ; i<7 ; i++){
            String goal_name = result.getString(2);
            int id_goal = result.getInt(1);

            if (id_goal==1) {
                addItem(getDrawable(R.drawable.ic_1),
                        "1", goal_name);
            }




        }




        // 두 번째 아이템 추가.
        addItem(getDrawable(R.drawable.ic_7),
                "2", "안쓰는 플러그 뽑기");
        // 세 번째 아이템 추가.
        addItem(getDrawable(R.drawable.ic_3),
                "3", "배달 시 일회용 수저, 포크 받지 않기");
        // 네 번째 아이템 추가.
        addItem(getDrawable(R.drawable.ic_4),
                "3", "재활용 용기 씻어서 버리기");
        // 다섯 번째 아이템 추가.
        addItem(getDrawable(R.drawable.ic_5),
                "3", "외식할 때 잔반 남기지 않기");
        // 여섯 번째 아이템 추가.
        addItem(getDrawable(R.drawable.ic_6),
                "3", "가까운 층은 걸어서 올라가기");
        Adapter.notifyDataSetChanged();


    }


    public void addItem(Drawable icon, String num, String content) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setNum(num);
        item.setContent(content);

        mList.add(item);
    }



}