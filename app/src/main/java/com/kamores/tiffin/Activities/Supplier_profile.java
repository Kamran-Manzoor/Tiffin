package com.kamores.tiffin.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kamores.tiffin.Adapter.AdapterClass_For_Service_Items;
import com.kamores.tiffin.ModelClasses.ModelClass;
import com.kamores.tiffin.R;

public class Supplier_profile extends AppCompatActivity {
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


        //recyclerViewService = findViewById(R.id.recyclerView_Services);
//        recyclerViewService.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        adapter = new AdapterClass(modelClasses, mContext);
//        recyclerViewService.setLayoutManager(layoutManager);
//        recyclerViewService.setAdapter(adapter);

    }
}