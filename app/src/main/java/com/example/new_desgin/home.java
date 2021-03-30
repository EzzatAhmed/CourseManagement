package com.example.new_desgin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        CardView profile = findViewById(R.id.profileid_stu);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_acc();
            }
        });

        CardView event = findViewById(R.id.eventid_stu);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event_acc();
            }
        });

        CardView course = findViewById(R.id.courseid_stu);
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_acc();
            }
        });

        CardView logout = findViewById(R.id.logoutid_stu);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();

            }
        });

        CardView calender=findViewById(R.id.calendarid_stu);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_calender();
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

    private void course_acc() {
        Intent course_acc = new Intent(this, material_year.class);
        startActivity(course_acc);
    }

    private void signout()
    {
        FirebaseAuth.getInstance().signOut();
        Intent logout_acc=new Intent(this,login.class);
        startActivity(logout_acc);
    }

    private void open_calender()
    {
        Intent cal=new Intent(this,calender.class);
        startActivity(cal);
    }

}
