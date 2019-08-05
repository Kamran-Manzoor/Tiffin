package com.kamores.tiffin.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.kamores.tiffin.Adapter.AdapterClass;
import com.kamores.tiffin.Adapter.AdapterServiceType;
import com.kamores.tiffin.Constants.Constants;
import com.kamores.tiffin.ModelClasses.ModelClass;
import com.kamores.tiffin.R;
import com.kamores.tiffin.Constants.RequestInterface;
import com.kamores.tiffin.Constants.ServerResponce;
import com.kamores.tiffin.ModelClasses.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    TextView signIn;
    User user;
    RecyclerView recyclerView,recyclerView_all;
    private AdapterClass adapter;
    private AdapterServiceType adaptertype;
    Toolbar toolbar;
    NavigationView navigationView;
    LinearLayout logoutLayout;
    TextView today,available;
    ProgressBar progressBar;

    private List<ModelClass> modelClasses;
        ArrayList<String> itemName;
        ArrayList<String> name;
        ArrayList<String> supplier_id;
        ArrayList<String> address;
        ArrayList<String> price;
        ArrayList<String> item_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_base );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        //views Initialise
        initialviews();

        user = new User();
        getToDaymenu();

        modelClasses = new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        signIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( BaseActivity.this, Login_Activity.class );
                startActivity( intent );
            }
        } );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close );
        mDrawerLayout.addDrawerListener( toggle );
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(Color.RED);
        navigationView.setNavigationItemSelectedListener( this );
        ColorStateList csl = new ColorStateList(
                new int[][] {
                        new int[] {-android.R.attr.state_checked}, // unchecked
                        new int[] { android.R.attr.state_checked}  // checked
                },
                new int[] {
                        Color.BLACK,
                        Color.RED
                }
        );
        navigationView.setItemTextColor(csl);
        navigationView.setItemIconTintList(csl);
        logoutLayout.setVisibility(View.GONE);
        today.setText("");
        available.setText("");
        progressBar.setVisibility(View.VISIBLE);

        if(!isConnectedToInternet(BaseActivity.this)){
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

    private void initialviews() {
        signIn = findViewById( R.id.text_SignIn_Main );
        toolbar = findViewById( R.id.toolbar );
        mDrawerLayout = findViewById( R.id.drawer_layout );
        navigationView = findViewById( R.id.nav_view );
        logoutLayout=findViewById(R.id.logout_layout);
        today=findViewById(R.id.tv_today_menu);
        available=findViewById(R.id.tv_now);
        progressBar=findViewById(R.id.progressBar);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Supplier) {
            Intent intent = new Intent( BaseActivity.this, Login_Activity_Supplier.class );
            startActivity( intent );
        } else if (id == R.id.nav_Save_Location) {
            Intent intent = new Intent( BaseActivity.this, Add_Items.class );
            startActivity( intent );
        } else if (id == R.id.nav_show_In_Map) {
            Intent i = new Intent(BaseActivity.this,MapsActivity.class);
            startActivity(i);
        }
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );

        return true;
    }

//Get all Data of Main Card View
    private void getToDaymenu() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl( Constants.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() ).build();

        RequestInterface requestInterface = retrofit.create( RequestInterface.class );
        Call<ServerResponce> response = requestInterface.operation();

        response.enqueue( new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    ServerResponce resp = response.body();
                    user = resp.getUser();
                    name = user.getName();
                    itemName = user.getItemName();
                    address = user.getAddress();
                    supplier_id = user.getSupplier_id();
                    price = user.getPrice();
                    item_image = user.getItem_image();


                    modelClasses = new ArrayList<>();
                    for (int i = 0; i < address.size(); i++) {
                        modelClasses.add( new ModelClass( name.get( i ),itemName.get( i ) ,address.get( i ),supplier_id.get( i ),item_image.get( i ),price.get( i )));
                    }
                    setUpRecyclerViewToday();
                    today.setText("Today's Menu");
                    available.setText("Available Now");
                    setUpRecyclerViewAll();
                    progressBar.setVisibility(View.GONE);

                } catch (Exception e) {
                    Toast.makeText( BaseActivity.this, "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText( BaseActivity.this, "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void setUpRecyclerViewToday(){

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        if (modelClasses == null){
            Toast.makeText( this, "Null", Toast.LENGTH_SHORT ).show();
        }
        else {
            adapter = new AdapterClass(modelClasses, getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
    private void setUpRecyclerViewAll(){

        recyclerView_all = findViewById(R.id.recycler_view1);

        recyclerView_all.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        if (modelClasses == null){
            Toast.makeText( this, "Null", Toast.LENGTH_SHORT ).show();
        }
        else {
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
                if (adapter == null){
                    Toast.makeText( BaseActivity.this, "Not Connected", Toast.LENGTH_SHORT ).show();
                }
                else
                    adapter.getFilter().filter( newText );
                    return false;
            }
        });
        return true;
    }




}