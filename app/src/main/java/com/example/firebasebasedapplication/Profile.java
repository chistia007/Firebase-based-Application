package com.example.firebasebasedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.firebasebasedapplication.databinding.ActivityProfileBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    ActivityProfileBinding binding5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding5=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding5.getRoot());

       ArrayList<String> a=new ArrayList<>();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/fire blog/Users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()); {
                    a.clear();
                    //it will iterate till it got child
                    // Note: values in database and User java class all should be same
                   for(DataSnapshot snapshot1:snapshot.getChildren()){
                       Users i=snapshot1.getValue(Users.class); //Children value from snapshot
                       String t="First name: "+ i.getFirstName()+"\n\nLast name: "+i.getLastName()+"\n\nPhone number: "+i.getPhoneNumber();
                       a.add(t);
                   }

                    }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(Profile.this,R.layout.items,a);
                binding5.lstUSerProfile.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: "+ error.getCode());
            }
        });


        binding5.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,MainActivity.class));
            }
        });
    }
}