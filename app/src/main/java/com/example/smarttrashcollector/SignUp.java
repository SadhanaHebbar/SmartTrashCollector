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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText usNa,usPa,usEm,usPh;
    private Button regButton;
    private TextView usLo;
    private FirebaseAuth firebaseAuth;
    String email, name, password,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth= FirebaseAuth.getInstance();

        usNa=(EditText)findViewById(R.id.fuNa);
        usPa=(EditText)findViewById(R.id.edPa);
        usEm=(EditText)findViewById(R.id.edEm);
        usPh=(EditText)findViewById(R.id.edPh);
        regButton=(Button)findViewById(R.id.registerBtn);
        usLo=(TextView)findViewById(R.id.crTe);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validate()) {
                   String us_em= usEm.getText().toString().trim();
                   String us_pa=usPa.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(us_em,us_pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               sendUserData();
                               firebaseAuth.signOut();
                               Toast.makeText(SignUp.this,"Registration successful",Toast.LENGTH_SHORT).show();
                               finish();
                               startActivity(new Intent(SignUp.this, Login.class ));
                       }else{
                               Toast.makeText(SignUp.this,"Registration failed",Toast.LENGTH_SHORT).show();
                           }

                       }
                   });

               }
            }
        });
        usLo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class ));
            }
        });

    }
    private Boolean validate(){
        Boolean result=false;

        name= usNa.getText().toString();
        password=usPa.getText().toString();
        email=usEm.getText().toString();
        phone=usPh.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Please enter all the details",Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile= new UserProfile(name,email,phone);
        myRef.setValue(userProfile);
    }
}
