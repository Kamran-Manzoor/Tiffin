package com.kamores.tiffin.adapters;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kamores.tiffin.models.ModelClass;
import com.kamores.tiffin.R;

import java.util.List;

public class CustomeAdapterItems extends RecyclerView.Adapter<CustomeAdapterItems.ViewHolder> {
    private Context mContext;
    private List<ModelClass> modelClasses;


    public CustomeAdapterItems(List<ModelClass> modelClasses, Context context) {
        this.mContext = context;
        this.modelClasses = modelClasses;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout, null);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(CustomeAdapterItems.ViewHolder holder, final int position) {
        final ModelClass currentItem = modelClasses.get(position);
        holder.tv_Item_name.setText(currentItem.getItemName());
        holder.tv_item_price.setText(currentItem.getPrice());

    }
    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Item_name, tv_item_price;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_Item_name= itemView.findViewById(R.id.tvItemNameDays);
            tv_item_price= itemView.findViewById(R.id.tvItemPriceDays);
        }
    }
}