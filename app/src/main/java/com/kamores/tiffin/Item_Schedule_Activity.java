package com.kamores.tiffin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class Item_Schedule_Activity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_item_schedule );

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent( Item_Schedule_Activity.this, Add_Items.class);
                startActivity(intent);
            }
        });
    }
}