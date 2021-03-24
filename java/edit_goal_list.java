package com.example.dailyeco;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dailyeco.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class edit_goal_list extends AppCompatActivity {

    DBHelper DBHelper = new DBHelper(this);
    SQLiteDatabase database;
    GoalData data;
    private EditText edit_goal, goal2;
    private ArrayList<GoalData> mArrayList;
    private CustomAdapter mAdapter;

    public byte[] iconBitmap(int d){
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
        setContentView(R.layout.edit_goal);

        edit_goal = findViewById(R.id.edit_add);
        goal2 = findViewById(R.id.goal2);
        //데이터 베이스 관련 코드
        //DBHelper = new DBHelper(this);
        //

        Intent intent = getIntent();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);



        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);




        Button arrow_left_r = (Button)findViewById(R.id.btn_arrow_r);
        arrow_left_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_setting = new Intent(edit_goal_list.this, Setting2.class);
                startActivity(intent_to_setting);

            }
        });

        GoalData reusable_bag = new GoalData("장바구니 사용", R.drawable.reusablebag_on, 1);
        GoalData stairs = new GoalData("계단 이용", R.drawable.stairs_on, 1);
        GoalData tumbler = new GoalData("텀블러 사용", R.drawable.tumbler_on, 1);



        mArrayList.add(reusable_bag); // RecyclerView의 마지막 줄에 삽입
        mArrayList.add(stairs); // RecyclerView의 마지막 줄에 삽입
        mArrayList.add(tumbler); // RecyclerView의 마지막 줄에 삽입
        mAdapter.notifyDataSetChanged();

        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                data = new GoalData(edit_goal.getText().toString(), R.drawable.leaf_on, 1);

                mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입
                mAdapter.notifyDataSetChanged();



                //데이터 베이스 연결
                if(mArrayList.size()!=0)
                {
                    database = DBHelper.getWritableDatabase();
                    String str = mArrayList.get(mArrayList.size()-1).getGoal();
                    int icon = mArrayList.get(mArrayList.size()-1).getIcon();
                    DBHelper.Insert_tbl_goal(str, iconBitmap(icon), 1);
                    //이즈 액티브 변수 수정, string m byte, int

                }

                }
        });



    }

}