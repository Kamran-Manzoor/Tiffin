package com.kamores.tiffin.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kamores.tiffin.Constants;
import com.kamores.tiffin.CustomeAdapterItems;
import com.kamores.tiffin.ModelClass;
import com.kamores.tiffin.R;
import com.kamores.tiffin.RequestInterfacePart;
import com.kamores.tiffin.ServerRequest;
import com.kamores.tiffin.ServerResponce;
import com.kamores.tiffin.Suppliers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentDay extends Fragment {

    TextView to_name, to_service, to_Location;
    RecyclerView recyclerView;
    private CustomeAdapterItems adapter;
    View view;

    private List<ModelClass> modelClasses;
    ArrayList<String> item_name;
    ArrayList<String> item_price;


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
                    ServerResponce resp = response.body();
                    suppliers = resp.getSuppliers();
                    String Sup_name = suppliers.getSupplier_name();
                    String Ser_name = suppliers.getService_name();
                    String Location = suppliers.getSupplier_location();
                    String Contact_no = suppliers.getSupplier_contact();

                    to_name.setText(Sup_name);
                    to_service.setText(Ser_name);
                    to_Location.setText(Location);
                    Toast.makeText(getContext(), ""+Contact_no, Toast.LENGTH_SHORT).show();
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
        view = inflater.inflate(R.layout.day_fragment, container, false);
        to_name = view.findViewById(R.id.to_showname);
        to_service = view.findViewById(R.id.to_service);
        to_Location = view.findViewById(R.id.to_showlocation);
        recyclerView = view.findViewById(R.id.recycler_view_day);

        getDataSupplier();
        String day = getDay();
        getDataItems(day);
        return view;
    }



        public void getDataItems(String day) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);

            Suppliers suppliers = new Suppliers();
            suppliers.setSupplier_id("2");
            suppliers.setDay(day);

            ServerRequest request = new ServerRequest();
            request.setOperation(Constants.RETRIVE_ITEMS);
            request.setSuppliers(suppliers);


            Call<ServerResponce> response = requestInterfacePart.operationone(request);

            response.enqueue(new Callback<ServerResponce>() {
                Suppliers suppliers;

                @Override
                public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                    try {
                        ServerResponce resp = response.body();
                        suppliers = resp.getSuppliers();
                        item_name =suppliers.getItem_name();
                        item_price = suppliers.getItem_price();

                        modelClasses = new ArrayList<>();
                        for (int i = 0; i < item_price.size(); i++) {
                            modelClasses.add( new ModelClass( item_name.get( i ),item_price.get( i )));
                        }
                        setUpRecyclerView();

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ServerResponce> call, Throwable t) {
                    Toast.makeText(getContext(), "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    public void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        if (modelClasses == null){
            Toast.makeText( getContext(), "Null", Toast.LENGTH_SHORT ).show();
        }
        else {
            adapter = new CustomeAdapterItems(modelClasses, getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

    }

    private String getDay() {
            Calendar calendar;
            String currentDay = "";
            calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            switch (day) {
                case Calendar.SUNDAY:
                    currentDay = "Sunday";
                    //Toast.makeText(this, "SUNDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.MONDAY:
                    currentDay = "Monday";
                    //Toast.makeText(this, "MONDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.TUESDAY:
                    currentDay = "Tuesday";
                    //Toast.makeText(this, "TUESDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.WEDNESDAY:
                    currentDay = "Wednesday";
                    //Toast.makeText(this, "WEDNESDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.THURSDAY:
                    currentDay = "Thursday";
                    //Toast.makeText(this, "THURSDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.FRIDAY:
                    currentDay = "Friday";
                    //Toast.makeText(this, "FRIDAY", Toast.LENGTH_SHORT).show();
                    break;
                case Calendar.SATURDAY:
                    currentDay = "Saturday";
                    // Toast.makeText(this, "SATURDAY", Toast.LENGTH_SHORT).show();
                    break;
            }
            return currentDay;
        }
    }




