package com.kamores.tiffin;

import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ResultActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    Button signOut;
    TextView nameTV;
    TextView emailTV;
    TextView idTV;
    TextView addressTV;
    ImageView imageIV;

    LocationManager locationManager;
    android.location.LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //signOut = findViewById(R.id.sign_Out);
        nameTV = findViewById(R.id.user_name);
        emailTV = findViewById(R.id.user_email);
        idTV = findViewById(R.id.user_id);
        //addressTV = findViewById(R.id.address_TV);
        imageIV = findViewById(R.id.imageIV);
        //cruntAddress();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ResultActivity.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivinName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();

            Uri personImage = acct.getPhotoUrl();

            nameTV.setText("Name: " + personName);
            emailTV.setText("Email: " + personEmail);
            idTV.setText("ID: " + personId);
            Glide.with(this).load(personImage).into(imageIV);
        }
//        signOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                forSignOut();
//            }
//        });


    }
    public void forSignOut() {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ResultActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ResultActivity.this, MainActivity.class));
                        finish();

                    }
                });
    }
}
