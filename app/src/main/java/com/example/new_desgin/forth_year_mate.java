package com.example.new_desgin;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class forth_year_mate extends AppCompatActivity {
    FirebaseStorage storage;
    FirebaseDatabase database;
    StorageReference storageReference,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forth_year_mate);

        CardView first = findViewById(R.id.frst_term_4_mate);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_first_term_mate();


            }
        });

        CardView second = findViewById(R.id.scnd_term_4_mate);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_scnd_term_mate();

            }
        });





    }
    private void open_first_term_mate() {
        Intent first = new Intent(this, forth_1_mate.class);
        startActivity(first);
    }private void open_scnd_term_mate() {
        Intent first = new Intent(this, forth_2_mate.class);
        startActivity(first);
    }





}
