package com.kamores.tiffin.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kamores.tiffin.Constants.Constants;
import com.kamores.tiffin.Constants.RequestInterfacePart;
import com.kamores.tiffin.Constants.ServerRequest;
import com.kamores.tiffin.Constants.ServerResponce;
import com.kamores.tiffin.ModelClasses.Suppliers;
import com.kamores.tiffin.R;

import java.sql.Struct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register_Activity_supplier extends AppCompatActivity {

    private RelativeLayout rlayout;
    private Animation animation;
    EditText etfullName, etAddress;
    Button btn_signUp;
    ImageView supplier_image;
    String name,address,supplierimage,user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_supplier);


        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        initViewSupplier();

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etfullName.getText().toString();
                String add = etAddress.getText().toString();
                if (name.equals("")) {
                    etfullName.setError("Add Name!");
                } else if (add.equals("")){
                    etAddress.setError("Add Address!");

                } else {
                    addSupplier(name, add);
                }
            }
        });
    }

    private void initViewSupplier() {
        etfullName = findViewById(R.id.tv_full_name);
        etAddress = findViewById(R.id.tv_address);
        supplier_image = findViewById(R.id.imageView_supplier);
        btn_signUp = findViewById(R.id.btn_signUp_supplier);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void getValues() {
        String name = etfullName.getText().toString();
        String add = etAddress.getText().toString();
        supplierimage = "ss";
        user_id = "11";
        //Sup_detail = "Some Detail";


    }

    private void addSupplier(String name,String address) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);
        final Suppliers suppliers = new Suppliers();

        getValues();

        suppliers.setName(name);
        suppliers.setAddress(address);
        suppliers.setSupplier_image(supplierimage);
        suppliers.setUser_id(user_id);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_SUPPLIER);
        request.setSuppliers(suppliers);

        Call<ServerResponce> resp = requestInterfacePart.operationone(request);

        resp.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    Suppliers suppliers1 = new Suppliers();
                    ServerResponce resp = response.body();
                    suppliers1 = resp.getSuppliers();


                    Toast.makeText(Register_Activity_supplier.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
//                    Supplier_id = suppliers1.getUser_id();
//                    Service_id = suppliers1.getAddress();
//
//                    setUpIntent();

                }
                catch(Exception e){
                    Toast.makeText(Register_Activity_supplier.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Register_Activity_supplier.this, "Connection Failure "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setUpIntent() {
        Intent intent = new Intent(Register_Activity_supplier.this,Add_Items.class);
        startActivity(intent);
        finish();
    }

}
