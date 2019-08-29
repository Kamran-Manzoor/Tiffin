package com.kamores.tiffin.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.kamores.tiffin.R;
import com.kamores.tiffin.adapters.AdapterClass;
import com.kamores.tiffin.adapters.AdapterServiceType;
import com.kamores.tiffin.constants.Constants;
import com.kamores.tiffin.constants.ServerRequest;
import com.kamores.tiffin.constants.ServerResponce;
import com.kamores.tiffin.etc.InternetDialog;
import com.kamores.tiffin.interfaces.RequestInterfacePart;
import com.kamores.tiffin.models.ModelClass;
import com.kamores.tiffin.models.User;
import com.kamores.tiffin.models.UserShared;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    ImageView signIn;
    User user;
    RecyclerView recyclerView, recyclerView_all;
    private AdapterClass adapter;
    private AdapterServiceType adaptertype;
    Toolbar toolbar;
    NavigationView navigationView;
    LinearLayout logoutLayout;
    TextView today, available, tv_warning, nav_name, nav_contact, nav_login;
    ProgressBar progressBar;
    String sup_id, currentDay;
    ImageView img_warning;
    ImageView nav_img, img_login;

    private List<ModelClass> modelClasses;
    ArrayList<String> itemName;
    ArrayList<String> name;
    ArrayList<String> supplier_id;
    ArrayList<String> address;
    ArrayList<String> price;
    ArrayList<String> item_image;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (new InternetDialog(this).getInternetStatus()) {
            //  Toast.makeText(this, "INTERNET VALIDATION PASSED", Toast.LENGTH_SHORT).show();
        }
        initialviews();
        getToDaymenu();

        modelClasses = new ArrayList<>();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, Login_Activity.class);
                startActivity(intent);

            }
        });
        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, Login_Activity.class);
                startActivity(intent);

            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(Color.RED);
        navigationView.setNavigationItemSelectedListener(this);
        ColorStateList csl = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked}, // unchecked
                        new int[]{android.R.attr.state_checked}  // checked
                },
                new int[]{
                        Color.BLACK,
                        Color.RED
                }
        );
        navigationView.setItemTextColor(csl);
        navigationView.setItemIconTintList(csl);
        logoutLayout.setVisibility(View.GONE);
        today.setText("");
        available.setText("");
        tv_warning.setText("");
        nav_name.setText("");
        nav_contact.setText("");
        nav_img.setVisibility(View.GONE);
        img_warning.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        hideItem();

        final UserShared userShared = new UserShared(BaseActivity.this);

        sup_id = userShared.getSupplier_id();

        if (!sup_id.equals("")) {
            Toast.makeText(this, sup_id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BaseActivity.this, Add_Items.class);
            startActivity(intent);
            finish();
            ShowItemSupplier();
        }
        if (!userShared.getUser_id().equals("")) {
            Toast.makeText(this, userShared.getUser_id(), Toast.LENGTH_SHORT).show();
            signIn.setImageResource(R.drawable.avatar);
            signIn.setEnabled(false);
            logoutLayout.setVisibility(View.VISIBLE);
            nav_name.setText("Wellcome User");
            nav_contact.setText("0300");
            nav_img.setVisibility(View.VISIBLE);
            img_login.setVisibility(View.GONE);
            nav_login.setText("");
            ShowItemUser();
        }

        if (!isConnectedToInternet(BaseActivity.this)) {
            Toast.makeText(BaseActivity.this, "Can't connect to Internet!", Toast.LENGTH_SHORT).show();
        }
//        fillExampleList();

    }

    private boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    public void LogOut(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setTitle("Confirm:");
        builder.setMessage("Are you sure you want to Log Out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                new UserShared(BaseActivity.this).removerUser();
                startActivity(new Intent(getApplicationContext(), BaseActivity.class));
                finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initialviews() {
        signIn = findViewById(R.id.text_SignIn_Main);
        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        logoutLayout = findViewById(R.id.logout_layout);
        today = findViewById(R.id.tv_today_menu);
        available = findViewById(R.id.tv_now);
        tv_warning = findViewById(R.id.tv_warning);
        img_warning = findViewById(R.id.img_warning);
        progressBar = findViewById(R.id.progressBar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nav_name = headerView.findViewById(R.id.nav_name);
        nav_contact = headerView.findViewById(R.id.nav_contact);
        nav_img = headerView.findViewById(R.id.nav_img);
        nav_login = headerView.findViewById(R.id.nav_tv_login);
        img_login = headerView.findViewById(R.id.nav_login);
        currentDay = LocalDate.now().getDayOfWeek().name();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void hideItem() {
        navigationView = findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_Supplier_Profile).setVisible(false);
        nav_Menu.findItem(R.id.nav_Supplier_SignUp).setVisible(false);
        nav_Menu.findItem(R.id.nav_Add_Item).setVisible(false);
        nav_Menu.findItem(R.id.nav_User_Profile).setVisible(false);
    }

    private void ShowItemSupplier() {
        navigationView = findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_Supplier_SignUp).setVisible(true);
        nav_Menu.findItem(R.id.nav_Supplier_Profile).setVisible(true);
        nav_Menu.findItem(R.id.nav_Add_Item).setVisible(true);
    }
    private void ShowItemUser() {
        navigationView = findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_User_Profile).setVisible(true);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Supplier_SignUp) {
            Intent intent = new Intent(BaseActivity.this, Register_Activity_supplier.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_Add_Item) {
            Intent intent = new Intent(BaseActivity.this, Add_Items.class);
            startActivity(intent);
        } else if (id == R.id.nav_Supplier_Profile) {
            Intent i = new Intent(BaseActivity.this, Supplier_Account.class);
            startActivity(i);
        }else if (id == R.id.nav_User_Profile) {
            Intent i = new Intent(BaseActivity.this, Supplier_Account.class);
            startActivity(i);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Get all Data of Main Card View
    private void getToDaymenu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        User user = new User();
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.TODAY_MENU);
        user.setDay(currentDay);
        request.setUser(user);

        RequestInterfacePart requestInterface = retrofit.create(RequestInterfacePart.class);

        Call<ServerResponce> response = requestInterface.operationone(request);

        response.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    ServerResponce resp = response.body();

                    Toast.makeText(BaseActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                    if (resp.getResult().equals(Constants.SUCCESS)) {
                        User user = resp.getUser();
                        name = user.getName();
                        itemName = user.getItemName();
                        address = user.getAddress();
                        supplier_id = user.getSupplier_id();
                        price = user.getPrice();
                        item_image = user.getItem_image();



                        modelClasses = new ArrayList<>();
                        for (int i = 0; i < address.size(); i++) {
                            modelClasses.add(new ModelClass(name.get(i), itemName.get(i), address.get(i), supplier_id.get(i), item_image.get(i), price.get(i)));
                        }

                        setUpRecyclerViewToday();
                        today.setText("Today's Menu");
                        available.setText("Available Now");
                        setUpRecyclerViewAll();
                        progressBar.setVisibility(View.GONE);
                    }
                    if (resp.getResult().equals(Constants.EMPTY)) {
                        tv_warning.setText("Sorry no Item Found..!!!");
                        img_warning.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    Toast.makeText(BaseActivity.this, "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {

                Toast.makeText(BaseActivity.this, "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerViewToday() {

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        if (modelClasses == null) {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new AdapterClass(modelClasses, getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    private void setUpRecyclerViewAll() {

        recyclerView_all = findViewById(R.id.recycler_view1);

        recyclerView_all.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        if (modelClasses == null) {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        } else {
            adaptertype = new AdapterServiceType(modelClasses, getApplicationContext());
            recyclerView_all.setLayoutManager(layoutManager);
            recyclerView_all.setAdapter(adaptertype);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        MenuItem searchIItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchIItem);
        searchView.setQueryHint(Html.fromHtml("<font color = #F35746>" + getResources().getString(R.string.hintSearchMess) + "</font>"));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter == null) {
                    Toast.makeText(BaseActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
                } else
                    adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}