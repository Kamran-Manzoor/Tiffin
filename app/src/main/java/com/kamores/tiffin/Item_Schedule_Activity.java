package com.kamores.tiffin;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
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