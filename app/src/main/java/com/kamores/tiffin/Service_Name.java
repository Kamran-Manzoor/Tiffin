package com.kamores.tiffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Service_Name extends AppCompatActivity {
    RecyclerView recyclerViewService;
    AdapterClass_For_Service_Items adapter;
    ModelClass modelClasses;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_service_name );

        recyclerViewService = findViewById(R.id.recyclerView_Services);
        recyclerViewService.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        adapter = new AdapterClass(modelClasses, mContext);
        recyclerViewService.setLayoutManager(layoutManager);
        recyclerViewService.setAdapter(adapter);

    }
}