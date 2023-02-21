package com.example.gobus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LoginFragment extends Fragment {

    Button btnsingup , btnlogin;
    EditText username , password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment,container,false);

         btnsingup= view.findViewById(R.id.buttonsingup);
         btnlogin =view.findViewById(R.id.buttonlogin);
         username = view.findViewById(R.id.login_username);
         password = view.findViewById(R.id.login_password);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") & password.getText().toString().equals("12345")){
                    Intent intent = new Intent(getActivity(), AdminPannel.class);
                    startActivity(intent);
                }else {

                }

            }
        });

        btnsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_cotainer , new RegisterFragment());
                ft.commit();
            }
        });


        return view;

    }


}
