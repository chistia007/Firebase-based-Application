package com.example.firebasebasedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.firebasebasedapplication.databinding.ActivityUserDataInputBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserDataInput extends AppCompatActivity {
    ActivityUserDataInputBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserDataInputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = binding.edtFirstName.getText().toString();
                String lastName = binding.edtLastName.getText().toString();
                String phone = binding.edtNumber.getText().toString();
                if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(phone)) {

                    Toast.makeText(UserDataInput.this, "Please Enter both email and password", Toast.LENGTH_SHORT).show();
                } else {
                    add(firstName, lastName,phone);
                }
            }
        });
    }

    private void add(String firstName, String lastName, String phone) {
        HashMap<String, Object> m=new HashMap<String, Object>();
        m.put("firstName", firstName);
        m.put("lastName", lastName);
        m.put("phoneNumber", phone);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/fire blog");
        DatabaseReference usersRef = ref.child("Users");
        usersRef.push().setValue(m);
        Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(UserDataInput.this,Profile.class);
        startActivity(intent);

    }

}