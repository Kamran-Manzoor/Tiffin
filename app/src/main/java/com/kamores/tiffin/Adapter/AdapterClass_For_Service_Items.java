package com.kamores.tiffin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kamores.tiffin.R;

import java.util.ArrayList;

public class AdapterClass_For_Service_Items extends RecyclerView.Adapter<AdapterClass_For_Service_Items.ViewHolder> {
    private ArrayList<String> mItems;
    private Context mContext;

    public AdapterClass_For_Service_Items(ArrayList<String> mItems, Context mContext) {
        this.mItems = mItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardview_for_supplier_items, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.showItems.setText(mItems.get(position));

        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent().setClass(mContext, Activity_Detail.class);
//                mContext.getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView showItems;
        CardView cardView;

        public ViewHolder(View itemView) {
            super( itemView );
//            showItems = itemView.findViewById(R.id.tv_Service_Items);
//            cardView= itemView.findViewById(R.id.cardViewServices);
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View v) {

        }
    }
}