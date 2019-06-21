package com.kamores.tiffin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView next, facebook, google;
    EditText etEmail;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmail();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
                startActivity(intent);
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/ServiceLogin/signinchooser?flowName=GlifWebSignIn&flowEntry=ServiceLogin&cid=1&navigationDirection=forward"));
                startActivity(intent);
            }
        });
    }

    public void initViews() {
        etEmail = findViewById(R.id.et_email);
        next = findViewById(R.id.img_Next);
        facebook = findViewById(R.id.img_Facebook);
        google = findViewById(R.id.img_Google);
    }

    public void getEmail() {
        email= etEmail.getText().toString();
        if (email.isEmpty()) {
            etEmail.setError("Enter Email");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("enter a valid email address");
        } else {
            etEmail.setError(null);
            Intent intent= new Intent(MainActivity.this, Password.class);
            startActivity(intent);
        }
    }
}