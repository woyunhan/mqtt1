package com.example.lgw.mqtt1.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class BaseAdapter extends RecyclerView.Adapter {
    Context context;
    int Resid;
    public interface OnItemClikListener {
        void OnItemClick(View view, int pos);
    }
    private OnItemClikListener MyItemClikListener;
    public void setOnItemClikListener(OnItemClikListener onItemClikListener) {
        this.MyItemClikListener = onItemClikListener;
    }
    public BaseAdapter(Context context, int Resid, Object object) {
        this.context = context;
        this.Resid = Resid;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(Resid, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getLayoutPosition();
                MyItemClikListener.OnItemClick(viewHolder.itemView, pos);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    public void processDate(RecyclerView.ViewHolder viewHolder,int i)
    {
    }
}

