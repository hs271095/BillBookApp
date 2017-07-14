package bangalore.bms.billbookonline;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    Button register;
    String company,pan,u,p,mobile;
    Context ctx=this;
    EditText comname, panno, email, password, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        comname = (EditText) findViewById(R.id.et_name);
        panno = (EditText) findViewById(R.id.et_pan);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        phone = (EditText) findViewById(R.id.et_phone);
        register = (Button) findViewById(R.id.register_but);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                company=comname.getText().toString();
                pan=panno.getText().toString();
                u=email.getText().toString();
                p=password.getText().toString();
                mobile=phone.getText().toString();
                SQLiteDBHelper sdb=new SQLiteDBHelper(ctx);
                sdb.putInformation(sdb,company,pan,u,p,mobile);
                Toast.makeText(RegisterPage.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}

