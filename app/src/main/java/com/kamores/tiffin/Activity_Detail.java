package com.kamores.tiffin;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kamores.tiffin.Fragment.FragmentDay;
import com.kamores.tiffin.Fragment.FragmentWeek;

import java.util.ArrayList;

public class Activity_Detail extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    MenuView itemCall, itemSMS, itemShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail );

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

    }
}