package com.kamores.tiffin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(checkPassword(etPass.getText().toString(),etConfirmPass.getText().toString())==null){
                    Toast.makeText(Password.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Password.this,BaseActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
    public void initViews(){
        etPass= findViewById(R.id.et_Pass);
        etConfirmPass= findViewById(R.id.et_ConfirmPass);
        finish= findViewById(R.id.btn_Finished);
    }
    public String checkPassword(String pass, String confirmPass){
        String getPass = null;
        if (pass.isEmpty() || confirmPass.isEmpty()){
            Toast.makeText(this, "Password Empty", Toast.LENGTH_SHORT).show();
        }
        if (pass.equals(confirmPass)){
            getPass = pass;
            Toast.makeText(this, "Password matched", Toast.LENGTH_SHORT).show();
        }
        return getPass;
    }
}