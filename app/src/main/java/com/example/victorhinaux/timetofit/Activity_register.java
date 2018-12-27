package com.example.victorhinaux.timetofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class Activity_register extends AppCompatActivity {

    private static final String TAG = "Register";
    Database helper = new Database(this);
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //to link to the login activity
       /* btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_register.this, MainActivity.class);
                startActivity(intent);
            }
        });
        */
    }
    public void onSignUpClick(View v){
        if(v.getId() == R.id.btn_register) {
            EditText firstName = (EditText) findViewById(R.id.ETfirstName);
            EditText lastName = (EditText) findViewById(R.id.ETlastName);
            EditText email = (EditText) findViewById(R.id.ETemail);
            EditText pass1 = (EditText) findViewById(R.id.ETpass1);
            EditText pass2 = (EditText) findViewById(R.id.ETpass2);

            String firstNamestr = firstName.getText().toString();
            String lastNamestr = lastName.getText().toString();
            String emailstr = email.getText().toString();
            Log.d(TAG, emailstr);
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();
            Log.d(TAG, "register test");

            if (!pass1str.equals(pass2str)) {
                //popup msg:
                Toast toastpass = Toast.makeText(Activity_register.this, "passwords don't match", Toast.LENGTH_LONG);
                toastpass.show();
            } else {
                //insert the details in DB:
                Contact c = new Contact();
                c.setFirstName(firstNamestr);
                c.setLastName(lastNamestr);
                c.setEmail(emailstr);
                Log.d(TAG, "emailstr:" + emailstr);

                c.setPass(pass1str);
                Log.d(TAG, "pass1str:" + pass1str);
                helper.insertContact(c);

                Intent intent = new Intent(Activity_register.this, MainActivity.class);
                startActivity(intent);
            }

        }

        }
}