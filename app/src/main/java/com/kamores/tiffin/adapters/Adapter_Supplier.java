package com.kamores.tiffin.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kamores.tiffin.constants.Constants;
import com.kamores.tiffin.models.ModelClass;
import com.kamores.tiffin.models.ModelClass_Supplier;
import com.kamores.tiffin.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Adapter_Supplier extends RecyclerView.Adapter<Adapter_Supplier.ViewHolder> {
    private List<ModelClass_Supplier> modelClasses;
    private Context mContext;

    public Adapter_Supplier(List<ModelClass_Supplier> modelClasses, Context mContext) {
        this.modelClasses = modelClasses;
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
        final ModelClass_Supplier currentItem = modelClasses.get(position);

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage = null;
        try {
            myImage = task.execute(Constants.BASE_URL+"/Tiffin/uploads/"+modelClasses.get(position).getItem_image() +".jpg").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Glide.with(mContext).load(myImage).into(holder.img);
        holder.tv_FoodName.setText(currentItem.getItemName());
        holder.tv_price.setText("RS:" + currentItem.getPrice());
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
    public int getItemCount() {
        return modelClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        android.widget.ImageView ImageView;
        TextView  tv_FoodName,tv_price;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_FoodName = itemView.findViewById(R.id.tvNameFood);
            tv_price = itemView.findViewById(R.id.tvPrice);
            img = itemView.findViewById(R.id.food_img);
        }
    }
}