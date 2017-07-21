package bangalore.bms.billbookonline;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Harshit on 01-07-2017.
 */

public class LoginPage extends AppCompatActivity{
    EditText un,pwd;
    Button login;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        un =(EditText)findViewById(R.id.un);
        pwd =(EditText)findViewById(R.id.pwd);
        login =(Button)findViewById(R.id.login1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go =new Intent(LoginPage.this,HomePage.class);
                startActivity(go);
//                String password= pwd.getText().toString();
//                String UserName= un.getText().toString();
//                validateUserTask task = new validateUserTask();
//                task.execute(new String[]{UserName,password});
            }
        });


        }

//    private class validateUserTask extends AsyncTask<String, Void, String>{
//        SQLiteDatabase db;
//
//        @Override
//        protected String doInBackground(String... params) {
//            String u= params[0];
//            String p=params[1];
//            db=openOrCreateDatabase("Main", SQLiteDatabase.CREATE_IF_NECESSARY,null);
//            Cursor c = db.rawQuery("SELECT * FROM users WHERE TRIM(email) = '"+u.trim()+"' and TRIM(password)='"+p.trim()+"'", null);
//            if(c.getCount()==0){
//
//                Toast.makeText(LoginPage.this,"Valid user",Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(LoginPage.this,HomePage.class);
//                startActivity(i);
//            }
//            else  {
//                Toast.makeText(LoginPage.this,"Not Valid user",Toast.LENGTH_SHORT).show();
//
//            }
//            c.close();
//            return null;
//        }
//    }

    }

