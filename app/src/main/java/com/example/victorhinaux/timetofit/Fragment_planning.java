package com.example.victorhinaux.timetofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class Fragment_planning extends Fragment {

    private String array_courses[];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        return inflater.inflate(R.layout.fragment_planning, container, false);

    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_planning);
        array_courses=new String[5];
        array_courses[0]="option 1";
        array_courses[1]="option 2";
        array_courses[2]="option 3";
        array_courses[3]="option 4";
        array_courses[4]="option 5";
        Spinner s = (Spinner) findViewById(R.id.Spinner_courses);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_courses);
        s.setAdapter(adapter);
    }*/
}