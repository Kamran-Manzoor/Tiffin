package com.kamores.tiffin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Password extends AppCompatActivity {
    EditText etPass, etConfirmPass;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        initViews();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Password.this,BaseActivity.class );
                startActivity( intent );
            }
        });
    }
    public void initViews(){
        etPass= findViewById(R.id.et_Pass);
        etConfirmPass= findViewById(R.id.et_ConfirmPass);
        finish= findViewById(R.id.btn_Finished);
    }
}