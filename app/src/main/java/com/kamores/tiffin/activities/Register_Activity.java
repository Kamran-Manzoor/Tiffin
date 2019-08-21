package com.kamores.tiffin.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kamores.tiffin.constants.Constants;
import com.kamores.tiffin.interfaces.RequestInterfacePart;
import com.kamores.tiffin.constants.ServerRequest;
import com.kamores.tiffin.constants.ServerResponce;
import com.kamores.tiffin.models.User;
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
    ImageButton rg_bck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        initViewUser();
        rg_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_Activity.this,Login_Activity_Supplier.class);
                startActivity(intent);

            }
        });



        //make translucent statusBar on kitkat devices
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

    public void initViewUser() {
        etContact = findViewById(R.id.user_contact);
        etEmail = findViewById(R.id.user_email);
        etPassword = findViewById(R.id.user_password);
        etConfirmPass = findViewById(R.id.user_confirmPassword);
        btn_add = findViewById(R.id.btn_signUp_user);
        rg_bck = findViewById(R.id.previous);


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
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
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
                ServerResponce resp = response.body();
                Toast.makeText(Register_Activity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                setUpIntent();

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
