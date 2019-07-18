package com.kamores.tiffin;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add_Supplier extends AppCompatActivity {
    EditText etName,etService, etContact;
    TextView addressTV;
    ImageView addSupplier;
    String Sup_name,Sup_service,Sup_con,Sup_location,Sup_detail;
    public static String Supplier_id,Service_id;

    LocationManager locationManager;
    android.location.LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier );

        initViewSuppliers();

        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sup_name = etName.getText().toString();
                Sup_service = etService.getText().toString();
                Sup_con = etContact.getText().toString();
                if (Sup_name.equals("")) {
                    etName.setError("Add Name!");
                } else if (Sup_service.equals("")){
                    etService.setError("Add Service!");
                } else if (Sup_con.equals("") || Sup_con.length()<11){
                    etContact.setError("Add Complete Mobile Number!");
                } else {
                    Intent intent= new Intent(Add_Supplier.this, Add_Items.class);
                    startActivity(intent);
                }
                getValues();
                addSuppliers();
            }
        });
    }

    private void getValues() {
        Sup_name = etName.getText().toString();
        Sup_service = etService.getText().toString();
        Sup_con = etContact.getText().toString();
        Sup_location = "Khanewal";
        Sup_detail = "Some Detail";
    }

    public void initViewSuppliers(){
        etName =findViewById(R.id.et_supplier_name);
        etService= findViewById(R.id.et_supplier_serviceName);
        etContact= findViewById(R.id.et_supplier_contact);
        addressTV = findViewById(R.id.tv_supplier_address);
        addSupplier= findViewById(R.id.img_AddSuppliers);
        //cruntAddress();
    }

    public void cruntAddress() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location: ", location.toString());
                updateLocationInfo(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            Toast.makeText(this, "Grant the location access!", Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) locationListener);
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastKnownLocation != null) {
            updateLocationInfo(lastKnownLocation);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListening();
        }

    }
    public void startListening()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) locationListener);

    }
    public void updateLocationInfo(Location location)
    {
        Log.i("Location1: ", location.toString());
        String address = "Could not find any address";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if (listAddresses != null && listAddresses.size() > 0) {
                address = "";
                if (listAddresses.get(0).getThoroughfare() != null) {
                    address = listAddresses.get(0).getThoroughfare() + "\n";
                }
                if (listAddresses.get(0).getLocality() != null) {
                    address += listAddresses.get(0).getLocality() + " ";
                }
                if (listAddresses.get(0).getPostalCode() != null) {
                    address += listAddresses.get(0).getPostalCode() + " ";
                }
                if (listAddresses.get(0).getAdminArea() != null) {
                    address += listAddresses.get(0).getAdminArea();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        addressTV.setText(address);
    }
    private void addSuppliers() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);
        final Suppliers suppliers = new Suppliers();


        suppliers.setService_name(Sup_service);
        suppliers.setService_detail(Sup_detail);
        suppliers.setSupplier_name(Sup_name);
        suppliers.setSupplier_contact(Sup_con);
        suppliers.setSupplier_location(Sup_location);
        suppliers.setUser_id("1");


        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_SERVICE);
        request.setSuppliers(suppliers);

        Call<ServerResponce> resp = requestInterfacePart.operationone(request);

        resp.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    Suppliers suppliers1 = new Suppliers();
                    ServerResponce resp = response.body();
                    suppliers1 = resp.getSuppliers();


                    Supplier_id = suppliers1.getSupplier_id();
                    Service_id = suppliers1.getService_id();

                    setUpIntent();

//                    Toast.makeText(Add_Supplier.this, ""+suppliers1.getSupplier_id(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Add_Supplier.this, ""+suppliers1.getService_id(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Add_Supplier.this, "" + resp.getMessage(), Toast.LENGTH_LONG).show();
                }
                catch(Exception e){
                    Toast.makeText(Add_Supplier.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Add_Supplier.this, "Connection Failure "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setUpIntent() {
        Intent intent = new Intent(Add_Supplier.this,Add_Items.class);
        intent.putExtra("Supplier_id",Supplier_id);
        intent.putExtra("Service_id",Service_id);
        startActivity(intent);
    }

}