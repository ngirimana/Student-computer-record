package com.example.sqllitecrudeoperation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    DatabaseConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar =getSupportActionBar() ;

        actionBar.setTitle("All Student Information");

        mRecyclerView=findViewById(R.id.recyclerView1);

        dbConnector= new DatabaseConnector(this);
        showStudentRecord();
        fab =findViewById(R.id.addFabButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,StudentRecord.class);
                intent.putExtra("editMode",false);
                startActivity(intent);
            }
        });
    }

    private void showStudentRecord() {
        StudentAdapter studentAdapter= new StudentAdapter(MainActivity.this,dbConnector.getAllData(Contants.S_ADD_TIMESTAMP + " DESC"));
        mRecyclerView.setAdapter(studentAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showStudentRecord();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== event.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}