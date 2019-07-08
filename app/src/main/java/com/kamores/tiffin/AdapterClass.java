package com.kamores.tiffin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kamores.tiffin.Fragment.FragmentDay;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> implements Filterable {
    private Context mContext;
    private List<ModelClass> modelClasses;
    private List<ModelClass> modelClassList;


    public AdapterClass(List<ModelClass> modelClasses, Context context) {
        this.mContext = context;
        this.modelClasses = modelClasses;
        this.modelClassList = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ModelClass currentItem = modelClasses.get(position);
        //holder.img_food.setImageResource(currentItem.getImage_food());
        //holder.imgDetail.setImageResource(currentItem.getImage_detail());
        holder.tv_ServiceName.setText(currentItem.getService_name());
        holder.tv_SupplierName.setText(currentItem.getSup_name());
        holder.tv_Location.setText(currentItem.getLocation());

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent().setClass(mContext,Activity_Detail.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                mContext.getApplicationContext().startActivity(i);

            }
        });
    }
    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_ServiceName, tv_SupplierName,tv_Location;
        ImageView details;
        public ViewHolder(View itemView) {
            super(itemView);
            //img_food= itemView.findViewById(R.id.img_Food_RecyclerView);
            //imgDetail= itemView.findViewById(R.id.img_Details_RecyclerView);
            tv_ServiceName= itemView.findViewById(R.id.tv_ServiceName_RecyclerView);
            tv_SupplierName= itemView.findViewById(R.id.tv_SupplierName_RecyclerView);
            tv_Location = itemView.findViewById(R.id.tv_SupplierLocation_RecyclerView);
            details = itemView.findViewById(R.id.btn_see_details);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ModelClass> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(modelClassList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ModelClass item : modelClassList){
                    if (item.getService_name().toLowerCase().contains(filterPattern) ||
                            item.getSup_name().toLowerCase().contains(filterPattern) ||
                            item.getLocation().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results= new FilterResults();
            results.values= filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            modelClasses.clear();
            modelClasses.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}