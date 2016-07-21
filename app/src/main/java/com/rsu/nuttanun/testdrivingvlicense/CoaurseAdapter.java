package com.rsu.nuttanun.testdrivingvlicense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by MooTuM-NB on 21/7/2559.
 */
public class CoaurseAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] titleStrings, detailStrings;

    public CoaurseAdapter(Context context,
                          String[] titleStrings,
                          String[] detailStrings) {
        this.context = context;
        this.titleStrings = titleStrings;
        this.detailStrings = detailStrings;
    }

    @Override
    public int getCount() {
        return titleStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.coaurse_layout, parent, false);

        //intial widget
        TextView titleTextView = (TextView) view.findViewById(R.id.textView13);
        TextView detailTextView = (TextView) view.findViewById(R.id.textView14);

        titleTextView.setText(titleStrings[position]);
        String shortdetail = detailStrings[position].substring(0, 20) + "...";
        detailTextView.setText(shortdetail);



        return view;
    }
}   //main class
