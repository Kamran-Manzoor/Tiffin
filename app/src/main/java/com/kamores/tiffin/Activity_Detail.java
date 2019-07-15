package com.kamores.tiffin;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.view.menu.MenuView;

import com.kamores.tiffin.Fragment.FragmentDay;
import com.kamores.tiffin.Fragment.FragmentWeek;

public class Activity_Detail extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    Bundle bundle;
    public static String Sup_id,Day,Sup_contact;


    MenuView itemCall, itemSMS, itemShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail );

        bundle = getIntent().getExtras();
        Sup_contact = bundle.getString("Contact_info");
        Sup_id = bundle.getString("Supplier_id");
        Day = bundle.getString("Day");
//        initDayName();

//        itemCall= findViewById(R.id.action_call);
//        itemSMS= findViewById(R.id.action_sms);
//        itemShare= findViewById(R.id.action_share);

        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.pageViewer);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentDay(),"Current");
        adapter.AddFragment(new FragmentWeek(),"Week");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        

//        // coed for Call Button
//        itemCall.setOnClickListener(new View.OnClickListener() {
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

    public String getMyData() {
        return Sup_id;
    }
}