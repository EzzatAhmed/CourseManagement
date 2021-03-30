package com.example.new_desgin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class material_year extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_year);

        CardView first = findViewById(R.id.frst_year_mate);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_first_year();
            }
        });

        CardView second = findViewById(R.id.scnd_year_mate);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_scnd_year();
            }
        });

        CardView third = findViewById(R.id.third_year_mate);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_third_year();
            }
        });

        CardView forth = findViewById(R.id.forth_year_mate);
        forth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_forth_year();
            }
        });



    }


    public void open_first_year() {
        Intent first = new Intent(this, first_year_mate.class);
        startActivity(first);
    }
    public void open_scnd_year() {
        Intent scnd = new Intent(this, scnd_year_mate.class);
        startActivity(scnd);
    }
    public void open_third_year() {
        Intent third = new Intent(this, third_year_mate.class);
        startActivity(third);
    }
    public void open_forth_year() {
        Intent forth = new Intent(this, forth_year_mate.class);
        startActivity(forth);
    }

}
