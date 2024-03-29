//package com.kamores.tiffin.Fragment;
//
//import android.content.ClipData;
//import android.content.ClipboardManager;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.kamores.tiffin.Activities.Activity_Detail;
//import com.kamores.tiffin.Adapter.AdapterClass;
//import com.kamores.tiffin.Constants.Constants;
//import com.kamores.tiffin.Adapter.CustomeAdapterItems;
//import com.kamores.tiffin.ModelClasses.ModelClass;
//import com.kamores.tiffin.R;
//import com.kamores.tiffin.Constants.RequestInterfacePart;
//import com.kamores.tiffin.Constants.ServerRequest;
//import com.kamores.tiffin.Constants.ServerResponce;
//import com.kamores.tiffin.ModelClasses.Supplier;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class FragmentDay extends Fragment {
//
//    TextView to_name, to_service, to_Location,phone;
//    private ClipboardManager myClipboard;
//    private ClipData myClip;
//    RecyclerView recyclerView;
//    Bitmap bitmap;
//    String URLIMAGE;
//    Bitmap myImage = null;
//    Context mcontex;
//    Button button;
//    ImageView imageView_show;
//    private CustomeAdapterItems adapter;
//    View view;
//    public static String Selected_Day;
//
//    private List<ModelClass> modelClasses;
//    ArrayList<String> item_name;
//    ArrayList<String> item_price;
//
//    public FragmentDay() {
//
//    }
//
//    private void getDataSupplier() {
//
//        Retrofit retrofit = new Retrofit.Builder().baseUrl( Constants.BASE_URL ).addConverterFactory( GsonConverterFactory.create() ).build();
//        RequestInterfacePart requestInterfacePart = retrofit.create( RequestInterfacePart.class );
//
//        Supplier suppliers = new Supplier();
//       // suppliers.setSupplier_id(Activity_Detail.Sup_id);
//
//        ServerRequest request = new ServerRequest();
//        request.setOperation(Constants.RETRIVE_DETAIL);
//        request.setSupplier(suppliers);
//
//        Call<ServerResponce> response = requestInterfacePart.operationone(request);
//
//        response.enqueue( new Callback<ServerResponce>() {
//            Supplier suppliers;
//            @Override
//            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
//                try {
//                    ServerResponce resp = response.body();
//                    suppliers = resp.getSupplier();
////                    String Sup_name = suppliers.getSupplier_name();
////                    String Ser_name = suppliers.getService_name();
////                    String Location = suppliers.getSupplier_location();
////                    String Contact_no = suppliers.getSupplier_contact();
//
////                    to_name.setText(Sup_name);
////                    to_service.setText(Ser_name);
////                    to_Location.setText(Location);
////                    //Toast.makeText(getContext(), ""+Contact_no, Toast.LENGTH_SHORT).show();
////                    phone.setText(Contact_no);
////                    //Toast.makeText(getContext(), ""+Contact_no, Toast.LENGTH_SHORT).show();
////                } catch (Exception e) {
//                    Toast.makeText( getContext(), "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ServerResponce> call, Throwable t) {
//                Toast.makeText( getContext(), "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
//            }
//        } );
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.day_fragment, container, false);
//        to_name = view.findViewById(R.id.to_showname);
//        to_service = view.findViewById(R.id.to_service);
//        to_Location = view.findViewById(R.id.to_showlocation);
//        recyclerView = view.findViewById(R.id.recycler_view_day);
//        phone = view.findViewById(R.id.to_showContact);
////        button = view.findViewById(R.id.btn_phone);
//        imageView_show = view.findViewById(R.id.image_view_show);
//
//        URLIMAGE = Constants.BASE_URL+"TiffinApp/uploads/"+AdapterClass.image_name+".jpg";
//
//        new GetImageFromURL(imageView_show).execute(URLIMAGE);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                myClipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
//
//
//                String text;
//                text = phone.getText().toString();
//
//                myClip = ClipData.newPlainText("text", text);
//                myClipboard.setPrimaryClip(myClip);
//
//                Toast.makeText(getContext(), "Text Copied",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//        String day;
//        getDataSupplier();
//        if (Activity_Detail.Day !=null){
//            Selected_Day = Activity_Detail.Day;
//        }
//        else {
//            day = getDay();
//          Selected_Day = day;
//        }
//        getDataItems(Selected_Day);
//        return view;
//    }
//
//    public class GetImageFromURL extends AsyncTask<String,Void, Bitmap> {
//        ImageView imageView;
//
//        public GetImageFromURL(ImageView imageView) {
//            this.imageView = imageView;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            String uriDisplay = strings[0];
//            bitmap = null;
//            try {
//                InputStream str = new java.net.URL(uriDisplay).openStream();
//                bitmap = BitmapFactory.decodeStream(str);
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//
//            return bitmap;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//            imageView.setImageBitmap(bitmap);
//        }
//    }
//
//
//
//        public void getDataItems(String day) {
//            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//            RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);
//
//            Supplier suppliers = new Supplier();
//            suppliers.setSupplier_id(Activity_Detail.Sup_id);
//            suppliers.setDay(day);
//
//            ServerRequest request = new ServerRequest();
//            request.setOperation(Constants.RETRIVE_ITEMS);
//            request.setSupplier(suppliers);
//
//
//            Call<ServerResponce> response = requestInterfacePart.operationone(request);
//
//            response.enqueue(new Callback<ServerResponce>() {
//                Supplier suppliers;
//
//                @Override
//                public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
//                    try {
//                        ServerResponce resp = response.body();
//                        if (resp.getResult().equals(Constants.SUCCESS)){
//                            suppliers = resp.getSupplier();
//                            item_name =suppliers.getItem_name();
//                            item_price = suppliers.getItem_price();
//
//                            modelClasses = new ArrayList<>();
//                            for (int i = 0; i < item_price.size(); i++) {
//                              //  modelClasses.add( new ModelClass( item_name.get( i ),item_price.get( i )));
//                            }
//                            setUpRecyclerView();
//                        }
//                        else {
//                            Toast.makeText(getContext(), "Sorry No Record Found on this Day", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    } catch (Exception e) {
//                        Toast.makeText(getContext(), "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ServerResponce> call, Throwable t) {
//                    Toast.makeText(getContext(), "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//    public void setUpRecyclerView() {
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//
//        if (modelClasses == null){
//            Toast.makeText( getContext(), "Null", Toast.LENGTH_SHORT ).show();
//        }
//        else {
//            adapter = new CustomeAdapterItems(modelClasses, getContext());
//            recyclerView.setLayoutManager(layoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//    }
//
//    private String getDay() {
//            Calendar calendar;
//            String currentDay = "";
//            calendar = Calendar.getInstance();
//            int day = calendar.get(Calendar.DAY_OF_WEEK);
//            switch (day) {
//                case Calendar.SUNDAY:
//                    currentDay = "Sunday";
//                    //Toast.makeText(this, "SUNDAY", Toast.LENGTH_SHORT).show();
//                    break;
//                case Calendar.MONDAY:
//                    currentDay = "Monday";
//                    //Toast.makeText(this, "MONDAY", Toast.LENGTH_SHORT).show();
//                    break;
//                case Calendar.TUESDAY:
//                    currentDay = "Tuesday";
//                    //Toast.makeText(this, "TUESDAY", Toast.LENGTH_SHORT).show();
//                    break;
//                case Calendar.WEDNESDAY:
//                    currentDay = "Wednesday";
//                    //Toast.makeText(this, "WEDNESDAY", Toast.LENGTH_SHORT).show();
//                    break;
//                case Calendar.THURSDAY:
//                    currentDay = "Thursday";
//                    //Toast.makeText(this, "THURSDAY", Toast.LENGTH_SHORT).show();
//                    break;
//                case Calendar.FRIDAY:
//                    currentDay = "Friday";
//                    //Toast.makeText(this, "FRIDAY", Toast.LENGTH_SHORT).show();
//                    break;
//                case Calendar.SATURDAY:
//                    currentDay = "Saturday";
//                    // Toast.makeText(this, "SATURDAY", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            return currentDay;
//        }
//    }