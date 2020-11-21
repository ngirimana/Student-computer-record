package com.example.sqllitecrudeoperation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class StudentRecord extends AppCompatActivity {
    private ImageView studentImg;
    private EditText sName,sRegNumber,sPcSerialNumber,sPhone;
    Button saveInfoBtn;
    ActionBar actionBar;

    private static final int CAMERA_REQUEST_CODE=100;
    private static final int STORAGE_REQUEST_CODE=101;
    private static final int IMAGE_PICK_CAMERA_CODE=102;
    private static final int IMAGE_PICK_GALLERY_CODE=103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;
    private String name,regNumber,serialNumber,phone,timeStamp;
    private DatabaseConnector dbHelper;

    public StudentRecord() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_record);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Student Info");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        studentImg =findViewById(R.id.studentImage);
        sName= findViewById(R.id.studentName);
        sRegNumber=findViewById(R.id.regNumber);
        sPcSerialNumber=findViewById(R.id.pcSerialNumber);
        sPhone=findViewById(R.id.studentPhone);

        saveInfoBtn = findViewById(R.id.addButton);
        cameraPermissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions =new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
         /*
         initiate database connection in main function
          */
        dbHelper= new DatabaseConnector(this);
        studentImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imagePickDialog();
            }
        });

        saveInfoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             // when click on save button, insert data in database
                getData();
            }
        });
    }

    private void getData() {
        name= ""+sName.getText().toString();
        regNumber=""+sRegNumber.getText().toString();
        serialNumber=""+sPcSerialNumber.getText().toString();
        phone=""+sPhone.getText().toString();
        timeStamp=""+System.currentTimeMillis();

        long id= dbHelper.insertStudentInfo(
                ""+name,
                ""+regNumber,
                ""+serialNumber,
                ""+imageUri,
                ""+phone,
                ""+timeStamp,
                ""+timeStamp
        ) ;
      
        Toast.makeText(this, "New Student has been added Successfully", Toast.LENGTH_SHORT).show();
    }

    private void imagePickDialog() {
        String[] options={"Camera","Gallery"};
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Select image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0){
                    //if 0 then open camera and check camera permissions
                    if (!checkCameraPermission()){
                        //if permission is not granted , request fot it
                        requestCameraPermission();
                    }
                    else {
                        pickFromCamera();
                    }
                }
                else if(which==1){
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else {
                        PickFromStorage();
                    }
                }
             }
        });
        builder.create().show();  
    }                             
/*
    Getting image from gallery
 */
    private void PickFromStorage() {
        Intent galleryIntent=new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,IMAGE_PICK_GALLERY_CODE);

    }
    /*
        Getting image from camera
     */
    private void pickFromCamera() {
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"images title");
        values.put(MediaStore.Images.Media.DESCRIPTION,"images description");

        imageUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMERA_CODE);

    }

    //check storage permission
    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                (PackageManager.PERMISSION_GRANTED);
        return  result;
    }
    // request storage permissions
    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE);
    }
    // check camera permission
    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==
                (PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                (PackageManager.PERMISSION_GRANTED);
        return  result && result1;
    }
    // request camera permissions
    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                boolean cameraAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                boolean storageAccepted=grantResults[1]== PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted && storageAccepted){
                    pickFromCamera();
                }
                else{
                    Toast.makeText(this, "Camera permission denied!",Toast.LENGTH_SHORT).show();
                }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if (grantResults.length>0){
                    boolean storageAccepted=grantResults[0]== PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        PickFromStorage();
                    }
                    else{
                            Toast.makeText(this, "Storage permission denied!",Toast.LENGTH_SHORT).show();
                    }}
            }
            }

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        /*
        add image cropping library
         */
        if(resultCode == RESULT_OK){
            if(requestCode==IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1).start(this);
            }
            else if(requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON).
                        setAspectRatio(1,1).start(this);
                
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if(resultCode == RESULT_OK){
                    Uri resultUri = result.getUri();
                    imageUri=resultUri;
                    studentImg.setImageURI(resultUri);
                }
                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error= result.getError();
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean onSupportNavigationUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}