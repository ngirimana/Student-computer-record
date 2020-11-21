package com.example.sqllitecrudeoperation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentInfoHolder> {
    Context context;
    List<StudentData> studentList = null;;
    StudentInfoHolder studentInfoHolder;

    public StudentAdapter(android.content.Context context, ArrayList<StudentData> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new StudentInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentInfoHolder holder, int position) {
        StudentData studentData=studentList.get(position);
        holder.bind(studentData);
        holder= studentInfoHolder;

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
