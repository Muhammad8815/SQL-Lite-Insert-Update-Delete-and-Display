package com.example.sqlliteinsertupdatedeletedisplay2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    final static String DBname="New_Database";
    final static String Table_name="Student_Record";
    final static String COL_1="Roll_no";
    final static String COL_2="Name";
    final static String COL_3="Class";
    Context context1;

    public MyDatabase(Context context) {
        super(context, DBname, null, 1);
        context1=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Table_name+" (Roll_no TEXT PRIMARY KEY, Name TEXT, Class TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
    }
    public void insertData(String Roll_no,String Name, String Class)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,Roll_no);
        contentValues.put(COL_2,Name);
        contentValues.put(COL_3,Class);
        long a=db.insert(Table_name,null,contentValues);
        if (a==-1)
        {
            Toast.makeText(context1, "not inserted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context1, "Successfully inserted", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateData(String Roll_no,String Name, String Class)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,Name);
        contentValues.put(COL_3,Class);
        Cursor cursor=db.rawQuery("SELECT * FROM "+Table_name+" WHERE Roll_no=? ",new String[] {Roll_no});
        if (cursor.getCount()>0)
        {
            long a=db.update(Table_name,contentValues,"Roll_no=?", new String[] {Roll_no});
            if (a==-1)
            {
                Toast.makeText(context1, "Record Not Updated", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context1, "Record Updated", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(context1, "Records Not found", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteData(String Roll_no)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM "+Table_name+" Where Roll_no=? ",new String[] {Roll_no});
        if (cursor.getCount()>0)
        {
            long a=db.delete(Table_name,"Roll_no=?", new String[] {Roll_no});
            if (a==-1)
            {
                Toast.makeText(context1, "Record not deleted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context1, "Record Successfully Delete ", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(context1, "Record not found", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor Display()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Table_name,null);
        return cursor;
    }
}
