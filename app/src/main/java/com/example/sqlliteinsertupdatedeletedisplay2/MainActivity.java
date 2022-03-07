package com.example.sqlliteinsertupdatedeletedisplay2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText roll_no1,name1,class1;
    TextView records;
    Button insert,update,delete,display;
    MyDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1=findViewById(R.id.name);
        roll_no1=findViewById(R.id.roll);
        class1=findViewById(R.id.class1);
        records=findViewById(R.id.records);
        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        display=findViewById(R.id.display);
        mydb=new MyDatabase(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nname=name1.getText().toString();
                String nroll_no=roll_no1.getText().toString();
                String nclass=class1.getText().toString();
                mydb.insertData(nroll_no,nname,nroll_no);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nroll_no=roll_no1.getText().toString();
                String nname=name1.getText().toString();
                String nclass=class1.getText().toString();
                mydb.updateData(nroll_no,nname,nclass);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nroll_no=roll_no1.getText().toString();
                mydb.deleteData(nroll_no);
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor=mydb.Display();
                if (cursor.getCount()>0)
                {
                    int i=1;
                    StringBuffer buffer=new StringBuffer();
                    while (cursor.moveToNext())
                    {
                        buffer.append(i+"-Roll no : "+cursor.getString(0)+"     ");
                        buffer.append("Name : "+cursor.getString(1)+"     ");
                        buffer.append("Class : "+cursor.getString(2)+"\n");
                        i++;
                    }
                    records.setText(buffer.toString());
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No record found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}