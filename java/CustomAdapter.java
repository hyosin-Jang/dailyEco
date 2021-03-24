package com.example.dailyeco;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.dailyeco.R;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<GoalData> mList;
    private Context context;
    //private SparseBooleanArray selectedItems = new SparseBooleanArray();//item의 클릭 상태를 저장할 array 객체
    //private int prePosition = -1;//직전에 클릭됐던 item의 position

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.goal.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.goal.setText(mList.get(position).getGoal());//add버튼 추가시 적은 텍스트를 아래 list에 추가해서 보여줌



    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        protected TextView goal, goal2, tv_name;
        protected Button btn_save, btn_delete, btn_icon;
        protected GoalData data;
        protected int position;

        //OnViewHolderItemClickListener onViewHolderItemClickListener;


        public CustomViewHolder(View view) {
            super(view);
            this.goal = (TextView) view.findViewById(R.id.goal_listitem);
            this.goal2 = (TextView) view.findViewById(R.id.goal2);
            this.tv_name = (TextView) view.findViewById(R.id.tv_name);
            this.btn_save = (Button) view.findViewById(R.id.btn_save);
            this.btn_delete = (Button) view.findViewById(R.id.btn_delete);
            this.btn_icon = (Button) view.findViewById(R.id.btn_icon);


            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//저장 버튼 클릭 리스너


                    if(goal2.length() == 0)//새로 적은 내용이 없을 때
                    {
                        mList.get(position).setGoal(goal.getText().toString());
                    }
                    else
                    {
                        mList.get(position).setGoal(goal2.getText().toString());

                    }
                    mList.get(position).setIcon(R.drawable.leaf_on);//아이콘 수정 코드 바꾸기
                    notifyDataSetChanged();


                }
            });

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mList.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();



                }
            });

            btn_icon.setOnClickListener(new View.OnClickListener() {//아이콘 변경
                @Override
                public void onClick(View v) {

                    Intent intent_to_icon = new Intent(context, icon_select.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent_to_icon);
                }
            });

           /* for(int i =0; i< 6; i++)
            {
                mList.get(i).set_is_active(1);
            }
            notifyDataSetChanged();*///이부분 다시 수정!


        }

        /*public void onBind(GoalData data,int position){

            this.data = data;
            this.position = position;

            //data가져오기

            changeVisibility(selectedItems.get(position));


        }*/

        /*private void changeVisibility(final boolean isExpanded) {

            tv_name.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            goal2.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            btn_icon.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            btn_save.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            btn_delete.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        }*/

        //public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        //    this.onViewHolderItemClickListener = onViewHolderItemClickListener;
        //}

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }

    }


    public CustomAdapter(Context context, ArrayList<GoalData> list) {
        this.mList = list;
        this.context = context;

    }


    void addItem(GoalData data)
    {
        mList.add(data);
    }


    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}