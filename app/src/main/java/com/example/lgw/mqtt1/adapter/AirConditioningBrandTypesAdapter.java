package com.example.lgw.mqtt1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lgw.mqtt1.R;
import com.example.lgw.mqtt1.bean.BrandTypes;

import java.util.List;

public class AirConditioningBrandTypesAdapter extends BaseAdapter {

    List<BrandTypes> datas;
    public AirConditioningBrandTypesAdapter(Context context, int Resid, List<BrandTypes> object) {
        super(context, Resid, object);
        datas=object;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        processDate(viewHolder,i);
    }

    @Override
    public void processDate(RecyclerView.ViewHolder viewHolder, int i) {
        super.processDate(viewHolder, i);
        View itemView = viewHolder.itemView;
       TextView tv_brand= itemView.findViewById(R.id.tv_brand);
        tv_brand.setText(datas.get(i).getTv_brand());
        TextView tv_brand_types=itemView.findViewById(R.id.tv_brand_types);
        tv_brand_types.setText(datas.get(i).getTv_brand_types());
        ImageView iv_tv_noselect=itemView.findViewById(R.id.iv_tv_noselect);
        iv_tv_noselect.setImageResource(datas.get(i).getImgId());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
