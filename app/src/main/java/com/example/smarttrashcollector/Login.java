package com.example.smarttrashcollector;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private TextView forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Password=(EditText)findViewById(R.id.loPa);
        Email=(EditText)findViewById(R.id.loEm);
        Login=(Button)findViewById(R.id.loBt);
        userRegistration=(TextView)findViewById(R.id.cAcc);
        forgotPass=(TextView)findViewById(R.id.forg);

        firebaseAuth=FirebaseAuth.getInstance();

        FirebaseUser user=firebaseAuth.getCurrentUser();



        if(user != null){
            finish();
            startActivity(new Intent(com.example.smarttrashcollector.Login.this, Home.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(),Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.smarttrashcollector.Login.this, SignUp.class));
            }
        });


        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(com.example.smarttrashcollector.Login.this, FoPa.class));

            }
        });


    }

    private void validate(String userEmail, String userPassword){

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(com.example.smarttrashcollector.Login.this, "Logged In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(com.example.smarttrashcollector.Login.this, Home.class));
                    finish();

                }else{
                    Toast.makeText(com.example.smarttrashcollector.Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
