package bangalore.bms.billbookonline;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class View_user extends Activity {
    EditText search;
    Button viewuser;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        search=(EditText)findViewById(R.id.search_key);



        viewuser=(Button)findViewById(R.id.view);

            final Context context=this;
            viewuser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String search_key=search.getText().toString();
                    db=openOrCreateDatabase("SubUserDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);
                    TextView userview=(TextView)findViewById(R.id.viewuser);
                    String temp="";


                    Cursor c = db.rawQuery("SELECT * FROM subuser WHERE TRIM(employeename) = '"+search_key.trim()+"'", null);
                    if( c.getCount()==0) {
                        Toast.makeText(View_user.this,"User not found",Toast.LENGTH_SHORT).show();
                    }else{
                        c.moveToFirst();

                        String s1=c.getString(1);
                        String s2=c.getString(2);
                        String s3=c.getString(3);
                        String s4=c.getString(4);
                        String s5=c.getString(5);
                        temp=temp+"\nName:"+s1+"\nEmployee no.:"+s2+"\nEmail:"+s3+"\nMobile:"+s4+"\nUsername:"+s5;


                        userview.setText(temp);
                    }

                }
            });
        }

    }

