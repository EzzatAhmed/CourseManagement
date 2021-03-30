package com.example.new_desgin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class calender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);

        CardView first = findViewById(R.id.frst_year);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_first_year();
            }
        });

        CardView second = findViewById(R.id.scnd_year);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_scnd_year();
            }
        });

        CardView third = findViewById(R.id.third_year);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_third_year();
            }
        });

        CardView forth = findViewById(R.id.forth_year);
        forth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_forth_year();
            }
        });



    }


    public void open_first_year() {
        Intent first = new Intent(this, first_year.class);
        startActivity(first);
    }
    public void open_scnd_year() {
        Intent scnd = new Intent(this, scnd_year.class);
        startActivity(scnd);
    }
    public void open_third_year() {
        Intent third = new Intent(this, third_year.class);
        startActivity(third);
    }
    public void open_forth_year() {
        Intent forth = new Intent(this, forth_year.class);
        startActivity(forth);
    }

}
