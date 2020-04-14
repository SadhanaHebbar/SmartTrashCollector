package com.example.smarttrashcollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity {

    FirebaseAuth mAuth;

    private Button showBin;
    private Button report;
    private Button profile;
    private Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth=FirebaseAuth.getInstance();

        showBin = (Button) findViewById(R.id.button1);
        report = (Button) findViewById(R.id.button3);
        profile = (Button) findViewById(R.id.button2);
        map=(Button)findViewById(R.id.btmap);


        showBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_show_bin();
            }

            private void openactivity_show_bin() {
                Intent intent = new Intent(Home.this, ShowBin.class);
                startActivity(intent);

            }

        });


        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_report();
            }

            private void openactivity_report() {
                Intent intent = new Intent(Home.this, Report.class);
                startActivity(intent);
            }

        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_profile();
            }

            private void openactivity_profile() {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }

        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_maps();
            }

            private void openactivity_maps() {
                Intent intent = new Intent(Home.this, MapsActivity.class);
                startActivity(intent);
            }

        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.siM){
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent=new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}