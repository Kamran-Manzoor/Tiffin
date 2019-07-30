package com.kamores.tiffin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.view.MenuItemCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
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

import android.widget.TextView;
import android.widget.Toast;


import com.kamores.tiffin.Constants.Constants;

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
    RecyclerView recyclerView;
    private AdapterClass adapter;
    Toolbar toolbar;
    NavigationView navigationView;

    private List<ModelClass> modelClasses;
    ArrayList<String> service_name;
    ArrayList<String> sup_name;
    ArrayList<String> location;
    ArrayList<String> sup_id;
    ArrayList<String> sup_contact;
    ArrayList<String> item_image;

    ProgressDialog progressDialog;
    SwipeRefreshLayout refresh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_base );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
      //  toolbar.setTitleTextColor(getResources().getColor(android.R.color.holo_red_light));

        //views Initialise
        initialviews();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                new Handler().postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        getUsers();
                        Toast.makeText(BaseActivity.this, "Refresh Complete", Toast.LENGTH_SHORT).show();
                        refresh.setRefreshing(false);
                    }
                },1000);
            }
        });

        user = new User();
        getUsers();

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
//        fillExampleList();

    }

    private void initialviews() {
        refresh = findViewById( R.id.refresh);
        signIn = findViewById( R.id.text_SignIn_Main );
        toolbar = findViewById( R.id.toolbar );
        mDrawerLayout = findViewById( R.id.drawer_layout );
        navigationView = findViewById( R.id.nav_view );
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
            Intent intent = new Intent( BaseActivity.this, Add_Supplier.class );
            startActivity( intent );
        } else if (id == R.id.nav_Save_Location) {
        } else if (id == R.id.nav_show_In_Map) {
            Intent i = new Intent(BaseActivity.this,MapsActivity.class);
            startActivity(i);
        }
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );

        return true;
    }

//Get all Data of Main Card View
    private void getUsers() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl( Constants.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() ).build();

//        ServerRequest request = new ServerRequest();
//        request.setOperation( Constants.RETRIEVE_ALL );

        RequestInterface requestInterface = retrofit.create( RequestInterface.class );
        Call<ServerResponce> response = requestInterface.operation();

        response.enqueue( new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    ServerResponce resp = response.body();
                    user = resp.getUser();
                    sup_name = user.getSup_name();
                    service_name = user.getService_name();
                    location = user.getLocation();
                    sup_id = user.getSupplier_id();
                    sup_contact = user.getSup_contact();
                    sup_contact = user.getSup_contact();
                    item_image = user.getItem_image();
                    //progressDialog.dismiss();

                    modelClasses = new ArrayList<>();
                    for (int i = 0; i < location.size(); i++) {
                        modelClasses.add( new ModelClass( sup_name.get( i ),service_name.get( i ) ,location.get( i ),sup_id.get( i ),sup_contact.get( i ),item_image.get( i )));
                    }
                    setUpRecyclerView();

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
    private void setUpRecyclerView(){

        recyclerView = findViewById(R.id.recycler_view);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        MenuItem searchIItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchIItem);
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