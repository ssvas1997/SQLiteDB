package com.example.srinivas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="student.db";
    private static final String col1="ID";
    private static final String col2="name";
    private static final String col3="surname";
    private static final String col4="marks";

    //Cursor is a ResultSet
    //CursorFactory multiple data sets
    public DBHelper(Context context) {
        super(context,DB_NAME,null,1);
        SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE student_table ( ID integer, name text, surname text, marks integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS student_table");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(int id, String name, String surname,int marks)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(col1,id);
        cv.put(col2,name);
        cv.put(col3,surname);
        cv.put(col4,marks);
        db.insert("student_table",null,cv);
        return true;
    }

    public Cursor showData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from student_table",null);
        return res;
    }

    public Integer deleteData(Integer id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        int deleterows= db.delete("student_table","id=?",new String[]{id.toString()});
        return deleterows;
    }

    public boolean updateData(Integer id, String name, String surname, int marks){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(col1,id);
        cv.put(col2,name);
        cv.put(col3,surname);
        cv.put(col4,marks);
        db.update("student_table",cv,"id=?",new String[]{id.toString()});
        return true;
    }
}
