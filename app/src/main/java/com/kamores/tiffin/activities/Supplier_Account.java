package com.kamores.tiffin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.kamores.tiffin.R;
import com.kamores.tiffin.constants.Constants;
import com.kamores.tiffin.constants.ServerRequest;
import com.kamores.tiffin.constants.ServerResponce;
import com.kamores.tiffin.interfaces.RequestInterfacePart;
import com.kamores.tiffin.models.User;
import com.kamores.tiffin.models.UserShared;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Supplier_Account extends AppCompatActivity {

    String contact,email,user_id;
    TextView etEmail,etContact,etLocation,etPassword;
   // RelativeLayout Rname,Raddress;
    TextView deactivate,tv_Back ;
    Context mContext;
    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_supplier__account);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
            getWindow().setStatusBarColor( Color.TRANSPARENT);
        }
        initViewUser();
//        Rname.setVisibility(View.GONE);
//        Raddress.setVisibility(View.GONE);

//        tv_Back.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(Supplier_Account.this,BaseActivity.class);
//                startActivity(intent);
//                finish();
//             }
//        } );
//        deactivate.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                AlertDialog.Builder dg = new AlertDialog.Builder(mContext);
////                View view1 = LayoutInflater.from(mContext).inflate(R.layout.deactivate_dialogue, null);
////                dg.setTitle("Enter Password");
////                final EditText pass = view1.findViewById(R.id.et_dg_pass);
////                dg.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                    dellUserAccount();
////
////                        Toast.makeText(mContext, "Deleted...", Toast.LENGTH_SHORT).show();
////                    }
////                });
//            }
//        });

        getUserAccount();
    }

    private void initViewUser() {
        etEmail=findViewById( R.id.tv_email_userprofile);

        etContact=findViewById( R.id.tv_contact_no__userprofile);
        etLocation = findViewById(R.id.tv_location_userprofile);
        etPassword=findViewById(R.id.tv_password_userprofile);
//        Rname=findViewById( R.id.nameLayout );
//        Raddress=findViewById( R.id.addressLayout );
//        deactivate=findViewById( R.id.tvDeactivate );
//        tv_Back = findViewById(R.id.tv_Back_to_home);

        UserShared user1 = new UserShared(Supplier_Account.this);
        user_id = user1.getUser_id();
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
    private void getUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Constants.BASE_URL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();

        final User user = new User();
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.GET_USER);
        user.setId(user_id);
        request.setUser(user);

        RequestInterfacePart requestInterface = retrofit.create(RequestInterfacePart.class);

        Call<ServerResponce> response = requestInterface.operationone(request);
        response.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    ServerResponce resp = response.body();
                    Toast.makeText( Supplier_Account.this, ""+resp.getMessage(), Toast.LENGTH_SHORT ).show();

                    User user1 = resp.getUser();
                    contact=user1.getContact();
                    email = user1.getEmail();

                    etContact.setText( contact );
                    etContact.setEnabled( false );
                    etEmail.setText( email );
                    etEmail.setEnabled( false );


                } catch (Exception e) {
                    Toast.makeText(Supplier_Account.this, "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Supplier_Account.this, "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void dellUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Constants.BASE_URL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();

        final User user = new User();
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.DEACTIVATE_ACCOUNT);
        user.setId(user_id);
        user.setPassword("");
        request.setUser(user);

        RequestInterfacePart requestInterface = retrofit.create(RequestInterfacePart.class);

        Call<ServerResponce> response = requestInterface.operationone(request);
        response.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    ServerResponce resp = response.body();
                    Toast.makeText( Supplier_Account.this, ""+resp.getMessage(), Toast.LENGTH_SHORT ).show();

                } catch (Exception e) {
                    Toast.makeText(Supplier_Account.this, "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Supplier_Account.this, "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_action, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
             case R.id.menu_edit:

                 etEmail.setFocusableInTouchMode(true);
                 etContact.setFocusableInTouchMode(true);

                 InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                 imm.showSoftInput(etContact, InputMethodManager.SHOW_IMPLICIT);

                 action.findItem(R.id.menu_edit).setVisible(false);
                 action.findItem(R.id.menu_save).setVisible(true);

                 return true;

             case R.id.menu_save:

                  action.findItem(R.id.menu_edit).setVisible(true);
                 action.findItem(R.id.menu_save).setVisible(false);

                 etContact.setFocusableInTouchMode(false);
                 etEmail.setFocusableInTouchMode(false);
                 etContact.setFocusable(false);
                 etEmail.setFocusable(false);

                 return true;

                 default:

                     return super.onOptionsItemSelected(item);


         }
    }
}