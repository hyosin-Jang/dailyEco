package com.example.dailyeco;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.loader.ResourcesProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "dailyeco.db";


    public DBHelper(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);


    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        if (!db.isReadOnly()) {
        // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }

        //데이터베이스 생성될 때 호출
        // 데이터베이스 -> 테이블 -> 컬럼 -> 값

        //daily 기록 테이블
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_dailynum (id_dailynum INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, doc_date String, id_goal INTEGER, daily_count INTEGER(10), FOREIGN KEY(id_goal) REFERENCES tbl_goal(id_goal))");



        db.execSQL("INSERT into tbl_dailynum\n" +
                "(doc_date,id_goal,daily_count)\n" +
                "VALUES('2021-03-24',1,6);");



        //환경실천목표 테이블
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_goal (id_goal INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name_goal String UNIQUE, icon_goal BLOB, is_active INTEGER)");



        //목표횟수변경 테이블
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_goalcount (id_count INTEGER PRIMARY KEY AUTOINCREMENT, green_count INTEGER NOT NULL, yellow_count INTEGER NOT NULL)");

        db.execSQL("INSERT into tbl_goalcount(\n" +
                "green_count, yellow_count)\n" +
                "VALUES(5,3);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);


    }


    //daily 기록 select문
    public ArrayList<Dailylist> getDailylist(String date) {
        ArrayList<Dailylist> dailylists = new ArrayList<>();


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_dailynum JOIN tbl_goal ON tbl_dailynum.id_goal = tbl_goal.id_goal WHERE doc_date =" + date,null);

        System.out.println(cursor.getCount());

        if(cursor.getCount()!=0){
            //조회된 데이터가 있을 때 내부수행

            int sum=0;
            while(cursor.moveToNext()){
                String name_goal=cursor.getString(cursor.getColumnIndex("name_goal"));
                int daily_count=cursor.getInt(cursor.getColumnIndex("daily_count"));

                Dailylist dailylist = new Dailylist();
                dailylist.setName_goal(name_goal);
                dailylist.setDaily_count(daily_count);

                sum+=daily_count;
                dailylist.setTotal_count(sum);
                dailylists.add(dailylist);

            }

        }
        cursor.close();

        return dailylists;
    }


    //daily 기록 insert문
    public void Insert_tbl_dailynum(String _doc_date, int _id_goal, int _daily_count){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tbl_dailynum (doc_date, id_dailynum, daily_count) VALUES('"+_doc_date+"', "+_id_goal+", "+_daily_count+")");

    }


    //목표횟수를 수정하는 update문
    public void Update_tbl_goalcount(int _green_count, int _yellow_count){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE tbl_goalcount SET green_count = "+_green_count+", yellow_count = "+_yellow_count+"" );

    }


    //환경실천목표 테이블에서 icon은 iconBitmap 함수를 이용해서 byte 형식으로 입력
    //환경실천목표를 추가하는 insert문
    public void Insert_tbl_goal(String _name_goal, byte[] _icon_goal, int _is_active){
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("INSERT OR IGNORE INTO tbl_goal (name_goal, icon_goal, is_active) VALUES ('"+_name_goal+"', '"+_icon_goal+"',"+_is_active+")");
        String sql="INSERT OR REPLACE INTO tbl_goal (name_goal, icon_goal, is_active) VALUES (?,?,?)";
        SQLiteStatement insertSt = db.compileStatement(sql);
        insertSt.bindString(1,_name_goal);
        insertSt.bindBlob(2,_icon_goal);
        insertSt.bindLong(3,_is_active);
        insertSt.executeInsert();
    }

    //환경실천목표 추가화면의 6개 리스트를 수정하는 update문
    public void Update_tbl_goal(String _name_goal, byte[] _icon_goal, int _is_active){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("UPDATE tbl_goal SET is_active="+_is_active+", _icon_goal='"+_icon_goal+"' WHERE name_goal = '"+_name_goal+"'");
    }


}
