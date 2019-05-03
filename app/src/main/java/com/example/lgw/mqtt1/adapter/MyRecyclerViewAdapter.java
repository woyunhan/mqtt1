package com.example.lgw.mqtt1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lgw.mqtt1.R;

import java.util.ArrayList;

//设置RecyclerView的适配器
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private final Context context;
    private ArrayList<String> datas;
    public MyRecyclerViewAdapter(Context context, ArrayList<String> datas) {
        this.context=context;
        this.datas=datas;
    }

    /**
     * 相当于getView方法中创建View和ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=View.inflate(context, R.layout.item_brand,null);
        return new MyViewHolder(itemView);
    }

    /**
     * 相当于getView方法中的绑定
     * view的绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //根据位置得到对应数据
        String data=datas.get(position);
        holder.tv_brand.setText(data);
    }

    /**
     * 得到总条数
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

//    /**
//     * 添加数据
//     * @param position
//     * @param data
//     */
//    public void addData(int position, String data) {
//        datas.add(position,data);
//        /**
//         * 刷新适配器
//         */
//        notifyItemInserted(position);
//    }
//
//    public void removeData(int position) {
//        datas.remove(position);
//        /**
//         * 刷新适配器
//         */
//        notifyItemRemoved(position);
//    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_brand;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_brand=(TextView) itemView.findViewById(R.id.tv_brand);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "data=="+datas.get(getLayoutPosition()), Toast.LENGTH_SHORT).show();
                    if (onItemClickListener!=null){
                        onItemClickListener.OnItemClick(view,datas.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    /**
     * 点击RecyclerView的某条的监听
     */
    public interface OnItemClickListener{
        /**
         * 当RecyclerView某个被点击的时候回调
         * @param view 点击item的视图
         * @param data 点击得到的数据
         */
        public void OnItemClick(View view, String data);
    }
    public OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
