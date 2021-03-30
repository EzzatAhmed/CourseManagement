package com.example.new_desgin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    TextView prof_name,prof_email,prof_num,prof_id;
    ImageView prof_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        prof_name=findViewById(R.id.prof_username);
        prof_email=findViewById(R.id.prof_email);
        prof_id=findViewById(R.id.prof_id);
        prof_num=findViewById(R.id.prof_num);
        prof_img=findViewById(R.id.prof_pic);




    }
}
