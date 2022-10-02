package com.example.firebasebasedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebasebasedapplication.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding binding1;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding1= ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding1.getRoot());

        auth = FirebaseAuth.getInstance();



        binding1.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1=binding1.edtEmailReg.getText().toString();
                String password1=binding1.edtPasswordReg.getText().toString();
                if (TextUtils.isEmpty(email1)|| TextUtils.isEmpty((password1))){
                    Toast.makeText(Register.this, "Please Enter both email and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    regis(email1,password1);
                }

            }
        });
    }
    //onCompleteListener
    private void regis(String email, String password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("TAG", "createUserWithEmail:success");
                    Toast.makeText(Register.this, "successful sign up", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(Register.this,UserDataInput.class);
                    startActivity(intent);
                    FirebaseUser user = auth.getCurrentUser();
                    updateUI(user);

                }
                else{
                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Register.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);

                }
            }
        });
    }
    private void reload() { finish();}

    private void updateUI(FirebaseUser user) {

    }
}