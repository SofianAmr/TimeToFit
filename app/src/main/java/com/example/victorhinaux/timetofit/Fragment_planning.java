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

    Training Fitness = new Training("Fitness", "Bring your stuff", 10, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio = new Training("Cardio", "Try to resist", 15, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness2 = new Training("Fitness", "Bring your stuff", 9, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio2 = new Training("Cardio", "Try to resist", 9, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness3 = new Training("Fitness", "Bring your stuff", 10, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio3 = new Training("Cardio", "Try to resist", 10, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness4 = new Training("Fitness", "Bring your stuff", 11, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio4 = new Training("Cardio", "Try to resist", 12, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness5 = new Training("Fitness", "Bring your stuff", 13, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio5 = new Training("Cardio", "Try to resist", 14, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness6 = new Training("Fitness", "Bring your stuff", 15, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio6 = new Training("Cardio", "Try to resist", 16, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness7 = new Training("Fitness", "Bring your stuff", 17, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio7 = new Training("Cardio", "Try to resist", 18, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness8 = new Training("Fitness", "Bring your stuff", 19, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio8 = new Training("Cardio", "Try to resist", 20, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness9 = new Training("Fitness", "Bring your stuff", 21, 2, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio9 = new Training("Cardio", "Try to resist", 22, 2, 2019,"17:10 - 19:00", Marina);
    Training Fitness10 = new Training("Fitness", "Bring your stuff", 23, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio10 = new Training("Cardio", "Try to resist", 24, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness11 = new Training("Fitness", "Bring your stuff", 25, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio11 = new Training("Cardio", "Try to resist", 26, 1, 2019,"17:10 - 19:00", Marina);
    Training Fitness12 = new Training("Fitness", "Bring your stuff", 27, 1, 2019,"15:10 - 17:00", Jimmy);
    Training Cardio12 = new Training("Cardio", "Try to resist", 28, 1, 2019,"17:10 - 19:00", Marina);

    //final Training[] values = new Training[]{Fitness, Cardio,Fitness2, Cardio2,Fitness3, Cardio3,Fitness4, Cardio4,Fitness5, Cardio5,Fitness6, Cardio6,Fitness7, Cardio7,Fitness8, Cardio8,Fitness9, Cardio9,Fitness10, Cardio10,Fitness11, Cardio11,Fitness12, Cardio12};







   // private Training[] values = new Training[]{Fitness, Cardio,Fitness2, Cardio2,Fitness3, Cardio3,Fitness4, Cardio4,Fitness5, Cardio5,Fitness6, Cardio6,Fitness7, Cardio7,Fitness8, Cardio8,Fitness9, Cardio9,Fitness10, Cardio10,Fitness11, Cardio11,Fitness12, Cardio12};
   Training[] values;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_planning, container, false);


        super.onCreate(saveInstanceState);
        //values = new Training[]{Fitness,Cardio};
        values = new Training[]{Fitness, Cardio,Fitness2, Cardio2,Fitness3, Cardio3,Fitness4, Cardio4,Fitness5, Cardio5,Fitness6, Cardio6,Fitness7, Cardio7,Fitness8, Cardio8,Fitness9, Cardio9,Fitness10, Cardio10,Fitness11, Cardio11,Fitness12, Cardio12};

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
        Training[] trainings = new Training[]{training};
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getContext(), trainings);
        if ((training.getDay() == dayOfMonth) && (training.getMonth() == month + 1) && (training.getYear() == year)) {
            mListView.setAdapter(adapter);
        }
    }



}