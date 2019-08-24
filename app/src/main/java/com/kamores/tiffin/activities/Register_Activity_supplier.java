package com.kamores.tiffin.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kamores.tiffin.constants.Constants;
import com.kamores.tiffin.interfaces.RequestInterfacePart;
import com.kamores.tiffin.constants.ServerRequest;
import com.kamores.tiffin.constants.ServerResponce;
import com.kamores.tiffin.models.Supplier;
import com.kamores.tiffin.models.UserShared;
import com.kamores.tiffin.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register_Activity_supplier extends AppCompatActivity {

    private static final int IMG_REQUEST = 777;
    String file_name;
    private Bitmap bitmap;

    private RelativeLayout rlayout;
    private Animation animation;
    EditText etfullName, etAddress;
    Button btn_signUp;
    ImageView supplier_image;
    ImageButton rg_bck;

    String name, address, supplierimage, user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_supplier);


        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        initViewSupplier();

        rg_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_Activity_supplier.this,BaseActivity.class);
                startActivity(intent);
                finish();

            }
        });


        supplier_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etfullName.getText().toString();
                String add = etAddress.getText().toString();
                if (name.equals("")) {
                    etfullName.setError("Add Name!");
                } else if (add.equals("")) {
                    etAddress.setError("Add Address!");

                } else {
                    addSupplier();
                }
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

    private void initViewSupplier() {
        etfullName = findViewById(R.id.tv_full_name);
        etAddress = findViewById(R.id.tv_address);
        supplier_image = findViewById(R.id.imageView_supplier);
        btn_signUp = findViewById(R.id.btn_signUp_supplier);
        rg_bck = findViewById(R.id.previous);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getValues() {
        name = etfullName.getText().toString().trim();
        address = etAddress.getText().toString().trim();
        UserShared user1 = new UserShared(Register_Activity_supplier.this);
        user_id = user1.getUser_id();

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
                supplier_image.setImageBitmap(bitmap);
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

    private void addSupplier() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RequestInterfacePart requestInterfacePart = retrofit.create(RequestInterfacePart.class);
        final Supplier supplier = new Supplier();
        final String image = imageToString();
        Toast.makeText(this, "" + image, Toast.LENGTH_LONG).show();

        getValues();

        supplier.setName(name);
        supplier.setAddress(address);
        supplier.setImage_name(file_name);
        supplier.setImage_code(image);
        supplier.setUser_id(user_id);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_SUPPLIER);
        request.setSupplier(supplier);

        Call<ServerResponce> resp = requestInterfacePart.operationone(request);

        resp.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                try {
                    Supplier supplier1 = new Supplier();
                    ServerResponce resp = response.body();
                    supplier1 = resp.getSupplier();

                    setUpIntent();

                } catch (Exception e) {
                    Toast.makeText(Register_Activity_supplier.this, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Register_Activity_supplier.this, "Connection Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setUpIntent() {
        Intent intent = new Intent(Register_Activity_supplier.this, Add_Items.class);
        startActivity(intent);
        finish();
    }

}
