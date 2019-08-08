package com.kamores.tiffin.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kamores.tiffin.Adapter.AdapterClass_For_Service_Items;
import com.kamores.tiffin.Adapter.CustomeAdapterItems;
import com.kamores.tiffin.Constants.Constants;
import com.kamores.tiffin.Constants.RequestInterfacePart;
import com.kamores.tiffin.Constants.ServerRequest;
import com.kamores.tiffin.Constants.ServerResponce;
import com.kamores.tiffin.ModelClasses.ModelClass;
import com.kamores.tiffin.ModelClasses.Suppliers;
import com.kamores.tiffin.R;

import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Supplier_profile extends AppCompatActivity {

    ImageView supplierProfile;
    ImageButton imageButton;
    CircleImageView circleImageView;
    TextView SupplierName,SupplierContact,SupplierAddress;
    RecyclerView recyclerViewService;
    AdapterClass_For_Service_Items adapter;
    ModelClass modelClasses;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_supplier_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initViewSuppliersProfile();


        //recyclerViewService = findViewById(R.id.recyclerView_Services);
//        recyclerViewService.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        adapter = new AdapterClass(modelClasses, mContext);
//        recyclerViewService.setLayoutManager(layoutManager);
//        recyclerViewService.setAdapter(adapter);

    }

    public void initViewSuppliersProfile() {
        SupplierName = findViewById(R.id.Profile);
        SupplierContact = findViewById(R.id.Profile_Contact);
        SupplierAddress = findViewById(R.id.Address);

    }

//    private void getDataSupplier() {
////
////        Retrofit retrofit = new Retrofit.Builder().baseUrl( Constants.BASE_URL ).addConverterFactory( GsonConverterFactory.create() ).build();
////        RequestInterfacePart requestInterfacePart = retrofit.create( RequestInterfacePart.class );
////
////        Suppliers suppliers = new Suppliers();
////        // suppliers.setSupplier_id(Activity_Detail.Sup_id);
////
////        ServerRequest request = new ServerRequest();
////        request.setOperation(Constants.RETRIVE_DETAIL);
////        request.setSuppliers(suppliers);
////
////        Call<ServerResponce> response = requestInterfacePart.operationone(request);
////
////        response.enqueue( new Callback<ServerResponce>() {
////            Suppliers suppliers;
////            @Override
////            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
////                try {
////                    ServerResponce resp = response.body();
////                    suppliers = resp.getSuppliers();
//////                    String Sup_name = suppliers.getSupplier_name();
//////                    String Ser_name = suppliers.getService_name();
//////                    String Location = suppliers.getSupplier_location();
//////                    String Contact_no = suppliers.getSupplier_contact();
////
//////                    to_name.setText(Sup_name);
//////                    to_service.setText(Ser_name);
//////                    to_Location.setText(Location);
//////                    //Toast.makeText(getContext(), ""+Contact_no, Toast.LENGTH_SHORT).show();
//////                    phone.setText(Contact_no);
//////                    //Toast.makeText(getContext(), ""+Contact_no, Toast.LENGTH_SHORT).show();
//////                } catch (Exception e) {
////                    Toast.makeText( getContext(), "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
////                }
////            }
////
////            @Override
////            public void onFailure(Call<ServerResponce> call, Throwable t) {
////                Toast.makeText( getContext(), "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
////            }
////        } );
////    }

//    public void setUpRecyclerView() {
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//
//        if (modelClasses == null){
//            Toast.makeText( getContext(), "Null", Toast.LENGTH_SHORT ).show();
//        }
//        else {
//            adapter = new CustomeAdapterItems(modelClasses, getContext());
//            recyclerView.setLayoutManager(layoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//    }

//    public class GetImageFromURL extends AsyncTask<String,Void, Bitmap> {
//        ImageView imageView;
//
//        public GetImageFromURL(ImageView imageView) {
//            this.imageView = imageView;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            String uriDisplay = strings[0];
//            bitmap = null;
//            try {
//                InputStream str = new java.net.URL(uriDisplay).openStream();
//                bitmap = BitmapFactory.decodeStream(str);
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//
//            return bitmap;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//            imageView.setImageBitmap(bitmap);
//        }
//    }


//    public void getDataItems(String day) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);
//
//        Suppliers suppliers = new Suppliers();
//        suppliers.setSupplier_id(Activity_Detail.Sup_id);
//        suppliers.setDay(day);
//
//        ServerRequest request = new ServerRequest();
//        request.setOperation(Constants.RETRIVE_ITEMS);
//        request.setSuppliers(suppliers);
//
//
//        Call<ServerResponce> response = requestInterfacePart.operationone(request);
//
//        response.enqueue(new Callback<ServerResponce>() {
//            Suppliers suppliers;
//
//            @Override
//            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
//                try {
//                    ServerResponce resp = response.body();
//                    if (resp.getResult().equals(Constants.SUCCESS)){
//                        suppliers = resp.getSuppliers();
//                        item_name =suppliers.getItem_name();
//                        item_price = suppliers.getItem_price();
//
//                        modelClasses = new ArrayList<>();
//                        for (int i = 0; i < item_price.size(); i++) {
//                            //  modelClasses.add( new ModelClass( item_name.get( i ),item_price.get( i )));
//                        }
//                        setUpRecyclerView();
//                    }
//                    else {
//                        Toast.makeText(getContext(), "Sorry No Record Found on this Day", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                } catch (Exception e) {
//                    Toast.makeText(getContext(), "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ServerResponce> call, Throwable t) {
//                Toast.makeText(getContext(), "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//
}