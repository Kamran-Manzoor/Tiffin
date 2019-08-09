package com.kamores.tiffin.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamores.tiffin.adapters.AdapterClass_For_Week;
import com.kamores.tiffin.R;

import java.util.ArrayList;

public class FragmentWeek extends Fragment {
    private ArrayList<String> mNames = new ArrayList<>();
    View view;
    RecyclerView recyclerViewWeekly;
    Context context;

    public FragmentWeek() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.week_fragment,container,false);
        initDayName();

        recyclerViewWeekly  = view.findViewById(R.id.recyclerView_Weekly);
        AdapterClass_For_Week adapter = new AdapterClass_For_Week(getContext(), mNames);
        recyclerViewWeekly.setAdapter(adapter);
        recyclerViewWeekly.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewWeekly.setAdapter(adapter);
        return view;
    }

    private void initDayName(){
        mNames.add("Monday");
        mNames.add("Tuesday");
        mNames.add("Wednesday");
        mNames.add("Thursday");
        mNames.add("Friday");
        mNames.add("Saturday");
        mNames.add("Sunday");
    }
}