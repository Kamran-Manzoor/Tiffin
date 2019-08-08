package com.kamores.tiffin.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kamores.tiffin.Adapter.Adapter_Supplier;
import com.kamores.tiffin.ModelClasses.ModelClass;
import com.kamores.tiffin.R;

import java.time.LocalDate;

public class Supplier_profile extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_Supplier adapter;
    ModelClass modelClasses;
    Context mContext;
    ImageButton imageButton;
    String currentDay;
    TextView name,contact,address;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_supplier_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        initialviews();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Supplier_profile.this,BaseActivity.class);
                startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initialviews() {
       imageButton=findViewById(R.id.previous);
       name=findViewById(R.id.tv_supplier_name);
       contact=findViewById(R.id.tv_supplier_contact);
       address=findViewById(R.id.tv_supplier_Address);
       recyclerView=findViewById(R.id.rv_supplier_profile);
        currentDay = LocalDate.now().getDayOfWeek().name();
        Toast.makeText(this, currentDay, Toast.LENGTH_SHORT).show();

    }
}