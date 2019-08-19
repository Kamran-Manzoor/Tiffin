package com.kamores.tiffin.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kamores.tiffin.adapters.Adapter_Supplier;
import com.kamores.tiffin.constants.Constants;
import com.kamores.tiffin.interfaces.RequestInterfacePart;
import com.kamores.tiffin.constants.ServerRequest;
import com.kamores.tiffin.constants.ServerResponce;
import com.kamores.tiffin.models.ModelClass;
import com.kamores.tiffin.models.ModelClass_Supplier;
import com.kamores.tiffin.models.Supplier_Model;
import com.kamores.tiffin.R;
import com.kamores.tiffin.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Supplier_profile extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_Supplier adapter;
    String currentDay,supplier_id;
    ImageButton imageButton;
    ImageView img_suplier;
    TextView name1, contact1, address1;
    Bundle bundle;

    private List<ModelClass> modelClasses;
    String itemName;
    String name;
    String address;
    String contact;
    String supplier_image;
    String price;
    String item_image;
    String supplier__id;
    String type;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_profile);
        getSupplierMenu();

        bundle = getIntent().getExtras();
        supplier_id = bundle.getString( "Supplier_id" );

        currentDay = LocalDate.now().getDayOfWeek().name();






        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

//        modelClass = new ArrayList<>();

        initialviews();



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Supplier_profile.this, BaseActivity.class);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initialviews() {
        imageButton = findViewById(R.id.previous);
        name1 = findViewById(R.id.tv_supplier_name);
        contact1 = findViewById(R.id.tv_supplier_contact);
        address1 = findViewById(R.id.tv_supplier_Address);
        recyclerView = findViewById(R.id.rv_supplier_profile);
        img_suplier = findViewById(R.id.img_supplier_profile);







//        Toast.makeText(this, currentDay, Toast.LENGTH_SHORT).show();

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

    //Get all Data of Supplier Profile Card View
    private void getSupplierMenu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Supplier_Model supplier_model = new Supplier_Model();
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.PROFILE_MENU);
        supplier_model.setSup_id("1");
        supplier_model.setDay( "Monday" );
        request.setSupplier_model(supplier_model);


        RequestInterfacePart requestInterface = retrofit.create(RequestInterfacePart.class);

        Call<ServerResponce> response = requestInterface.operationone(request);
        response.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {

                    ServerResponce resp = response.body();
                    Toast.makeText( Supplier_profile.this, ""+resp.getMessage(), Toast.LENGTH_SHORT ).show();

                    //assert resp != null;
                    //Toast.makeText(Supplier_profile.this, resp.getMessage(), Toast.LENGTH_SHORT).show();

                    Supplier_Model supplier_model = resp.getSupplier_model();
                    name = supplier_model.getName();

                    //itemName = supplier_model.getItemName();
                    address = supplier_model.getAddress();
                    //supplier__id = supplier_model.getSupplier_id();
                    //price = supplier_model.getPrice();
                    //item_image = supplier_model.getItem_image();
                    contact=supplier_model.getContact();
                    //supplier_image=supplier_model.getSupplier_image();
                    //type=supplier_model.getType();


                    name1.setText( name );
                    contact1.setText( contact );
                    address1.setText( address );

//
//                    modelClass = new ArrayList<>();
//                    for (int i = 0; i < itemName.size(); i++) {
//                        modelClass.add(new ModelClass_Supplier(itemName.get(i), item_image.get(i), price.get(i), day.get(i), service.get(i)));
//                    }
//                    setUpRecyclerViewToday();


                } catch (Exception e) {
                    Toast.makeText(Supplier_profile.this, "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Supplier_profile.this, "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

//    private void setUpRecyclerViewToday() {
//
//        recyclerView = findViewById(R.id.rv_supplier_profile);
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setHasFixedSize(true);
//        if (modelClass == null) {
//            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
//        } else {
//            adapter = new Adapter_Supplier(modelClass, getApplicationContext());
//            recyclerView.setAdapter(adapter);
//        }
//    }
}