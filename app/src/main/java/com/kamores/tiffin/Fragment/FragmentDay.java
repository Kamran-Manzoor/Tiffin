package com.kamores.tiffin.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kamores.tiffin.BaseActivity;
import com.kamores.tiffin.Constants;
import com.kamores.tiffin.ModelClass;
import com.kamores.tiffin.R;
import com.kamores.tiffin.RequestInterface;
import com.kamores.tiffin.RequestInterfacePart;
import com.kamores.tiffin.ServerRequest;
import com.kamores.tiffin.ServerResponce;
import com.kamores.tiffin.Suppliers;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentDay extends Fragment {

//    ImageView imageView;
    TextView to_name,to_service,to_Location;
    ListView list_View;
    View view;

    public FragmentDay() {

    }

    private void getDataSupplier() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl( Constants.BASE_URL ).addConverterFactory( GsonConverterFactory.create() ).build();
        RequestInterfacePart requestInterfacePart = retrofit.create( RequestInterfacePart.class );

        Suppliers suppliers = new Suppliers();
        suppliers.setSupplier_id("2");

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.RETRIVE_DETAIL);
        request.setSuppliers(suppliers);



        Call<ServerResponce> response = requestInterfacePart.operationone(request);

        response.enqueue( new Callback<ServerResponce>() {
            Suppliers suppliers;
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
//                    Suppliers suppliers = new Suppliers();
                    ServerResponce resp = response.body();
                    //Toast.makeText(getContext(), "Responce : "+resp.getMessage(), Toast.LENGTH_SHORT).show();
                    suppliers = resp.getSuppliers();
                    //Toast.makeText(getContext(), "Responce : "+resp.getMessage(), Toast.LENGTH_SHORT).show();
                    String Sup_name = suppliers.getSupplier_name();
                    String Ser_name = suppliers.getService_name();
                    String Location = suppliers.getSupplier_location();

                    to_name.setText(Sup_name);
                    to_service.setText(Ser_name);
                    to_Location.setText(Location);
//                    progressDialog.dismiss();
                    //Toast.makeText(getContext(), "Responce : "+resp.getSuppliers().getService_name(), Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    Toast.makeText( getContext(), "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText( getContext(), "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.day_fragment,container,false);
//        imageView = view.findViewById(R.id.to_showimage);
        to_name = view.findViewById(R.id.to_showname);
        to_service = view.findViewById(R.id.to_service);
        to_Location = view.findViewById(R.id.to_showlocation);
        list_View = view.findViewById(R.id.listView);
        String day = getDay();
        //Toast.makeText(getContext(), "Day : "+day, Toast.LENGTH_SHORT).show();
        getDataSupplier();
        getDataItems(day);

        return view;
    }

    private void getDataItems(String day) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl( Constants.BASE_URL ).addConverterFactory( GsonConverterFactory.create() ).build();
        RequestInterfacePart requestInterfacePart = retrofit.create( RequestInterfacePart.class );

        Suppliers suppliers = new Suppliers();
        suppliers.setSupplier_id("2");
        suppliers.setDay(day);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.RETRIVE_ITEMS);
        request.setSuppliers(suppliers);



        Call<ServerResponce> response = requestInterfacePart.operationone(request);

        response.enqueue( new Callback<ServerResponce>() {
            Suppliers suppliers;
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
//                    Suppliers suppliers = new Suppliers();
                    ServerResponce resp = response.body();
                    //Toast.makeText(getContext(), "Responce : "+resp.getMessage(), Toast.LENGTH_SHORT).show();
                    suppliers = resp.getSuppliers();
                    Toast.makeText(getContext(), "Responce : "+resp.getMessage(), Toast.LENGTH_SHORT).show();
//                    String Sup_name = suppliers.getSupplier_name();
//                    String Ser_name = suppliers.getService_name();
//                    String Location = suppliers.getSupplier_location();

//                    to_name.setText(Sup_name);
//                    to_service.setText(Ser_name);
//                    to_Location.setText(Location);
//                    progressDialog.dismiss();
                    //Toast.makeText(getContext(), "Responce : "+resp.getSuppliers().getService_name(), Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    Toast.makeText( getContext(), "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText( getContext(), "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private String getDay() {
        Calendar calendar;
        String currentDay = "";
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY:
                currentDay = "SUNDAY";
                //Toast.makeText(this, "SUNDAY", Toast.LENGTH_SHORT).show();
                break;
            case Calendar.MONDAY:
                currentDay = "MONDAY";
                //Toast.makeText(this, "MONDAY", Toast.LENGTH_SHORT).show();
                break;
            case Calendar.TUESDAY:
                currentDay = "TUESDAY";
                //Toast.makeText(this, "TUESDAY", Toast.LENGTH_SHORT).show();
                break;
            case Calendar.WEDNESDAY:
                currentDay = "WEDNESDAY";
                //Toast.makeText(this, "WEDNESDAY", Toast.LENGTH_SHORT).show();
                break;
            case Calendar.THURSDAY:
                currentDay = "THURSDAY";
                //Toast.makeText(this, "THURSDAY", Toast.LENGTH_SHORT).show();
                break;
            case Calendar.FRIDAY:
                currentDay = "FRIDAY";
                //Toast.makeText(this, "FRIDAY", Toast.LENGTH_SHORT).show();
                break;
            case Calendar.SATURDAY:
                currentDay = "SATURDAY";
               // Toast.makeText(this, "SATURDAY", Toast.LENGTH_SHORT).show();
                break;
        }
        return currentDay;
    }


}