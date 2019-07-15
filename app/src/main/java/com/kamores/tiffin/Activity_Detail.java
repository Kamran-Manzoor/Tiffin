package com.kamores.tiffin;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.view.menu.MenuView;

import com.kamores.tiffin.Fragment.FragmentDay;
import com.kamores.tiffin.Fragment.FragmentWeek;

public class Activity_Detail extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    Bundle bundle;
    Button update;


    MenuView itemCall, itemSMS, itemShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        update = findViewById(R.id.update);

//        if (checkAddOrUpdate()){
//            Toast.makeText(this, "Please Update", Toast.LENGTH_SHORT).show();
//        }


//        FragmentDay fragmentDay = new FragmentDay();//Get Fragment Instance
//        Bundle data = new Bundle();//Use bundle to pass data
//        data.putString("data", "This is Argument Fragment");//put string, int, etc in bundle with a key value
//        fragmentDay.setArguments(data);//Finally set argument bundle to fragment


//            Bundle bundle = getIntent().getExtras();
//            String id = bundle.getString("Supplier_id");
//
//            FragmentDay fragmentDay = new FragmentDay();
//            fragmentDay.setArguments(bundle);
//        initDayName();

//        itemCall= findViewById(R.id.action_call);
//        itemSMS= findViewById(R.id.action_sms);
//        itemShare= findViewById(R.id.action_share);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.pageViewer);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentDay(), "Current");
        adapter.AddFragment(new FragmentWeek(), "Week");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

//        // coed for Call Button
//        imageCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData( Uri.parse("tel:"));
//                startActivity(intent);
//            }
//        });
//        // coed for TextMessage Button
//        imageSMS.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint({"WrongViewCast", "MissingPermission"})
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "1234567890", null));
//                intent.putExtra("sms-body", "Pakistan...");
//                startActivity(intent);
//            }
//        });


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setElevation(0);
    }


}