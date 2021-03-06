package com.example.kev.revanotes;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class UploadActivity extends AppCompatActivity {

    Button btn_select, btn_upload, btn_display;
    TextView notification;
    EditText file_name, file_desc;
    RadioGroup rg_branch, rg_sem, rg_sub;
    RadioButton branch1, branch2, branch3, branch4, branch5, branch6, branch7, branch8, branch9, branch10;
    RadioButton sem1, sem2, sem3, sem4, sem5, sem6, sem7, sem8, sem9, sem10;
    RadioButton subject1, subject2, subject3, subject4, subject5, subject6, subject7, subject8;


    Uri pdfUri;

    FirebaseStorage storage;
    FirebaseDatabase database;
    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;
    DatabaseReference file_names, file_description;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //storage= FirebaseStorage.getInstance();
        // database= FirebaseDatabase.getInstance(Constants.DATABASE_PATH_UPLOADS);

        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
        //database= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        btn_select = findViewById(R.id.button_upload);
        btn_upload = findViewById(R.id.button_display);
        btn_display = findViewById(R.id.button_fetch_files);
        notification = findViewById(R.id.notification);
        file_name = findViewById(R.id.enter_filename);
        file_desc = findViewById(R.id.enter_filedesc);

        rg_branch = findViewById(R.id.radiogp_branch);
        rg_sem = findViewById(R.id.radiogp_sem);
        rg_sub = findViewById(R.id.radiogp_sub);

        branch1 = findViewById(R.id.branch1);
        branch2 = findViewById(R.id.branch2);
        branch3 = findViewById(R.id.branch3);
        branch4 = findViewById(R.id.branch4);
        branch5 = findViewById(R.id.branch5);
        branch6 = findViewById(R.id.branch6);
        branch7 = findViewById(R.id.branch7);
        branch8 = findViewById(R.id.branch8);
        //branch9 = findViewById(R.id.branch9);
        // branch10 = findViewById(R.id.branch10);

        sem1 = findViewById(R.id.sem1);
        sem2 = findViewById(R.id.sem2);
        sem3 = findViewById(R.id.sem3);
        sem4 = findViewById(R.id.sem4);
        sem5 = findViewById(R.id.sem5);
        sem6 = findViewById(R.id.sem6);
        sem7 = findViewById(R.id.sem7);
        sem8 = findViewById(R.id.sem8);
        sem9 = findViewById(R.id.sem9);
        sem10 = findViewById(R.id.sem10);

        subject1 = findViewById(R.id.subject1);
        subject2 = findViewById(R.id.subject2);
        subject3 = findViewById(R.id.subject3);
        subject4 = findViewById(R.id.subject4);
        subject5 = findViewById(R.id.subject5);
        subject6 = findViewById(R.id.subject6);
        subject7 = findViewById(R.id.subject7);
        subject8 = findViewById(R.id.subject8);

        branch1.setText(R.string.CS);
        branch2.setText(R.string.Mech);
        branch3.setText(R.string.EE);
        branch4.setText(R.string.EC);
        branch5.setText(R.string.Civil);
        branch6.setText(R.string.Arch);
        branch7.setText(R.string.BBA);
        branch8.setText(R.string.BCOM);

        branch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem1.setText(R.string.First);
                sem2.setText(R.string.Second);
                sem3.setText(R.string.Third);
                sem4.setText(R.string.Fourth);
                sem5.setText(R.string.Fifth);
                sem6.setText(R.string.Sixth);
                sem7.setText(R.string.Seventh);
                sem8.setText(R.string.Eighth);
                sem1.setVisibility(View.VISIBLE);
                sem2.setVisibility(View.VISIBLE);
                sem3.setVisibility(View.VISIBLE);
                sem4.setVisibility(View.VISIBLE);
                sem5.setVisibility(View.VISIBLE);
                sem6.setVisibility(View.VISIBLE);
                sem7.setVisibility(View.VISIBLE);
                sem8.setVisibility(View.VISIBLE);
            }
        });
        //int selectedBranchId=rg_branch.getCheckedRadioButtonId();
        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectBranch = findViewById(rg_branch.getCheckedRadioButtonId());
                String BranchChosen = selectBranch.getText().toString();
                notification.setText(BranchChosen);
                if (BranchChosen.equals("Computer Science Engineering") || BranchChosen.equals("Mechanical Engineering") || BranchChosen.equals("Electrical and Electronics Engineering") || BranchChosen.equals("Electronics and Communication Engineering") || BranchChosen.equals("Civil Engineering")) {
                    subject1.setText(R.string.CS_1_Sub1);
                    subject2.setText(R.string.CS_1_Sub2);
                    subject3.setText(R.string.CS_1_Sub3);
                    subject4.setText(R.string.CS_1_Sub4);
                    subject5.setText(R.string.CS_1_Sub5);
                    subject6.setText(R.string.CS_1_Sub6);
                    subject1.setVisibility(View.VISIBLE);
                    subject2.setVisibility(View.VISIBLE);
                    subject3.setVisibility(View.VISIBLE);
                    subject4.setVisibility(View.VISIBLE);
                    subject5.setVisibility(View.VISIBLE);
                    subject6.setVisibility(View.VISIBLE);
                }
            }
        });
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectBranch = findViewById(rg_branch.getCheckedRadioButtonId());
                String BranchChosen = selectBranch.getText().toString();
                if (BranchChosen.equals("Computer Science Engineering") || BranchChosen.equals("Mechanical Engineering") || BranchChosen.equals("Electrical and Electronics Engineering") || BranchChosen.equals("Electronics and Communication Engineering") || BranchChosen.equals("Civil Engineering")) {
                    subject1.setText(R.string.CS_2_Sub1);
                    subject2.setText(R.string.CS_2_Sub2);
                    subject3.setText(R.string.CS_2_Sub3);
                    subject4.setText(R.string.CS_2_Sub4);
                    subject5.setText(R.string.CS_2_Sub5);
                    subject1.setVisibility(View.VISIBLE);
                    subject2.setVisibility(View.VISIBLE);
                    subject3.setVisibility(View.VISIBLE);
                    subject4.setVisibility(View.VISIBLE);
                    subject5.setVisibility(View.VISIBLE);
                }
            }
        });
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectBranch = findViewById(rg_branch.getCheckedRadioButtonId());
                String BranchChosen = selectBranch.getText().toString();
                if (BranchChosen.equals("Computer Science Engineering")) {
                    subject1.setText(R.string.CS_3_Sub1);
                    subject2.setText(R.string.CS_3_Sub2);
                    subject3.setText(R.string.CS_3_Sub3);
                    subject4.setText(R.string.CS_3_Sub4);
                    subject5.setText(R.string.CS_3_Sub5);
                    subject1.setVisibility(View.VISIBLE);
                    subject2.setVisibility(View.VISIBLE);
                    subject3.setVisibility(View.VISIBLE);
                    subject4.setVisibility(View.VISIBLE);
                    subject5.setVisibility(View.VISIBLE);

                } else if (BranchChosen.equals("Mechanical Engineering")) {
                    subject1.setText(R.string.Mech_1_Sub1);

                }
            }
        });
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectBranch = findViewById(rg_branch.getCheckedRadioButtonId());
                String BranchChosen = selectBranch.getText().toString();
                if (BranchChosen.equals("Computer Science Engineering")) {
                    subject1.setText(R.string.CS_4_Sub1);
                    subject2.setText(R.string.CS_4_Sub2);
                    subject3.setText(R.string.CS_4_Sub3);
                    subject4.setText(R.string.CS_4_Sub4);
                    subject5.setText(R.string.CS_4_Sub5);
                    subject1.setVisibility(View.VISIBLE);
                    subject2.setVisibility(View.VISIBLE);
                    subject3.setVisibility(View.VISIBLE);
                    subject4.setVisibility(View.VISIBLE);
                    subject5.setVisibility(View.VISIBLE);
                }
            }
        });
        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectBranch = findViewById(rg_branch.getCheckedRadioButtonId());
                String BranchChosen = selectBranch.getText().toString();
                if (BranchChosen.equals("Computer Science Engineering")) {
                    subject1.setText(R.string.CS_5_Sub1);
                    subject2.setText(R.string.CS_5_Sub2);
                    subject3.setText(R.string.CS_5_Sub3);
                    subject4.setText(R.string.CS_5_Sub4);
                    subject5.setText(R.string.CS_5_Sub5);
                    subject1.setVisibility(View.VISIBLE);
                    subject2.setVisibility(View.VISIBLE);
                    subject3.setVisibility(View.VISIBLE);
                    subject4.setVisibility(View.VISIBLE);
                    subject5.setVisibility(View.VISIBLE);
                }
            }
        });
        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectBranch = findViewById(rg_branch.getCheckedRadioButtonId());
                String BranchChosen = selectBranch.getText().toString();
                if (BranchChosen.equals("Computer Science Engineering")) {
                    subject1.setText(R.string.CS_6_Sub1);
                    subject2.setText(R.string.CS_6_Sub2);
                    subject3.setText(R.string.CS_6_Sub3);
                    subject4.setText(R.string.CS_6_Sub4);
                    subject5.setText(R.string.CS_6_Sub5);
                    subject1.setVisibility(View.VISIBLE);
                    subject2.setVisibility(View.VISIBLE);
                    subject3.setVisibility(View.VISIBLE);
                    subject4.setVisibility(View.VISIBLE);
                    subject5.setVisibility(View.VISIBLE);
                }
            }
        });
        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectBranch = findViewById(rg_branch.getCheckedRadioButtonId());
                String BranchChosen = selectBranch.getText().toString();
                if (BranchChosen.equals("Computer Science Engineering")) {
                    subject1.setText(R.string.CS_7_Sub1);
                    subject2.setText(R.string.CS_7_Sub2);
                    subject3.setText(R.string.CS_7_Sub3);
                    subject4.setText(R.string.CS_7_Sub4);
                    subject5.setText(R.string.CS_7_Sub5);
                    subject1.setVisibility(View.VISIBLE);
                    subject2.setVisibility(View.VISIBLE);
                    subject3.setVisibility(View.VISIBLE);
                    subject4.setVisibility(View.VISIBLE);
                    subject5.setVisibility(View.VISIBLE);
                }
            }
        });
        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectBranch = findViewById(rg_branch.getCheckedRadioButtonId());
                String BranchChosen = selectBranch.getText().toString();
                if (BranchChosen.equals("Computer Science Engineering")) {
                    subject1.setText(R.string.CS_8_Sub1);
                    subject2.setText(R.string.CS_8_Sub2);
                    subject3.setText(R.string.CS_8_Sub3);
                    subject4.setText(R.string.CS_8_Sub4);
                    subject5.setText(R.string.CS_8_Sub5);
                    subject1.setVisibility(View.VISIBLE);
                    subject2.setVisibility(View.VISIBLE);
                    subject3.setVisibility(View.VISIBLE);
                    subject4.setVisibility(View.VISIBLE);
                    subject5.setVisibility(View.VISIBLE);
                }
            }
        });


        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(UploadActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    selectPdf();
                } else
                    ActivityCompat.requestPermissions(UploadActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pdfUri != null) {
                    //String filen=file_name.getText().toString();
                    //String filed=file_desc.getText().toString();

                    uploadFile(pdfUri);//uploadFile(pdfUri,filen,filed);
                } else
                    Toast.makeText(UploadActivity.this, "please select a file", Toast.LENGTH_SHORT).show();
            }
        });

        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadActivity.this, DisplayActivity.class));
            }
        });


    }

    private void uploadFile(final Uri pdfUri)//,String filen,String filed
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file....");
        progressDialog.setProgress(0);
        progressDialog.show();

        StorageReference sRef = mStorageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + ".pdf");

        sRef.putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful()) ;
                        Uri downloadUrl = urlTask.getResult();

                        String url = downloadUrl.toString();
                        progressDialog.dismiss();
                        ///reference.child(reference.push().getKey()).setValue(uploads);
                        Toast.makeText(UploadActivity.this, "FILE SUCCESSFULLY UPLOADED", Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.GONE);
                        //textViewStatus.setText("File Uploaded Successfully");
                        //final String dbfilen=
                        final String filen = file_name.getText().toString();
                        final String filed = file_desc.getText().toString();
                        RadioButton selectBranch = findViewById(rg_branch.getCheckedRadioButtonId());
                        String branchChosen = selectBranch.getText().toString();
                        RadioButton selectSem = findViewById(rg_sem.getCheckedRadioButtonId());
                        String semChosen = selectSem.getText().toString();
                        RadioButton selectSub = findViewById(rg_sub.getCheckedRadioButtonId());
                        String subChosen = selectSub.getText().toString();


                        Uploads upload = new Uploads(filen, filed, url, branchChosen, semChosen, subChosen);
                        mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(UploadActivity.this, "FILE NOT UPLOADED", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        int currentProgress = (int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setProgress(currentProgress);
                        //double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        //textViewStatus.setText((int) progress + "% Uploading...");
                    }
                });

        final String filename = System.currentTimeMillis() + ".pdf";
        final String filename1 = System.currentTimeMillis() + "";
        //StorageReference storageReference=storage.getReference();
        //StorageReference storageReference=storage.getReference();

        //New Stuff
        //file_names = FirebaseDatabase.getInstance().getReference().child("name");
        //file_description = FirebaseDatabase.getInstance().getReference().child("description");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf();

        } else
            Toast.makeText(UploadActivity.this, "please provide permission.....", Toast.LENGTH_SHORT).show();
    }

    private void selectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            notification.setText("A file is selected: " + data.getData().getLastPathSegment());
        } else
            Toast.makeText(UploadActivity.this, "please select a file", Toast.LENGTH_SHORT).show();
    }


}