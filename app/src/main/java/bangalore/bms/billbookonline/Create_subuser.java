package bangalore.bms.billbookonline;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshit on 08-07-2017.
 */

public class Create_subuser extends AppCompatActivity{
    EditText employee_name, employee_number,work_email,contact,username,password;
    Button createuser,viewuser;
    Spinner userlist;
    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_sub_user);
        password=(EditText)findViewById(R.id.ET_password);
        employee_name=(EditText)findViewById(R.id.ET_empname);
        employee_number=(EditText)findViewById(R.id.ET_empsubuser);
        work_email=(EditText)findViewById(R.id.ET_emailsubuser);
        contact=(EditText)findViewById(R.id.ET_mobileclient);
        username=(EditText)findViewById(R.id.ET_username);
        createuser=(Button)findViewById(R.id.createuser);
        viewuser=(Button)findViewById(R.id.viewuser);
        userlist=(Spinner)findViewById(R.id.spin_namesubuser);

        List<String> users = new ArrayList<String>();
        try{
            db=openOrCreateDatabase("SubUserDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            Cursor c=db.rawQuery("SELECT * FROM subuser",null);

            c.moveToFirst();

            while(!c.isAfterLast()){

                String name=c.getString(1);
                users.add(name);
                c.moveToNext();
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            userlist.setAdapter(dataAdapter);
        }catch (SQLiteException e){

        }
        final Context context=this;
        try {
            db = openOrCreateDatabase("SubUserDB", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL("CREATE TABLE  subuser(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,employeename VARCHAR, employeenumber VARCHAR,email VARCHAR,contact VARCHAR,username VARCHAR,password VARCHAR);");
        }catch (Exception e){
            e.printStackTrace();
        }
        viewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Create_subuser.this,View_user.class);
                startActivity(i);
            }
        });
        createuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employeename=employee_name.getText().toString();
                String pass=password.getText().toString();
                String employee_no=employee_number.getText().toString();
                String email=work_email.getText().toString();
                String mobile=contact.getText().toString();
                String user_name=username.getText().toString();
                ContentValues values=new ContentValues();
                values.put("employeename",employeename);
                values.put("employeenumber",employee_no);
                values.put("email",email );
                values.put("contact",mobile);
                values.put("username",user_name);
                values.put("password",pass);
                if(db.insert("subuser",null,values)!=-1){
                    Toast.makeText(Create_subuser.this,"Record Inserted",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Create_subuser.this,"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
