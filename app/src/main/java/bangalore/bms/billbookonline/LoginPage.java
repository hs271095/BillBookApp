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
                Intent go= new Intent(LoginPage.this,HomePage.class);
                startActivity(go);
            }
        });


        }



    }

