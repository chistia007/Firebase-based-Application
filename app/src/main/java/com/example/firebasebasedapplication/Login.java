package com.example.firebasebasedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebasebasedapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding2;
    FirebaseAuth auth;


    @Override
    protected void onStart() {
        super.onStart();
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            reload();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding2 = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());

        auth = FirebaseAuth.getInstance();



        binding2.btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = binding2.edtEmailLog.getText().toString();
                String password1 = binding2.edtPasswordLog.getText().toString();
                if (TextUtils.isEmpty(email1) || TextUtils.isEmpty((password1))) {

                    Toast.makeText(Login.this, "Please Enter both email and password", Toast.LENGTH_SHORT).show();
                } else {
                    Log_in(email1, password1);
                }
            }
        });

    }

    private void Log_in(String email1, String password1) {
        auth.signInWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent=new Intent(Login.this,Profile.class);
                            startActivity(intent);
                            Log.d("TAG", "signInWithEmail:success");
                            Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });


    }
    private void updateUI(FirebaseUser user) {

    }
    private void reload(){FirebaseAuth.getInstance().signOut();}
}
