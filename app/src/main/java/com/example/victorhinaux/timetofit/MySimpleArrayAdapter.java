package com.example.victorhinaux.timetofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

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

        TextView trainingTV = (TextView) rowView.findViewById(R.id.label);
        TextView timelineTV = (TextView) rowView.findViewById(R.id.timeline);
        final TextView trainerTV = (TextView) rowView.findViewById(R.id.trainer);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        final Button registerBTN = (Button) rowView.findViewById(R.id.registerBTN);

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBTN.setEnabled(false);
                registerBTN.setText("Registered");
            }
        });

        trainerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Put information from this class from TrainerSheet
                Intent intent = new Intent(context, TrainerSheet.class);
                String trainerName = null;
                intent.putExtra("trainerTV",trainerTV.getText());
                context.startActivity(intent);
            }
        });

        // print the text
        trainingTV.setText(values[position].getName());
        timelineTV.setText(values[position].getTimeline());
        trainerTV.setText(values[position].getTrainer().getName());

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