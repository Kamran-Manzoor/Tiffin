package com.kamores.tiffin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add_Supplier extends AppCompatActivity {
    EditText etName,etService, etContact;
    TextView address;
    ImageView addSupplier;
    String Sup_name,Sup_service,Sup_con,Sup_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier );

        initViewSuppliers();


        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(Add_Supplier.this, Add_Items.class);
//                startActivity(intent);
                getValues();
                addSuppliers();
            }
        });

    }

    private void getValues() {
        Sup_name = etName.getText().toString();
        Sup_service = etService.getText().toString();
        Sup_con = etContact.getText().toString();
        Sup_location = "Khanewal";
    }

    public void initViewSuppliers(){
        etName =findViewById(R.id.et_supplier_name);
        etService= findViewById(R.id.et_supplier_serviceName);
        etContact= findViewById(R.id.et_supplier_contact);
        address= findViewById(R.id.tv_supplier_address);
        addSupplier= findViewById(R.id.img_AddSuppliers);
    }
    private void addSuppliers() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);
        Suppliers suppliers = new Suppliers();
        suppliers.setSupplier_name(Sup_name);
        suppliers.setSupplier_contact(Sup_con);
        suppliers.setSupplier_location(Sup_location);
        suppliers.setUser_id("1");
        suppliers.setService_name(Sup_service);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_SERVICE);
        request.setSuppliers(suppliers);

        Call<ServerResponce> resp = requestInterfacePart.operationone(request);

        resp.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    ServerResponce resp = response.body();
                    Toast.makeText(Add_Supplier.this, "" + resp.getMessage(), Toast.LENGTH_LONG).show();
                }
                catch(Exception e){
                    Toast.makeText(Add_Supplier.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Add_Supplier.this, "Connection Failure "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}