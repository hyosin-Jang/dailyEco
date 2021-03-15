package com.example.edit_goal2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class icon_select extends AppCompatActivity {

    private RadioButton rg_btn1, rg_btn2, rg_btn3, rg_btn4, rg_btn5, rg_btn6, rg_btn7, rg_btn8, rg_btn9, rg_btn10, rg_btn11, rg_btn12, rg_btn13, rg_btn14;
    private RadioGroup radioGroup;
    private TextView goal;
    private Button btn_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icon_select);

        Intent intent_2 = getIntent();
        String pos = intent_2.getStringExtra("position");
        int position = Integer.parseInt(pos);


        rg_btn1 = (RadioButton) findViewById(R.id.rg_btn1);
        rg_btn2 = (RadioButton) findViewById(R.id.rg_btn2);
        rg_btn3 = (RadioButton) findViewById(R.id.rg_btn3);
        rg_btn4 = (RadioButton) findViewById(R.id.rg_btn4);
        rg_btn5 = (RadioButton) findViewById(R.id.rg_btn5);
        rg_btn6 = (RadioButton) findViewById(R.id.rg_btn6);
        rg_btn7 = (RadioButton) findViewById(R.id.rg_btn7);
        rg_btn8 = (RadioButton) findViewById(R.id.rg_btn8);
        rg_btn9 = (RadioButton) findViewById(R.id.rg_btn9);
        rg_btn10 = (RadioButton) findViewById(R.id.rg_btn10);
        rg_btn11 = (RadioButton) findViewById(R.id.rg_btn11);
        rg_btn12 = (RadioButton) findViewById(R.id.rg_btn12);
        rg_btn13 = (RadioButton) findViewById(R.id.rg_btn13);
        rg_btn14 = (RadioButton) findViewById(R.id.rg_btn14);

        goal = findViewById(R.id.tv_goal);
        btn_arrow = findViewById(R.id.btn_arrow);

        btn_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(icon_select.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //라디오 그룹 설정
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_btn1:
                        goal.setText("@drawable/tumbler_on");
                        break;

                    case R.id.rg_btn2:
                        goal.setText("@drawable/reusablebag_on");
                        break;

                    case R.id.rg_btn3:
                        goal.setText("@drawable/delivery_on");
                        break;

                    case R.id.rg_btn4:
                        goal.setText("@drawable/washing_on");
                        break;

                    case R.id.rg_btn5:
                        goal.setText("@drawable/walking_on");
                        break;

                    case R.id.rg_btn6:
                        goal.setText("@drawable/leftover_on");
                        break;

                    case R.id.rg_btn7:
                        goal.setText("@drawable/stairs_on");
                        break;

                    case R.id.rg_btn8:
                        goal.setText("@drawable/pull_plug_on");
                        break;

                    case R.id.rg_btn9:
                        goal.setText("@drawable/teethcup_on");
                        break;

                    case R.id.rg_btn10:
                        goal.setText("@drawable/receipt_on");
                        break;

                    case R.id.rg_btn11:
                        goal.setText("@drawable/pet_on");
                        break;

                    case R.id.rg_btn12:
                        goal.setText("@drawable/house_on");
                        break;

                    case R.id.rg_btn13:
                        goal.setText("@drawable/leaf_on");
                        break;

                    case R.id.rg_btn14:
                        goal.setText("@drawable/meal_on");
                        break;


                }
            }
        });

    }


}
