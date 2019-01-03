package com.example.victorhinaux.timetofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<Training> {
    private final Context context;
    private final Training[] values;

    public MySimpleArrayAdapter(Context context, Training[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position].getName());
        // change the icon for Windows and iPhone
        String s = values[position].getName();
        if (s.startsWith("Fitness")) {
            imageView.setImageResource(R.drawable.fitness);
        } if (s.startsWith("Cardio")) {
            imageView.setImageResource(R.drawable.cardio);
        }

        return rowView;
    }
}