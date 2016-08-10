package com.rsu.nuttanun.testdrivingvlicense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by MooTuM-NB on 10/8/2559.
 */
public class LabelAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] iconStrings, labelStrings, contentStrings;

    public LabelAdapter(Context context
            , String[] iconStrings,
                        String[] labelStrings,
                        String[] contentStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.labelStrings = labelStrings;
        this.contentStrings = contentStrings;
    }

    @Override
    public int getCount() {
        return iconStrings.length;
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
        View view = layoutInflater.inflate(R.layout.label_listview, parent, false);

        // BindWidget

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView9);
        TextView labelTextView = (TextView) view.findViewById(R.id.textView23);
        TextView contentTextView = (TextView) view.findViewById(R.id.textView24);

        Picasso.with(context).load(iconStrings[position]).resize(120, 120).into(imageView);
        labelTextView.setText(labelStrings[position]);

        String contentShort = contentStrings[position].substring(0, 4) + "..."; // ค่อยมาแก้ เป็น 20 ที่หลัง
        contentTextView.setText(contentShort);



        return view;
    }
}   //Main class
