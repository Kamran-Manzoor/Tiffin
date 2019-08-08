package com.kamores.tiffin.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kamores.tiffin.Adapter.AdapterClass;
import com.kamores.tiffin.Adapter.Adapter_Supplier;
import com.kamores.tiffin.Constants.Constants;
import com.kamores.tiffin.Constants.RequestInterfacePart;
import com.kamores.tiffin.Constants.ServerRequest;
import com.kamores.tiffin.Constants.ServerResponce;
import com.kamores.tiffin.ModelClasses.ModelClass;
import com.kamores.tiffin.ModelClasses.ModelClass_Supplier;
import com.kamores.tiffin.ModelClasses.Suppliers;
import com.kamores.tiffin.ModelClasses.User;
import com.kamores.tiffin.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Supplier_profile extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_Supplier adapter;
    Context mContext;
    ImageButton imageButton;
    String currentDay;
    TextView today, available;
    ProgressBar progressBar;
    ArrayList<String> name;
    ArrayList<String> address;
    String contact;
    ImageView img_suplier;
    TextView name1,contact1,address1;

   private   List<ModelClass_Supplier> modelClass;
    ArrayList<String> itemName;
    ArrayList<String> day;
    ArrayList<String> price;
    ArrayList<String> item_image;
    ArrayList<String> service;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_supplier_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        modelClass = new ArrayList<>();

        initialviews();
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(Supplier_profile.this,BaseActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initialviews() {
      // imageButton=findViewById(R.id.previous);
       name1=findViewById(R.id.tv_supplier_name);
       contact1=findViewById(R.id.tv_supplier_contact);
       address1=findViewById(R.id.tv_supplier_Address);
       recyclerView=findViewById(R.id.rv_supplier_profile);
       img_suplier=findViewById(R.id.img_supplier_profile);
        currentDay = LocalDate.now().getDayOfWeek().name();
        Toast.makeText(this, currentDay, Toast.LENGTH_SHORT).show();

    }

    //Get all Data of Supplier Profile Card View
    private void getToDaymenu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        User user = new User();
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.TODAY_MENU);
        user.setDay(currentDay);
        request.setUser(user);

        RequestInterfacePart requestInterface = retrofit.create(RequestInterfacePart.class);

        Call<ServerResponce> response = requestInterface.operationone(request);

        response.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    ServerResponce resp = response.body();

                    Toast.makeText(Supplier_profile.this, resp.getMessage(), Toast.LENGTH_SHORT).show();

//                    User user = resp.getUser();
//                    name = user.getName();
//                    itemName = user.getItemName();
//                    address = user.getAddress();
//                    supplier_id = user.getSupplier_id();
//                    price = user.getPrice();
//                    item_image = user.getItem_image();



                    modelClass = new ArrayList<>();
                    for (int i = 0; i < itemName.size(); i++) {
                        modelClass.add(new ModelClass_Supplier(itemName.get(i), item_image.get(i), price.get(i), day.get(i), service.get(i)));
                    }
                    setUpRecyclerViewToday();


                } catch (Exception e) {
                    Toast.makeText(Supplier_profile.this, "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Supplier_profile.this, "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerViewToday() {

        recyclerView = findViewById(R.id.rv_supplier_profile);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        if (modelClass == null) {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new Adapter_Supplier(modelClass, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
    }
}