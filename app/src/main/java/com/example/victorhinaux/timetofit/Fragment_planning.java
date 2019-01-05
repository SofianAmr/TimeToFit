package com.example.victorhinaux.timetofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Fragment_planning extends Fragment {

    private CalendarView mcalendarView;
    private TextView mtextView;
    private ListView mListView;

    private Integer mYear;
    private Integer mMonth;
    private Integer mDay;



    private Trainer Jimmy = new Trainer("Jimmy", "jimmy@gmail.com", "0618291054");
    private Trainer Marina = new Trainer("Marina", "marina@gmail.com", "0793183475");

    private Training Fitness = new Training("Fitness", "Bring your stuff", 10, 2, 2019,"15:10 - 17:00", Jimmy);
    private Training Cardio = new Training("Cardio", "Try to resist", 10, 2, 2019,"17:10 - 19:00", Marina);
    private Training[] values = new Training[]{Fitness, Cardio};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_planning, container, false);

        super.onCreate(saveInstanceState);
        Database helper = new Database(v.getContext());
        mcalendarView = (CalendarView) v.findViewById(R.id.calendar);
        mtextView = (TextView) v.findViewById(R.id.date);
        mListView = (ListView) v.findViewById(R.id.listview);

        //helper.insertTrainer(Jimmy);



        final ArrayList<Training> list = new ArrayList<Training>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        mcalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + " - " + (month+1) + " - " + year;
                mtextView.setText(date);
                for (Training training:list
                        ) {
                    DisplayTraining(training, dayOfMonth, month, year);
                }
            }
        });

        return v;
    }

    public void DisplayTraining(Training training, int dayOfMonth, int month, int year) {
        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getContext(), values);
        if ((training.getDay() == dayOfMonth) && (training.getMonth() == month + 1) && (training.getYear() == year)) {
            mListView.setAdapter(adapter);
        } else {
            mListView.setAdapter(null);
        }
    }



}