package com.example.gobus;

import android.os.Bundle;
import android.text.TextUtils;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterFragment extends Fragment {

    EditText Name, Email , PhoneNumber , Password ;
    Button RegisterUserButton;
    DatabaseReference dbref;
    Users users;
    private void clearconsole(){
        Name.setText("");
        Email.setText("");
        PhoneNumber.setText("");
        Password.setText("");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.registration_fragment,container,false);

        Name = view.findViewById(R.id.register_username);
        Email = view.findViewById(R.id.register_email);
        PhoneNumber = view.findViewById(R.id.register_phone);
        Password = view.findViewById(R.id.register_password);

        RegisterUserButton = view.findViewById(R.id.buttonRegister);

        users = new Users();

        RegisterUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child("Users");
                try {
                    if (TextUtils.isEmpty(Name.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Name" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(Email.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Email" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(Password.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Password" , Toast.LENGTH_SHORT).show();
                    }else {

                        users.setName(Name.getText().toString().trim());
                        users.setEmail(Email.getText().toString().trim());
                        users.setPhoneNumber(Integer.parseInt(PhoneNumber.getText().toString().trim()));
                        users.setPassword(Password.getText().toString().trim());

                        dbref.push().setValue(users);

                        Toast.makeText(getContext(), "Data Saved Successfull", Toast.LENGTH_SHORT).show();
                        clearconsole();

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_cotainer , new LoginFragment());
                        ft.commit();

                    }

                }catch (NumberFormatException e){
                        Toast.makeText(getContext(), "Invalid Phone Number" , Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;
    }


}
