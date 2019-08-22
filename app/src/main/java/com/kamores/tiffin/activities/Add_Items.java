package com.kamores.tiffin.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.kamores.tiffin.constants.Constants;
import com.kamores.tiffin.interfaces.RequestInterfacePart;
import com.kamores.tiffin.constants.ServerRequest;
import com.kamores.tiffin.constants.ServerResponce;
import com.kamores.tiffin.models.Item;
import com.kamores.tiffin.models.UserShared;
import com.kamores.tiffin.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add_Items extends AppCompatActivity {

    private static final int IMG_REQUEST = 777;
    String file_name;
    private Bitmap bitmap;

    ImageView itemImageChose;
    EditText itemName, itemPrice, itemDescription;
    AutoCompleteTextView spinnerDays, spinnerService;
    ImageButton imageButton;
    Button btnAddItem;
    List<String> listDays;
    List<String> listService;
    String image_code;
    String Item_name, sup_id, service_id, Item_price, Item_days, Item_image, Item_desc, Item_service;
    private static final int PICK_IMAGE_REQUEST = 1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViewItems();


        String currentDay=LocalDate.now().getDayOfWeek().name();
        Toast.makeText(this, currentDay, Toast.LENGTH_SHORT).show();

        itemImageChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addSuppliers();
                getValues();
                addItems();
//                Intent intent = new Intent(Add_Items.this,BaseActivity.class);
//                startActivity(intent);
            }
        });

//        showDays();
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDays);
//        adapter.setDropDownViewResource(R.layout.custom_spinner);
//        spinnerDays.setAdapter(adapter);
//        spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Item_days = parent.getItemAtPosition(position).toString();
//                Toast.makeText(Add_Items.this, Item_days, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        showService();
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item
//                , listService);
//        adapter1.setDropDownViewResource(R.layout.custom_spinner);
//        spinnerService.setAdapter(adapter1);
//        spinnerService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Item_service = parent.getItemAtPosition(position).toString();
//                Toast.makeText(Add_Items.this, Item_service, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    @SuppressLint("WrongViewCast")
    public void initViewItems() {
        itemName = findViewById(R.id.et_item_name);
        itemImageChose = findViewById(R.id.imageView_upper);
        spinnerService = findViewById(R.id.spinnerService);
        itemPrice = findViewById(R.id.et_item_price);
        itemDescription = findViewById(R.id.et_item_discription);
        spinnerDays = findViewById(R.id.spinnerDays);
       // btnChooseImage= findViewById(R.id.btn_choose_items);
        btnAddItem = findViewById(R.id.btn_Add_Items);
    }

    private void getValues() {
        Item_name = itemName.getText().toString();
        Item_price = itemPrice.getText().toString();
        Item_days = spinnerDays.getText().toString();
        Item_service = spinnerService.getText().toString();
        Item_desc = itemDescription.getText().toString();
        final UserShared userShared = new UserShared(Add_Items.this);
        sup_id = userShared.getSupplier_id();
        Toast.makeText(this,  sup_id, Toast.LENGTH_SHORT).show();
    }

    private void addItems() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);

        final String image = imageToString();
        Item item = new Item();
        item.setItem_name(Item_name);
        item.setItem_price(Item_price);
        item.setItem_image(file_name);
        item.setImage_code(image);
        item.setDay(Item_days);
        item.setDescription(Item_desc);
        item.setSupllier_id("1");
        item.setService_id(Item_service);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.ADD_ITEMS);
        request.setItem(item);

        Call<ServerResponce> resp = requestInterfacePart.operationone(request);

        resp.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    ServerResponce resp = response.body();
                    Toast.makeText(Add_Items.this, "" + resp.getMessage(), Toast.LENGTH_LONG).show();
                    setUpIntent();
                } catch (Exception e) {
                    Toast.makeText(Add_Items.this, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Add_Items.this, "Connection Failure" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpIntent() {
        Intent intent = new Intent(Add_Items.this, BaseActivity.class);
        startActivity(intent);
        finish();
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            file_name = getFileName(path);

            int pos = file_name.lastIndexOf(".");
            if (pos >= 0) {
                file_name = file_name.substring(0, pos);
            }

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                itemImageChose.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileName(Uri uri) {

        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
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

    public void showService() {
        listDays = new ArrayList<>();
        listDays.add("BreakFast");
        listDays.add("Lunch");
        listDays.add("Dinner");

    }
}