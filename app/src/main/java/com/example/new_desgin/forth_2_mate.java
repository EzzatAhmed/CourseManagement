package com.example.new_desgin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class forth_2_mate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forth_2_mate);


        CardView comp=findViewById(R.id.compiler);
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compiler();
            }
        });
        CardView es=findViewById(R.id.es);
        es.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                es();
            }
        });


    }


    public void cv() {
        Intent cv = new Intent(this, computer_vision.class);
        startActivity(cv);
    }
    public void net() {
        Intent net = new Intent(this, network.class);
        startActivity(net);
    }
    public void gra() {
        Intent gra = new Intent(this, graghics.class);
        startActivity(gra);
    }
    public void compiler() {
        Intent compiler = new Intent(this, compiler.class);
        startActivity(compiler);
    }
    public void es() {
        Intent es = new Intent(this, expert_system.class);
        startActivity(es);
    }
}
