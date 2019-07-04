package com.kamores.tiffin;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add_Items extends AppCompatActivity {
    ImageView itemImage;
    EditText itemName, itemPrice,itemDescription;
    Spinner spinnerDays;
    Button btnChooseImage, btnAddItem;
    List<String> listDays;
    String Item_name,Item_price,Item_days,Item_image,Item_desc;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items );

        initViewItems();
        getValues();

//        btnChooseImage.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openFileChooser();
//            }
//        });

        btnAddItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addSuppliers();
                addItems();
//                Intent intent = new Intent(Add_Items.this,BaseActivity.class);
//                startActivity(intent);
            }
        });

        showDays();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listDays);
        spinnerDays.setAdapter(adapter);
        spinnerDays.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item_days = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addItems() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);
        Items items = new Items();
        items.setItem_name(Item_name);
        items.setItem_price(Item_price);
        items.setItem_image(Item_image);
        items.setDay(Item_days);
        items.setDescription(Item_desc);
        items.setService_id("1");
        items.setSupllier_id("2");


        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.ADD_ITEMS);
        request.setItems(items);

        Call<ServerResponce> resp = requestInterfacePart.operationone(request);

        resp.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    ServerResponce resp = response.body();
                    Toast.makeText(Add_Items.this, "" + resp.getMessage(), Toast.LENGTH_LONG).show();
                }
                catch(Exception e){
                    Toast.makeText(Add_Items.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Add_Items.this, "Connection Failure "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void getValues() {
        Item_name = itemName.getText().toString();
        Item_price = itemPrice.getText().toString();
        Item_desc = itemDescription.getText().toString();
        Item_image = "some.jpg";
    }

    public void initViewItems(){
        itemImage= findViewById(R.id.img_New_Items);
        itemName= findViewById(R.id.et_ItemName);
        itemPrice= findViewById(R.id.et_ItemPrice);
        itemDescription= findViewById(R.id.et_ItemDescription);
        spinnerDays= findViewById(R.id.spinnerDays);
        btnChooseImage= findViewById(R.id.btn_choose_items);
        btnAddItem=findViewById(R.id.btn_Add_Items);
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void showDays() {
        listDays = new ArrayList<>();
        listDays.add("Monday");
        listDays.add("Tuesday");
        listDays.add("Wednesday");
        listDays.add("Thursday");
        listDays.add("Friday");
        listDays.add("Saturday");
        listDays.add("Sunday");
    }
}