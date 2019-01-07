package com.example.victorhinaux.timetofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.security.NoSuchAlgorithmException;


public class Fragment_account extends Fragment {


    private ImageView imgAvatar;
    private TextView txtEmail;
    private TextView txtBirthday;
    private TextView txtFirstName;
    private TextView txtLastName;
    Database helper = new Database(getContext());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        imgAvatar = (ImageView) v.findViewById(R.id.avatar);
        txtEmail = (TextView) v.findViewById(R.id.txtEmail);
        txtBirthday = (TextView) v.findViewById(R.id.txtBirthday);

        Intent i = getActivity().getIntent();
        Bundle b = i.getExtras();

        final String profile_picture;

        if(b!=null)
        {
            try{
                profile_picture = (String) b.get("URL");
                Picasso.with(getContext()).load(profile_picture.toString()).into(imgAvatar);
                txtEmail.setText((String) b.get("fbEmail"));
                txtBirthday.setText((String) b.get("fbBirthday"));
            }
            catch(Exception e )
            {
                System.out.println("Exception occurred");
            }

            try{
                txtFirstName.setText((String) b.get("contactFN"));
                txtLastName.setText((String) b.get("contactLN"));
                txtEmail.setText((String) b.get("contactE"));
            }
            catch(Exception e )
            {
                System.out.println("Exception occurred");
            }



        }


        return v;
    }
}