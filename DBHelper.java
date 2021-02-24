package com.example.dailyeco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "dailyeco.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //데이터베이스 생성될 때 호출
        // 데이터베이스 -> 테이블 -> 컬럼 -> 값

        //user정보 테이블
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_user (id_user INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name_suer VARCHAR(10), email_suer VARCHAR(20), passwd_user VARCHAR(20))");

        db.execSQL("INSERT into tbl_user\n" +
                "(name_user,email_user,passwd_user)\n" +
                "VALUES('장효신','ewha1234@gmail.com','ewha123!$');");
        //daily 기록 테이블
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_dailynum (id_dailynum INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, doc_date DATETIME, id_goal INTEGER, daily_count INTEGER(10), id_user INTEGER, FOREIGN KEY(id_goal) REFERENCES tbl_goal(id_goal), FOREIGN KEY(id_user) REFERENCES tbl_user(id_user))");


        db.execSQL("INSERT into tbl_dailynum\n" +
                "(doc_date,id_goal,daily_count,id_user)\n" +
                "VALUES('2021-02-24 15:30:00',1,6,1);");

        //환경실천목표 테이블
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_goal (id_goal INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name_goal VARCHAR(30), id_user INTEGER, is_active BOOLEAN, FOREIGN KEY(id_user) REFERENCES tbl_user(id_user))");

        db.execSQL("INSERT INTO tbl_goal (name_goal, id_user, is_active) VALUES" +
                "('텀블러 사용하기',1,true)," +
                "('장바구니 사용하기',1,true)," +
                "('배달 시 일회용 수저, 포크 받지 않기',1,true)," +
                "('재활용 용기 씻어서 버리기',1,true)," +
                "('외식할 때 잔반 남기지 않기',1,true)," +
                "('가까운 층은 걸어서 올라가기',1,true)," +
                "('안쓰는 플러그 뽑기',1,false)," +
                "('양치컵 사용하기',1,false)," +
                "('종이 영수증 받지 않기',1,false)," +
                "('페트병 뚜껑, 링, 비닐 분리해서 버리기',1,false)");

        //목표횟수변경 테이블
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_goalcount (id_count INTEGER PRIMARY KEY AUTOINCREMENT, green_count INTEGER NOT NULL, yellow_count INTEGER NOT NULL, id_user INTEGER, FOREIGN KEY(id_user) REFERENCES tbl_suer(id_user))");

        db.execSQL("INSERT into tbl_goalcount(\n" +
                "green_count, yellow_count,id_user)\n" +
                "VALUES(5,3,1);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    //daily 기록 insert문
    public void Insert_tbl_dailynum(int _id_dailynum, int _daily_num, int _id_user){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tbl_dailynum (id_dailynum, daily_num, id_user) VALUES('"+_id_dailynum+"', '"+_daily_num+"', '"+_id_user+"')");

    }

    //user정보 insert문
    public void Insert_tbl_user(int _id_user, String _name_user, String _email_user, String _passwd_user){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tbl_user (id_user, name_user, email_user, passwd_user) VALUES('"+_id_user+"', '"+_name_user+"', '"+_email_user+"', '"+_passwd_user+"')");

    }

    //목표횟수를 수정하는 update문
    public void Update_tbl_goalcount(int _green_count, int _yellow_count, int _id_user){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE tbl_goalcount SET green_count = '"+_green_count+"', yellow_count = '"+_yellow_count+"' WHERE id_user='"+_id_user+"'" );

    }

    //환경실천목표를 추가하는 insert문
    public void Insert_tbl_goal(String _name_goal, int _id_user, boolean _is_active){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tbl_goal (name_goal, id_user, is_active) VALUES ('"+_name_goal+"','"+_id_user+"'+'"+_is_active+"')");

    }

    //환경실천목표 추가화면의 6개 리스트를 수정하는 update문
    public void Update_tbl_goal(String _name_goal, int _id_user, boolean _is_active){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("UPDATE tbl_goal SET is_active='"+_is_active+"' WHERE id_user = '"+_id_user+"' AND name_goal = '"+_name_goal+"'");
    }



}
