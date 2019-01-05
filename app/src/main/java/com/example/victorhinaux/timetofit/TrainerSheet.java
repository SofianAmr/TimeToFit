package com.example.victorhinaux.timetofit;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerSheet extends AppCompatActivity {

    Database helper = new Database(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_sheet);

        TextView nameTV = (TextView) findViewById(R.id.nameTrainer);
        TextView emailTV = (TextView) findViewById(R.id.emailTrainer);
        TextView numeroTV = (TextView) findViewById(R.id.numTrainer);


        String trainersName;

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            trainersName = (String) b.get("trainerTV");
            String trainerEmail = helper.trainerEmail(trainersName);
            String trainerNumero = helper.trainerNumero(trainersName);
            String trainerTrainings = helper.trainerTrainings(trainersName);
            nameTV.setText(trainersName);
            emailTV.setText(trainerEmail);
            numeroTV.setText(trainerNumero);
        }

        Button backBTN = (Button) findViewById(R.id.backBTN);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainerSheet.this, Activity_navigation.class);
                startActivity(intent);
            }
        });
    }
}
