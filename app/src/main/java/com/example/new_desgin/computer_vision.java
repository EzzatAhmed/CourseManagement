package com.example.new_desgin;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class computer_vision extends AppCompatActivity {


    FirebaseStorage storage;
    FirebaseDatabase database;
    StorageReference storageReference,ref;


    Uri pdfUri;

    ProgressDialog progressDialog;

    CardView lec1,lec2,lec3,lec4,lec5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_vision);

        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();



        ImageView select_up = findViewById(R.id.select_file_up_cv);
        select_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(computer_vision.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    select_pdf();
                }
                else
                {
                    ActivityCompat.requestPermissions(computer_vision.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }

            }
        });


        TextView upload = findViewById(R.id.upload_btn_cv);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfUri!=null)
                    uploadfile(pdfUri);
                else
                    Toast.makeText(computer_vision.this,"select a file",Toast.LENGTH_SHORT).show();
            }
        });

        lec1=findViewById(R.id.Lec1_cv);
        lec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_lec1();
            }
        });

        lec2=findViewById(R.id.Lec2_cv);
        lec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_lec2();
            }
        });


        lec3=findViewById(R.id.Lec3_cv);
        lec3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_lec3();
            }
        });


        lec4=findViewById(R.id.Lec4_cv);
        lec4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_lec4();
            }
        });


        lec5=findViewById(R.id.Lec5_cv);
        lec5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_lec5();
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

                            Toast.makeText(computer_vision.this,"File Successfully uploaded",Toast.LENGTH_SHORT).show();

                    }

                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(computer_vision.this,"File not successfully uploaded",Toast.LENGTH_SHORT).show();


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
            Toast.makeText(computer_vision.this,"Please Provide permission..",Toast.LENGTH_SHORT).show();
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
            Toast.makeText(computer_vision.this,"Please select a file ",Toast.LENGTH_SHORT).show();
        }
    }

    //download

    private long downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {


        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        return downloadmanager.enqueue(request);
    }

    private  void download_lec1()
    {
        storageReference=FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("CV,lec.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(computer_vision.this,"CV,lec",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed To download this file !",Toast.LENGTH_LONG).show();
            }
        });

    }

    private  void download_lec2()
    {
        storageReference=FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("After page 41 in CV_Before_Midterm.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(computer_vision.this," After page 41 in CV_Before_Midterm",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed To download this file !",Toast.LENGTH_LONG).show();
            }
        });

    }

    private  void download_lec3()
    {
        storageReference=FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("Example on Feature Extraction.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(computer_vision.this,"Example on Feature Extraction",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed To download this file !",Toast.LENGTH_LONG).show();
            }
        });

    }


    private  void download_lec4()
    {
        storageReference=FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("CV_Before_Midterm.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(computer_vision.this,"CV_Before_Midterm",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed To download this file !",Toast.LENGTH_LONG).show();
            }
        });

    }

    private  void download_lec5()
    {
        storageReference=FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("CV_after_midterm.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(computer_vision.this,"CV_after_midterm",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed To download this file !",Toast.LENGTH_LONG).show();
            }
        });

    }


}



