package com.example.dailyeco;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    // 각 아이템 뷰에 들어갈 변수
    private ArrayList<ListViewItem> mData = null;
    ViewHolder viewHolder;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public Adapter (ArrayList<ListViewItem> list) {
        mData = list;
    }

    // 화면에 표시할 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView content,num;
        ImageView icon;
        int number=0;

        public ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            num = itemView.findViewById(R.id.num) ;
            content = itemView.findViewById(R.id.content) ;
            icon = itemView.findViewById(R.id.icon) ;
            number = Integer.parseInt(num.getText().toString());


            // 아이템 클릭 이벤트 처리
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Drawable tmp = icon.getDrawable();

                    //ic_1인지 비교
                    Drawable tmp1 = icon.getResources().getDrawable(R.drawable.ic_1);
                    Drawable tmp1_1 = icon.getResources().getDrawable(R.drawable.ic_1_1);
                    Bitmap tmpBitmap = ((BitmapDrawable) tmp).getBitmap();
                    Bitmap tmpBitmap1 = ((BitmapDrawable) tmp1).getBitmap();
                    Bitmap tmpBitmap1_1 = ((BitmapDrawable) tmp1_1).getBitmap();

                    if (tmpBitmap.equals(tmpBitmap1) || tmpBitmap.equals(tmpBitmap1_1) ) {
                        if (number == 0) {
                            icon.setImageResource(R.drawable.ic_1);
                            number++;
                            num.setText(number + "");
                        } else {
                            number++;
                            num.setText(number + "");
                        }
                    }
                }
            });

            icon.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // TODO Auto-generated method stub

                    Drawable tmp = icon.getDrawable();

                    //1~6까지 반복문 돌리기

                    //ic_1인지 비교
                    Drawable tmp1 = icon.getResources().getDrawable(R.drawable.ic_1);
                    Drawable tmp1_1 = icon.getResources().getDrawable(R.drawable.ic_1_1);

                    Bitmap tmpBitmap = ((BitmapDrawable) tmp).getBitmap();
                    Bitmap tmpBitmap1 = ((BitmapDrawable) tmp1).getBitmap();
                    Bitmap tmpBitmap1_1 = ((BitmapDrawable) tmp1_1).getBitmap();

                    if (tmpBitmap.equals(tmpBitmap1) || tmpBitmap.equals(tmpBitmap1_1) ) {


                        number = Integer.parseInt(num.getText().toString());
                        // num이 1보다 크면 longClick했을 때 1 감소
                        if (number > 1) {
                            number--;
                            // num의 text 변경
                            num.setText(number + "");
                        } else if (number == 1) {
                            number--;
                            num.setText(number + "");
                            icon.setImageResource(R.drawable.ic_1_1);
                        } else if (number == 0)
                            number = 0;
                    }
                    return true;
                }
            }); // end inner class*/
        }

    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_list, parent, false) ;
        Adapter.ViewHolder vh = new Adapter.ViewHolder(view) ;

        return vh ;
    }


    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        ListViewItem item = mData.get(position) ;

        holder.icon.setImageDrawable(item.getIcon()) ;
        holder.num.setText(item.getNum()) ;
        holder.content.setText(item.getContent()) ;
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}