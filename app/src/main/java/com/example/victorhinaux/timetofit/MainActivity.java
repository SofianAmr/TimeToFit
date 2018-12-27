package com.example.victorhinaux.timetofit;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private Button btn_gotoregister;
    private Button btn_connexion;
    private TextView text_time2fit;
    Database helper = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // GO TO REGISTRATION
        btn_gotoregister = (Button) findViewById(R.id.btn_gotoregister);
        btn_gotoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_register.class);
                startActivity(intent);
            }
        });
    }

        /*// GO TO HOME
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
    }*/

    public void onSigninClick(View v){
        if(v.getId() == R.id.btn_connexion)
        {
            EditText a = (EditText)findViewById(R.id.ETusername);
            String strEmail = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.ETpassword);
            String pass = b.getText().toString();

            Log.d(TAG, pass);


            String password = helper.searchPass(strEmail);
            Log.d(TAG, password);
            if(pass.equals(password))
            {
                Intent i = new Intent(MainActivity.this,Activity_navigation.class);
                i.putExtra("name" , strEmail);
                startActivity(i);
            }
            else{//popup msg:
                Toast temp = Toast.makeText(MainActivity.this, "username & passwords don't match", Toast.LENGTH_LONG);
                temp.show();}
        }

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
