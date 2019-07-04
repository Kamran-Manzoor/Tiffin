package com.kamores.tiffin;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    // 1060189699412-qd99af58up2oqk2ct1jpukilctjc6g06.apps.googleusercontent.com
    ImageView next, facebook;
    EditText etEmail;
    String email;
    private static final int REQ_CODE = 9001;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton btnGoogleSignin;

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

        btnGoogleSignin = findViewById(R.id.btnGoogleSignin);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        btnGoogleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    public void initViews() {
        etEmail = findViewById(R.id.et_email);
        next = findViewById(R.id.img_Next);
        facebook = findViewById(R.id.img_Facebook);

    }

    public void getEmail() {
        email= etEmail.getText().toString();
        if (email.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("enter a valid email address");
        } else {
          //  etEmail.setError(null);
            Intent intent= new Intent(MainActivity.this, Password.class);
            startActivity(intent);
        }
    }

    private void signIn()
    {
//        Intent intent =  mGoogleSignInClient.getSignInIntent();
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,REQ_CODE);
    }
    private void handleResult(GoogleSignInResult result)
    {

    }
    private void updateUI(boolean isLogin)
    {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // FOR GOOGLE LOGIN
        if (requestCode == REQ_CODE)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask)
    {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            startActivity(new Intent(MainActivity.this,Password.class));

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed'", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        // IF ALREADY LOGIN
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null)
        {
            startActivity(new Intent(MainActivity.this,Password.class));
        }
        super.onStart();
    }
}