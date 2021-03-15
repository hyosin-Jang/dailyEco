package com.example.edit_goal2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DBHelper DBHelper;
    GoalData data;
    private EditText edit_goal, goal2;
    private ArrayList<GoalData> mArrayList;
    private CustomAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_goal);

        edit_goal = findViewById(R.id.edit_add);
        goal2 = findViewById(R.id.goal2);
        //데이터 베이스 관련 코드
        //DBHelper = new DBHelper(this);
        //

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);



        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        Intent intent = getIntent();

        Button arrow_left_r = (Button)findViewById(R.id.btn_arrow_r);
        arrow_left_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_setting = new Intent(MainActivity.this, Setting.class);
                startActivity(intent_to_setting);

            }
        });

        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data = new GoalData(edit_goal.getText().toString(), R.drawable.leaf_on, 0);

                mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입
                mAdapter.notifyDataSetChanged();

                //데이터 베이스 연결
                //DBHelper.Insert_tbl_goal(mArrayList.get(mArrayList.size()-1.getGoal, mArrayList.get(mArrayList.size()-1.getIcon, is_active);
                //이즈 액티브 변수 수정

                }
        });



    }

}