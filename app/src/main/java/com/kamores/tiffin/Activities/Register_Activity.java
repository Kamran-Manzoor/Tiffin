package com.kamores.tiffin.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.kamores.tiffin.Constants.Constants;
import com.kamores.tiffin.Constants.RequestInterfacePart;
import com.kamores.tiffin.Constants.ServerRequest;
import com.kamores.tiffin.Constants.ServerResponce;
import com.kamores.tiffin.ModelClasses.Suppliers;
import com.kamores.tiffin.ModelClasses.User;
import com.kamores.tiffin.ModelClasses.UserModel;
import com.kamores.tiffin.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register_Activity extends AppCompatActivity {

    private RelativeLayout rlayout;
    private Animation animation;
    EditText etContact, etEmail, etPassword, etConfirmPass;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        initViewUser();
    }

    public void initViewUser() {
        etContact = findViewById(R.id.user_contact);
        etEmail = findViewById(R.id.user_email);
        etPassword = findViewById(R.id.user_password);
        etConfirmPass = findViewById(R.id.user_confirmPassword);
        btn_add = findViewById(R.id.btn_signUp_user);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = etPassword.getText().toString().trim();
                String confPass = etConfirmPass.getText().toString().trim();
                if (pass.equals(confPass)) {
                    addUser(etContact.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                } else {
                    Toast.makeText(Register_Activity.this, "Password didn't Match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addUser(String contact, String email, String password) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);
        final User user = new User();

        user.setContact(contact);
        user.setEmail(email);
        user.setPassword(password);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_USER);
        request.setUser(user);

        final Call<ServerResponce> resp = requestInterfacePart.operationone(request);

        resp.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                User user = new User();
                ServerResponce resp = response.body();
                user = resp.getUser();
                Toast.makeText(Register_Activity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
//                try {
//                    ServerResponce resp = response.body();
//
//                    assert resp != null;
//                    Toast.makeText(Register_Activity.this, "You Have Successfully Sign Up "+resp.getMessage(), Toast.LENGTH_SHORT).show();
//                    setUpIntent();
//                }
//                catch(Exception e){
//                    Toast.makeText(Register_Activity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Register_Activity.this, "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUpIntent() {
        Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
        startActivity(intent);
        finish();
    }
}
