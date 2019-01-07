package com.example.victorhinaux.timetofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private Button btn_gotoregister;
    private Button btn_connexion;
    private TextView text_time2fit;
    private LoginButton loginButton;
    private String txtEmail;
    private String txtBirthday;
    Database helper = new Database(this);
    CallbackManager callbackManager;
    ProgressDialog mDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



                callbackManager = CallbackManager.Factory.create();

        printKeyHash();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Retrieving data...");
                mDialog.show();

                String accesstoken = loginResult.getAccessToken().getToken();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        getData(object);
                    }
                });

                //Request Graph API
                Bundle parameters = new Bundle();
                parameters.putString("fields","id,email,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });



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

    private void getData(JSONObject object) {
        try{
            URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=120?height=120");


            txtEmail = object.getString("email");
            txtBirthday = object.getString("birthday");

            Intent intent = new Intent(this, Fragment_account.class);
            intent.putExtra("URL",profile_picture);
            intent.putExtra("fbEmail",txtEmail);
            intent.putExtra("fbBirthday",txtBirthday);
            this.startActivity(intent);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void printKeyHash(){
        try{
            PackageInfo info = getPackageManager().getPackageInfo("com.example.victorhinaux.timetofit", PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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



            String password = helper.searchPass(strEmail);
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
