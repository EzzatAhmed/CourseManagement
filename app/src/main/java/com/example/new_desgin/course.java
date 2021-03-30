package com.example.new_desgin;

import android.Manifest;

import android.app.DownloadManager;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class course extends AppCompatActivity {

    private LinearLayout download_file;
    FirebaseStorage storage;
    FirebaseDatabase database;


    Uri pdfUri;

    ProgressDialog progressDialog;

    StorageReference storageReference,ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);

        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();



        ImageView select_up = findViewById(R.id.select_file_up);
        select_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(course.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                     select_pdf();
                }
                else
                {
                    ActivityCompat.requestPermissions(course.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }

            }
        });


        TextView upload = findViewById(R.id.upload_btn);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfUri!=null)
                   uploadfile(pdfUri);
                else
                    Toast.makeText(course.this,"select a file",Toast.LENGTH_SHORT).show();

                //download
                download_file=(LinearLayout) findViewById(R.id.download_file);
                download_file.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        download();

                    }
                });



            }
        });


    }

    private void uploadfile(Uri pdfUri)
    {

        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file ...");
        progressDialog.setProgress(0);
        progressDialog.show();


        final String fileName=System.currentTimeMillis()+"";
        final StorageReference storageReference=storage.getReference();
        storageReference.child("Uploads").child(fileName).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url=storageReference.getDownloadUrl().toString();
                DatabaseReference reference=database.getReference();
                reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(course.this,"File Successfully uploaded",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(course.this,"File not successfully uploaded",Toast.LENGTH_SHORT).show();
                        }

                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress=(int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            select_pdf();
        }
        else
        {
            Toast.makeText(course.this,"Please Provide permission..",Toast.LENGTH_SHORT).show();
        }
    }



    public void select_pdf()
    {
        Intent intent=new Intent();
        intent.setType("file/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);  //fetch file
        startActivityForResult(intent,86);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null)
        {
            pdfUri=data.getData();


        }
        else
        {
            Toast.makeText(course.this,"Please select a file ",Toast.LENGTH_SHORT).show();
        }
    }


    //download
    public void download()
    {
        storageReference=FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("LBG.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                download_files(course.this,"LBG","pdf", DIRECTORY_DOWNLOADS,url);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
    public void download_files(Context context, String fileName, String fileExtension, String destinationDirectory, String url)
    {
        DownloadManager downloadManager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(url);
        DownloadManager.Request request=new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory,fileName+fileExtension);


        downloadManager.enqueue(request);


    }



}
