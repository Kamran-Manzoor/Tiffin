package com.kamores.tiffin.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kamores.tiffin.R;

public class FragmentDay extends Fragment {
    //    ImageView imageView;
    TextView to_name, to_item, to_Location;
    ListView list_View;
    View view;



    public FragmentDay() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.day_fragment, container, false);
//        imageView = view.findViewById(R.id.to_showimage);
        to_name = view.findViewById(R.id.to_showname);
        to_item = view.findViewById(R.id.to_servicec);
        to_Location = view.findViewById(R.id.to_showlocation);
        list_View = view.findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        list_View.setAdapter(customAdapter);
        Toast.makeText(getContext(), "Fragment", Toast.LENGTH_SHORT).show();

        return view;
    }
    class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint({"ViewHolder", "InflateParams"})
        @Override
        public View getView(int i, View views, ViewGroup viewGroup) {
            views = getLayoutInflater().inflate(R.layout.customlayout, null);

            TextView textView_name = views.findViewById(R.id.textView_name);
            TextView textView_description = views.findViewById(R.id.textView_description);
            String[] NAMES = {"Pakistan", "Australia", "England", "Russia", "USA", "Brazil", "China",
                    "Germany", "Italy", "NewZeland", "India"};
            String[] DESCRIPTION = {"22 Million", "40 Million", "70 Million", "75 Million", "29.32 Million",
                    "2 Billion", "2 Billion", "89 Million", "94 Million", "88 Million", "1 Billion"};

                textView_name.setText(NAMES[i]);
            textView_description.setText(DESCRIPTION[i]);

            Toast.makeText(getContext(), "all okay", Toast.LENGTH_SHORT).show();
            return views;
        }
    }

}



