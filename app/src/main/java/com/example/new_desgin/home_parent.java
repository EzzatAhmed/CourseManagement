package com.example.new_desgin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class home_parent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_parent);

        CardView profile = findViewById(R.id.profileid_pare);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_acc();
            }
        });

        CardView event = findViewById(R.id.eventid_pare);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event_acc();
            }
        });

        CardView logout = findViewById(R.id.logoutid_pare);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();

            }
        });
    }


    private void profile_acc() {
        Intent profile_acc = new Intent(this, profile.class);
        startActivity(profile_acc);
    }

    private void event_acc() {
        Intent event_acc = new Intent(this, event.class);
        startActivity(event_acc);
    }


    private void signout()
    {
        FirebaseAuth.getInstance().signOut();
        Intent logout_acc=new Intent(this,login.class);
        startActivity(logout_acc);
    }
}
