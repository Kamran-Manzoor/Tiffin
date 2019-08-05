package com.kamores.tiffin.Activities;

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

import com.kamores.tiffin.R;

import java.sql.Struct;

public class Register_Activity_supplier extends AppCompatActivity {

    private RelativeLayout rlayout;
    private Animation animation;
    EditText etfullName, etAddress;
    Button btn_signUp;
    ImageView supplier_image;

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

                 //   addSupplier(name, add);

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

    public void addSupplier(String fullName, String address, String supplier_image){


    }
}
