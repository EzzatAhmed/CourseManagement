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

public class scnd_year extends AppCompatActivity {

    FirebaseStorage storage;
    FirebaseDatabase database;
    StorageReference storageReference,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_year);

        CardView first = findViewById(R.id.frst_term_2);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                download_1st_term_scnd();
            }
        });

        CardView second = findViewById(R.id.scnd_term_2);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_scnd_term_scnd();

            }
        });





    }


    private long downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {


        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        return downloadmanager.enqueue(request);
    }


    private  void download_1st_term_scnd()
    {
        storageReference=FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("...");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(scnd_year.this,"....",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed To download this file !",Toast.LENGTH_LONG).show();

            }
        });

    }
    private  void download_scnd_term_scnd()
    {
        storageReference=FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("...");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(scnd_year.this,"...",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed To download this file !",Toast.LENGTH_LONG).show();

            }
        });

    }


}
