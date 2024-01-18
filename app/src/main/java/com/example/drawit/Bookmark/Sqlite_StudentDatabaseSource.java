package com.example.drawit.Bookmark;

import static com.example.drawit.Bookmark.Sqlite_StudentDatabaseHelper.STUDENT_TABLE;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Sqlite_StudentDatabaseSource {

    Sqlite_StudentDatabaseHelper studentDatabaseHelper ;
    SQLiteDatabase sqLiteDatabase ;
    Sqlite_StudentModel studentModel ;

    public Sqlite_StudentDatabaseSource(Context context) {

        studentDatabaseHelper = new Sqlite_StudentDatabaseHelper(context);

    }


    public void open()
    {
    sqLiteDatabase =studentDatabaseHelper.getWritableDatabase();
    }

    public void close()
    {
        studentDatabaseHelper.close();
    }

    public boolean addStudent(Sqlite_StudentModel studentModel)
    {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Sqlite_StudentDatabaseHelper.COL_NAME,studentModel.getName());
        contentValues.put(Sqlite_StudentDatabaseHelper.COL_AGE,studentModel.getAge());
        contentValues.put(Sqlite_StudentDatabaseHelper.COL_ADDRESS,studentModel.getAddress());
        Long insertedRow = sqLiteDatabase.insert(STUDENT_TABLE, null,contentValues);
        this.close();
        if(insertedRow>0)
        {
            return true;
        }
        else return false;

    }


    public boolean updateStudent (Sqlite_StudentModel studentModel)
    {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Sqlite_StudentDatabaseHelper.COL_NAME,studentModel.getName());
        contentValues.put(Sqlite_StudentDatabaseHelper.COL_AGE,studentModel.getAge());
        contentValues.put(Sqlite_StudentDatabaseHelper.COL_ADDRESS,studentModel.getAddress());

        int updatedRow = sqLiteDatabase.update(STUDENT_TABLE,contentValues, Sqlite_StudentDatabaseHelper.COL_ID+" =?",new String[]{String.valueOf(studentModel.getId())});
        this.close();

        if(updatedRow>0)
        return true;
        else
        return false;

    }


    public boolean deleteStudent(Sqlite_StudentModel model)
    {
        this.open();
        int deletedRow = sqLiteDatabase.delete(STUDENT_TABLE, Sqlite_StudentDatabaseHelper.COL_ID+" =?", new String[]{String.valueOf(model.getId())});
        this.close();

        if (deletedRow>0)
        {
            return true;
        }else {
            return false;
        }

    }


    public boolean delete_All_Student()
    {
        this.open();
        int deletedAll = sqLiteDatabase.delete(STUDENT_TABLE, null, null);
        this.close();

        if (deletedAll>0)
        {
            return true;
        }else {
            return false;
        }

    }


    public  ArrayList<Sqlite_StudentModel> getAllStudent()
    {

        this.open();
        ArrayList<Sqlite_StudentModel> arrayList = new ArrayList<>();

                     //Cursor cursor = sqLiteDatabase.rawQuery("Select * from Userdetails", null);
             Cursor cursor = sqLiteDatabase.query(STUDENT_TABLE,null,null,null,null,null,null,null) ;

             if(cursor.moveToFirst())
             {
                 do {
                    String name = cursor.getString(cursor.getColumnIndex(studentDatabaseHelper.COL_NAME));
                    int age = cursor.getInt(cursor.getColumnIndex(studentDatabaseHelper.COL_AGE));
                    String address = cursor.getString(cursor.getColumnIndex(studentDatabaseHelper.COL_ADDRESS));
                    int id = cursor.getInt(cursor.getColumnIndex(studentDatabaseHelper.COL_ID));

                     Sqlite_StudentModel studentModel = new Sqlite_StudentModel(id,name,age,address)  ;
                     arrayList.add(studentModel);

                 } while (cursor.moveToNext()) ;
             }
                this.close();
             cursor.close();

        return arrayList;
    }

}
