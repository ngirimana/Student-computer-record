package com.example.sqllitecrudeoperation;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentInfoHolder extends RecyclerView.ViewHolder {
StudentData  studentData;
ImageView studentImgView;
TextView fullName,regNum,serialNum,phone;
    @SuppressLint("WrongViewCast")
    public StudentInfoHolder(@NonNull View itemView) {
        super(itemView);
        studentImgView=itemView.findViewById(R.id.profileImage);
        fullName=(TextView)itemView.findViewById(R.id.studName);
        regNum=(TextView)itemView.findViewById(R.id.studRegNum);
        serialNum=(TextView)itemView.findViewById(R.id.studPcSerialNum);
        phone=(TextView)itemView.findViewById(R.id.studPhoneNum);
    }
    public void bind(StudentData studentData){
        studentImgView.setImageURI(Uri.parse(studentData.getImage()));
        fullName.setText(studentData.getName());
        regNum.setText(studentData.getRegNumber());
        serialNum.setText(studentData.getSerialNumber());
        phone.setText(studentData.getPhone());
    }
}
