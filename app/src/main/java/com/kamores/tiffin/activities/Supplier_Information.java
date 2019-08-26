package com.kamores.tiffin.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kamores.tiffin.R;

public class Supplier_Information extends AppCompatActivity {
    EditText etName,etService, etContact;
    TextView address;
    Button addSupplier;
    String supplierName,serviceName,supplierContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);

        initViewSuppliers();

        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supplierName= etName.getText().toString().trim();
                serviceName= etService.getText().toString().trim();
                supplierContact= etContact.getText().toString().trim();
                if (supplierName.isEmpty()){
                    etName.setError("Enter Supplier Name");
                }
                else if (serviceName.isEmpty()){
                    etService.setError("Enter Service Name");
                }
                else if (supplierContact.isEmpty()){
                    etContact.setError("Enter Supplier Contact");
                }
                else {
                   // Intent intent= new Intent( Supplier_Information.this, Password.class);
                   // startActivity(intent);
                }
            }
        });

            }

            public void initViewSuppliers() {
                etName = findViewById(R.id.et_supplier_name);
                etService = findViewById(R.id.et_supplier_serviceName);
                etContact = findViewById(R.id.et_supplier_contact);
                address = findViewById(R.id.tv_supplier_address);
                addSupplier = findViewById(R.id.btn_Add_Supplier);
            }
        }