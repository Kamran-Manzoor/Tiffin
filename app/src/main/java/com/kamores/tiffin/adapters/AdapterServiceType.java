package com.kamores.tiffin.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kamores.tiffin.activities.Supplier_profile;
import com.kamores.tiffin.constants.Constants;
import com.kamores.tiffin.models.ModelClass;
import com.kamores.tiffin.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AdapterServiceType extends RecyclerView.Adapter<AdapterServiceType.ViewHolder> implements Filterable {
    private Context mContext;
    private List<ModelClass> modelClasses;
    private List<ModelClass> modelClassList;
    public static String image_name;

    public AdapterServiceType(List<ModelClass> modelClasses, Context context) {
        this.mContext = context;
        this.modelClasses = modelClasses;
        this.modelClassList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_servicetypeall, null);
        return new ViewHolder(view);
    }
    public static class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                //Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                return null;
            } catch (IOException e) {
                //Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ModelClass currentItem = modelClasses.get(position);

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage = null;
        try {
            myImage = task.execute(Constants.BASE_URL+"/Tiffin/uploads/"+modelClasses.get(position).getItem_image() +".jpg").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Glide.with(mContext).load(myImage).into(holder.ImageView);
                //.into(holder.img_show);

        holder.tv_ServiceName.setText(currentItem.getItemName());
        holder.tv_SupplierName.setText(currentItem.getName());
        holder.tv_Location.setText(currentItem.getAddress());
        holder.tv_price.setText("RS:" + currentItem.getPrice());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_name = currentItem.getItem_image();
                Intent i = new Intent().setClass(mContext, Supplier_profile.class);
                i.putExtra("Supplier_id",currentItem.getSupplier_id());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                mContext.getApplicationContext().startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ImageView;
        TextView tv_ServiceName, tv_SupplierName, tv_Location,tv_price;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_ServiceName = itemView.findViewById(R.id.tv_item_name);
            tv_SupplierName = itemView.findViewById(R.id.tv_SupplierName_RecyclerView);
            tv_Location = itemView.findViewById(R.id.tv_SupplierLocation_RecyclerView);
            tv_price = itemView.findViewById(R.id.tv_item_price);
            cardView = itemView.findViewById(R.id.cardViewService);
            ImageView = itemView.findViewById(R.id.item_img);
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
                    if (item.getItemName().toLowerCase().contains(filterPattern) ||
                            item.getName().toLowerCase().contains(filterPattern) ||
                            item.getAddress().toLowerCase().contains(filterPattern)){
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