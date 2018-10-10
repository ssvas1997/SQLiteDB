package com.example.srinivas.database;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText id,fname,lname,marks;
    Button add,delete,update,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DBHelper(this);
        id=findViewById(R.id.id);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        marks=findViewById(R.id.marks);

        add=findViewById(R.id.add);
        delete=findViewById(R.id.delete);
        update=findViewById(R.id.update);
        show=findViewById(R.id.show);
    }

    public void add(View view){
        int id1=Integer.parseInt(id.getText().toString());
        String name1=fname.getText().toString();
        String name2=lname.getText().toString();
        int marks1=Integer.parseInt(marks.getText().toString());
        Boolean isInserted=dbHelper.insertData(id1,name1,name2,marks1);
        if (isInserted)
            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
    }

    public void delete(View view){
        Integer deleterows=dbHelper.deleteData(Integer.parseInt(id.getText().toString()));
        if(deleterows>0)
            Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Data not Deleted",Toast.LENGTH_LONG).show();
    }

    public void update(View view){
        int id1=Integer.parseInt(id.getText().toString());
        String name1=fname.getText().toString();
        String name2=lname.getText().toString();
        int marks1=Integer.parseInt(marks.getText().toString());
        Boolean updaterows=dbHelper.updateData(id1,name1,name2,marks1);
        if (updaterows)
            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
    }

    public void show(View view){
        Cursor res= dbHelper.showData();
        if(res.getCount()==0){
            //Show Message
            Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_LONG).show();
        }
        else{
            StringBuffer buffer= new StringBuffer();
            while(res.moveToNext()){
                buffer.append("\nID:"+res.getInt(0));
                buffer.append("\nName:"+res.getString(1));
                buffer.append("\nFname:"+res.getString(2));
                buffer.append("\nMarks"+res.getString(3));
            }
            showMessage("Data",buffer.toString());
        }

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
