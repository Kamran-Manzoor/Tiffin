package com.kamores.tiffin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Add_Supplier extends AppCompatActivity {
    EditText etName,etService, etContact;
    TextView address;
    ImageView addSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__supplier);

        initViewSuppliers();

        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Add_Supplier.this,BaseActivity.class);
                startActivity(intent);
            }
        });

    }
    public void initViewSuppliers(){
        etName =findViewById(R.id.et_supplier_name);
        etService= findViewById(R.id.et_supplier_serviceName);
        etContact= findViewById(R.id.et_supplier_contact);
        address= findViewById(R.id.tv_supplier_address);
        addSupplier= findViewById(R.id.img_AddSuppliers);
    }
}