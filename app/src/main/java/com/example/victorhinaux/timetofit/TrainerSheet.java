package com.example.victorhinaux.timetofit;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        final TextView nameTV = (TextView) findViewById(R.id.nameTrainer);
        TextView emailTV = (TextView) findViewById(R.id.emailTrainer);
        final TextView numeroTV = (TextView) findViewById(R.id.numTrainer);


        final String trainersName;

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            trainersName = (String) b.get("trainerTV");
            String trainerEmail = helper.trainerEmail(trainersName);
            String trainerNumero = helper.trainerNumero(trainersName);
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

        numeroTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS((String) numeroTV.getText(), (String) nameTV.getText());
            }
        });
    }

    protected void sendSMS(String numero, String name) {
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , numero );
        smsIntent.putExtra("sms_body"  , "Hello "+name+",");
        startActivity(smsIntent);
        finish();

        /*try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(TrainerSheet.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }*/
    }
}
