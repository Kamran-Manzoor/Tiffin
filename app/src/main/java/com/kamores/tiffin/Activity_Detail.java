package com.kamores.tiffin;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.view.menu.MenuView;

import com.kamores.tiffin.Fragment.FragmentDay;
import com.kamores.tiffin.Fragment.FragmentWeek;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.EXTRA_SUBJECT;
import static android.content.Intent.EXTRA_TEXT;
import static android.content.Intent.createChooser;

public class Activity_Detail extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    ImageView imageCall, imageSMS, imageShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_detail);

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

        imageCall= findViewById(R.id.image_call);
        imageSMS= findViewById(R.id.image_sms);
        imageShare= findViewById(R.id.image_share);


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

        // coed for Call Button
        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"));
                startActivity(intent);
            }
        });

        // coed for TextMessage Button
        imageSMS.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"WrongViewCast", "MissingPermission"})
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.fromParts("sms", "1234567890", null));
                intent.putExtra("sms-body", "Pakistan...");
                startActivity(intent);
            }
        });

        // code for share Button
        imageShare.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Your body here";
                String shareSub = "Your subject here";
                sharingIntent.putExtra(EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(EXTRA_TEXT, shareBody);
                startActivity(createChooser(sharingIntent, "Share using"));
            }
        });
    }
}