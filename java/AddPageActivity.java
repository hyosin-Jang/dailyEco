
package com.example.dailyeco;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private Adapter Adapter;
    private RecyclerView recyclerView;
    private GridLayoutManager GridLayout;
    private ImageView icon;
    private TextView text;
    private int number=0;
    
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    ArrayList<ListViewItem> mList = new ArrayList<ListViewItem>();

    // *image 파일 - byte[] 변경
    public byte[] iconBitmap(int d) {
        
        Drawable drawable = ContextCompat.getDrawable(this,d);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
        byte[] img;
        img = byteArray.toByteArray();

        return img;
    }
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 리사이클러뷰에 표시할 데이터 리스트 생성
        super.onCreate(savedInstanceState);
        // xml을 객체화시키는 inflate 동작
        setContentView(R.layout.activity_main);

        /*setContetnView로 메모리에 올라와서 객체화됐기 때문에
         xml에 배치했던 UI 요소들을 끌어와서 쓸 수 있음 */

        recyclerView = findViewById(R.id.recycler1); // 리사이클러뷰 참조
        text = (TextView) findViewById(R.id.num);
        icon = (ImageView) findViewById(R.id.icon);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //그리드 레이아웃, 한 행에 2개씩 나타내기로 결정
        GridLayout = new GridLayoutManager(this, 2);
        //리사이클러뷰의 아이텀뷰들을 그리드레이아웃으로 표시
        recyclerView.setLayoutManager(GridLayout);
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        Adapter = new Adapter(mList); // Adapter 클래스에 할당
        recyclerView.setAdapter(Adapter);

        // DB open
        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();
        // 읽기모드로 데이터베이스를 오픈, tbl_goal 생성됨.

        // tbl_goal에 기본값 입력
        byte[] icon = iconBitmap(R.drawable.ic_1);
        dbHelper.Insert_tbl_goal("텀블러 사용하기",icon,1);
        icon=iconBitmap(R.drawable.ic_2);
        dbHelper.Insert_tbl_goal("장바구니 사용하기",icon,1);
        icon=iconBitmap(R.drawable.ic_3);
        dbHelper.Insert_tbl_goal("배달 시 일회용 수저, 포크 받지 않기",icon,1);
        icon=iconBitmap(R.drawable.ic_4);
        dbHelper.Insert_tbl_goal("재활용 용기 씻어서 버리기",icon,1);
        icon=iconBitmap(R.drawable.ic_5);
        dbHelper.Insert_tbl_goal("외식할 때 잔반 남기지 않기",icon,1);
        icon=iconBitmap(R.drawable.ic_6);
        dbHelper.Insert_tbl_goal("가까운 층은 걸어서 올라가기",icon,1);
        icon=iconBitmap(R.drawable.ic_7);
        dbHelper.Insert_tbl_goal("안쓰는 플러그 뽑기",icon,0);
        icon=iconBitmap(R.drawable.ic_8);
        dbHelper.Insert_tbl_goal("양치컵 사용하기",icon,0);

        selectData("tbl_goal");
        Adapter.notifyDataSetChanged();

        dbHelper.close();

    }

        // *select문으로 데이터 가져오기
        public void selectData(String tableName){
            if(database != null){
                String sql = "SELECT name_goal, icon_goal FROM "+ tableName +" WHERE is_active=1";
                Cursor cursor = database.rawQuery(sql,null);

                for(int i = 0 ; i <6 ; i++){
                    cursor.moveToNext(); // 다음 레코드로 넘어감
                    String name = cursor.getString(cursor.getColumnIndex("name_goal")); // name_goal 칼럼 뽑아줌
                    byte [] Icon = cursor.getBlob(cursor.getColumnIndex("icon_goal")); // icon 칼럼

                    // * byte[] => drawable 로 typecasting
                    Drawable drawable = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(Icon,0,Icon.length));
                    // 리사이클러뷰에 추가
                    addItem(drawable,"0",name);
                }
                cursor.close(); // cursor도 데이터베이스 접근하는 것이기 때문에 꼭 닫아준다.
            }
        }

        /*
        // 스트링을 바이너리 바이트 배열로
        public static byte[] binaryStringToByteArray(String s){
            int count = s.length()/8;
            byte [] b = new byte[count];
            for(int i =1 ; i<count; i++){
                String t = s.substring((i-1)*8,i*8);
                b[i-1] = binaryStringToByte(t);
            }
            return b;
        }

        // 스트링을 바이너리 바이트로
        public static byte binaryStringToByte(String s){
            byte ret =0,total=0;
            for(int i=0 ; i<8 ; i++){
                ret = (s.charAt(7-i) =='1')?(byte)(1<<i):0;
                total = (byte)(ret|total);
            }
            return total;
        }

        */
    
    public void addItem(Drawable icon, String num, String content) {
        ListViewItem item = new ListViewItem();
        item.setIcon(icon);
        item.setNum(num);
        item.setContent(content);
        mList.add(item);
    }
}
