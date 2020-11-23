package com.example.sqllitecrudeoperation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.Holder> {
    Context context;
    List<StudentData> studentList = null;

    public StudentAdapter(Context context, List<StudentData> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        StudentData studentData = studentList.get(position);
        String id = studentData.getId();
        String name = studentData.getName();
        String regNum = studentData.getRegNumber();
        String serialNum = studentData.getSerialNumber();
        String image = studentData.getImage();
        String phone = studentData.getPhone();
        final String addTime = studentData.getAddTimeStamp();
        final String updateTime = studentData.getUpdateTimeStamp();
        //set
        holder.studentImgView.setImageURI(Uri.parse(image));
        holder.fullName.setText(name);
        holder.regNum.setText(regNum);
        holder.serialNum.setText(serialNum);
        holder.phone.setText(phone);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
                        "" + id,
                        "" + name,
                        "" + regNum,
                        "" + serialNum,
                        "" + image,
                        "" + phone,
                        "" + addTime,
                        "" + updateTime
                );
            }
        });


    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView studentImgView;
        TextView fullName, regNum, serialNum, phone;
        ImageButton editButton;

        public Holder(@NonNull View itemView) {
            super(itemView);
            studentImgView = itemView.findViewById(R.id.profileImage);
            fullName = (TextView) itemView.findViewById(R.id.studName);
            regNum = (TextView) itemView.findViewById(R.id.studRegNum);
            serialNum = (TextView) itemView.findViewById(R.id.studPcSerialNum);
            phone = (TextView) itemView.findViewById(R.id.studPhoneNum);
            editButton = itemView.findViewById(R.id.editBtn);
        }
    }

    private void editDialog(String id, String name, String regNumber, String serialNum, String image, String phone, String addTime, String updateTime) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update Record");
        builder.setMessage("Do you want to update this record ?");
        builder.setIcon(R.drawable.edit_icon);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, EditStudentRecord.class);
                intent.putExtra("ID", id);
                intent.putExtra("NAME", name);
                intent.putExtra("REGNUM", regNumber);
                intent.putExtra("SERIAL_NUM", serialNum);
                intent.putExtra("IMAGE", image);
                intent.putExtra("PHONE", phone);
                intent.putExtra("ADD_TIME", addTime);
                intent.putExtra("UPDATE_TIME", updateTime);
                intent.putExtra("editMode", true);
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        builder.create().show();
    }
}


