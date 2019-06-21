package com.kamores.tiffin.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kamores.tiffin.R;

public class FragmentDay extends Fragment {
//    ImageView imageView;
    TextView to_name,to_item,to_Location;
    ListView list_View;
    View view;
    public FragmentDay() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.day_fragment,container,false);
//        imageView = view.findViewById(R.id.to_showimage);
        to_name = view.findViewById(R.id.to_showname);
        to_item = view.findViewById(R.id.to_showitem);
        to_Location = view.findViewById(R.id.to_showlocation);
        list_View = view.findViewById(R.id.listView);






        return view;

    }

}