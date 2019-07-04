package com.kamores.tiffin;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kamores.tiffin.Fragment.FragmentDay;

import java.util.ArrayList;

public class AdapterClass_For_Week extends RecyclerView.Adapter<AdapterClass_For_Week.ViewHolder> {
    private ArrayList<String> mNames;
    private Context mContext;

    public AdapterClass_For_Week(Context mContext, ArrayList<String> mNames) {
        this.mNames = mNames;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_week, parent, false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.showDays.setText(mNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView showDays;

        public ViewHolder(View itemView) {
            super(itemView);
            showDays = itemView.findViewById(R.id.tv_Days);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext,Activity_Detail.class);
            mContext.startActivity(intent);
        }
    }
}