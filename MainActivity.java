package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    DBHelper DBHelper;
    SQLiteDatabase db;
    CheckBox ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10;
    TextView txt_result;
    Button btn_save;
    ImageView ch1_yellow, ch1_green;
    ImageView ch2_yellow, ch2_green;
    ImageView ch3_yellow, ch3_green;
    ImageView ch4_yellow, ch4_green;
    ImageView ch5_yellow, ch5_green;
    ImageView ch6_yellow, ch6_green;
    ImageView ch7_yellow, ch7_green;
    ImageView ch8_yellow, ch8_green;
    ImageView ch9_yellow, ch9_green;
    ImageView ch10_yellow, ch10_green;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ch1 = findViewById(R.id.ch1);
        ch2 = findViewById(R.id.ch2);
        ch3 = findViewById(R.id.ch3);
        ch4 = findViewById(R.id.ch4);
        ch5 = findViewById(R.id.ch5);
        ch6 = findViewById(R.id.ch6);
        ch7 = findViewById(R.id.ch7);
        ch8 = findViewById(R.id.ch8);
        ch9 = findViewById(R.id.ch9);
        ch10 = findViewById(R.id.ch10);
        ch1_yellow = findViewById(R.id.ch1_yellow);
        ch2_yellow = findViewById(R.id.ch2_yellow);
        ch3_yellow = findViewById(R.id.ch3_yellow);
        ch4_yellow = findViewById(R.id.ch4_yellow);
        ch5_yellow = findViewById(R.id.ch5_yellow);
        ch6_yellow = findViewById(R.id.ch6_yellow);
        ch7_yellow = findViewById(R.id.ch7_yellow);
        ch8_yellow = findViewById(R.id.ch8_yellow);
        ch9_yellow = findViewById(R.id.ch9_yellow);
        ch10_yellow = findViewById(R.id.ch10_yellow);

        ch1_green = findViewById(R.id.ch1_green);
        ch2_green = findViewById(R.id.ch2_green);
        ch3_green = findViewById(R.id.ch3_green);
        ch4_green = findViewById(R.id.ch4_green);
        ch5_green = findViewById(R.id.ch5_green);
        ch6_green = findViewById(R.id.ch6_green);
        ch7_green = findViewById(R.id.ch7_green);
        ch8_green = findViewById(R.id.ch8_green);
        ch9_green = findViewById(R.id.ch9_green);
        ch10_green = findViewById(R.id.ch10_green);



        txt_result = findViewById(R.id.txt_result);

        btn_save = findViewById(R.id.btn_save); //변수에 아이디 할당 -> 개체와 연결

        DBHelper = new DBHelper(this);
        btn_save.setOnClickListener(new View.OnClickListener() {//btn_result 클릭됐을 때 액션
            @Override
            public void onClick(View v) {
                //만약 save 버튼의 텍스트가 수정일 경우
                if (btn_save.getText().equals("수정"))
                {//이미지를 뒤에 깔고 background로? visibility로 조절?
                    //깔려있던 기존 기준의 색깔 >> 안보이도록 하고
                    //버튼을 모두 unchecked 상태로 초기화하기
                    ch1.setChecked(false);
                    ch1_yellow.setVisibility(View.GONE);
                    ch1_green.setVisibility(View.GONE);
                    ch2.setChecked(false);
                    ch2_yellow.setVisibility(View.GONE);
                    ch2_green.setVisibility(View.GONE);
                    ch3.setChecked(false);
                    ch3_yellow.setVisibility(View.GONE);
                    ch3_green.setVisibility(View.GONE);
                    ch4.setChecked(false);
                    ch4_yellow.setVisibility(View.GONE);
                    ch4_green.setVisibility(View.GONE);
                    ch5.setChecked(false);
                    ch5_yellow.setVisibility(View.GONE);
                    ch5_green.setVisibility(View.GONE);
                    ch6.setChecked(false);
                    ch6_yellow.setVisibility(View.GONE);
                    ch6_green.setVisibility(View.GONE);
                    ch7.setChecked(false);
                    ch7_yellow.setVisibility(View.GONE);
                    ch7_green.setVisibility(View.GONE);
                    ch8.setChecked(false);
                    ch8_yellow.setVisibility(View.GONE);
                    ch8_green.setVisibility(View.GONE);
                    ch9.setChecked(false);
                    ch9_yellow.setVisibility(View.GONE);
                    ch9_green.setVisibility(View.GONE);
                    ch10.setChecked(false);
                    ch10_yellow.setVisibility(View.GONE);
                    ch10_green.setVisibility(View.GONE);
                    btn_save.setText("저장");

                }


                else
                {
                    //save 버튼의 텍스트가 저장일 경우

                    int[] criteria = new int[2];
                    int i =0;

                    //주의)if문 안에서 criteria에 추가를 못하게 하는게 아니라 아예 check가 안되도록 해야함!!!!
                    if (ch1.isChecked())//1이 체크되었을 때
                    {
                        if(i < 2) criteria[i++] = 1;
                        else ch1.setChecked(false);

                    }
                    //여기서 처음에 if-else 문으로 분기시켰는데 이렇게 하면 여러개가 체크가 안됨. 꼭 각각의 if문으로 사용헤야함
                    if (ch2.isChecked())
                    {
                        if(i < 2) criteria[i++] = 2;
                        else ch2.setChecked(false);

                    }
                    if (ch3.isChecked())
                    {
                        if(i < 2) criteria[i++] = 3;
                        else ch3.setChecked(false);
                    }
                    if (ch4.isChecked())
                    {
                        if(i < 2) criteria[i++] = 4;
                        else ch4.setChecked(false);
                    }

                    if (ch5.isChecked())
                    {
                        if(i < 2) criteria[i++] = 5;
                        else ch5.setChecked(false);
                    }
                    if (ch6.isChecked())
                    {
                        if(i < 2) criteria[i++] = 6;
                        else ch6.setChecked(false);
                    }
                    if (ch7.isChecked())
                    {
                        if(i < 2) criteria[i++] = 7;
                        else ch7.setChecked(false);
                    }
                    if (ch8.isChecked())
                    {
                        if(i < 2) criteria[i++] = 8;
                        else ch8.setChecked(false);
                    }
                    if (ch9.isChecked())
                    {
                        if(i < 2) criteria[i++] = 9;
                        else ch9.setChecked(false);
                    }
                    if (ch10.isChecked())
                    {
                        if(i < 2) criteria[i++] = 10;
                        else ch10.setChecked(false);
                    }

                    if(criteria[0] > criteria[1])
                    {
                        int tmp = criteria[0];
                        criteria[0] = criteria[1];
                        criteria[1] = tmp;
                    }//배열 정렬 -> criteria[0]의 숫자 이미지를 노란색으로, criteria[1]를 초록색으로 변환해줘야함

                    switch (criteria[0])
                    {
                        case 1:
                            ch1_yellow.setVisibility(View.VISIBLE);
                            break;

                        case 2:
                            ch2_yellow.setVisibility(View.VISIBLE);
                            break;

                        case 3:
                            ch3_yellow.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            ch4_yellow.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            ch5_yellow.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            ch6_yellow.setVisibility(View.VISIBLE);
                            break;
                        case 7:
                            ch7_yellow.setVisibility(View.VISIBLE);
                            break;
                        case 8:
                            ch8_yellow.setVisibility(View.VISIBLE);
                            break;
                        case 9:
                            ch9_yellow.setVisibility(View.VISIBLE);
                            break;
                        case 10:
                            ch10_yellow.setVisibility(View.VISIBLE);
                            break;

                    }
                    switch (criteria[1])
                    {
                        case 1:
                            ch1_green.setVisibility(View.VISIBLE);
                            break;

                        case 2:
                            ch2_green.setVisibility(View.VISIBLE);
                            break;

                        case 3:
                            ch3_green.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            ch4_green.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            ch5_green.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            ch6_green.setVisibility(View.VISIBLE);
                            break;
                        case 7:
                            ch7_green.setVisibility(View.VISIBLE);
                            break;
                        case 8:
                            ch8_green.setVisibility(View.VISIBLE);
                            break;
                        case 9:
                            ch9_green.setVisibility(View.VISIBLE);
                            break;
                        case 10:
                            ch10_green.setVisibility(View.VISIBLE);
                            break;

                    }

                    //txt_result.setText(Integer.toString(criteria[0]) + Integer.toString(criteria[1])+"정상작동");

                    //db = DBHelper.getWritableDatabase();

                    DBHelper.Update_tbl_goalcount(criteria[1], criteria[0]);
                    //db.close();

                    if(criteria[0]!=0 && criteria[1]!=0)
                    {
                        btn_save.setText("수정");
                    }

                }





            }
        });


    }
}
