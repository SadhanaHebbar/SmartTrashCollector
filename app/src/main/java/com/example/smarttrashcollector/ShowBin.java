package com.example.smarttrashcollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowBin extends AppCompatActivity {
    TextView a,b;
    Button refresh;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bin);
        a=(TextView)findViewById(R.id.tvconc);
        b=(TextView)findViewById(R.id.tvlevel);
        refresh=(Button)findViewById(R.id.btnrefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff= FirebaseDatabase.getInstance().getReference().child("NODE1");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String concentration=dataSnapshot.child("Concentration").getValue().toString();
                        String level=dataSnapshot.child("Level").getValue().toString();
                        a.setText(concentration);

                        b.setText(level);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
