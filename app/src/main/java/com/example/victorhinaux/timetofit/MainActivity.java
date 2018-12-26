package com.example.victorhinaux.timetofit;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_gotoregister;
    private Button btn_connexion;
    private TextView text_time2fit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // GO TO REGISTRATION
        btn_gotoregister = (Button)findViewById(R.id.btn_gotoregister);
        btn_gotoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_register.class);
                startActivity(intent);
            }
        });

        // GO TO HOME
        btn_connexion = (Button)findViewById(R.id.btn_connexion);
        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_navigation.class);
                startActivity(intent);
            }
        });

        text_time2fit = (TextView) findViewById(R.id.text_time2fit);
        setFont(text_time2fit,"wbv5straight.ttf");
    }

    public void setFont(TextView textView, String fontName) {
        if(fontName != null){
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/" + fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }


}
