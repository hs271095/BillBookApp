package bangalore.bms.billbookonline;

/**
 * Created by hp on 05-07-2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class UpdateCompanyLayout extends AppCompatActivity {
    SQLiteDatabase db, db2;
    EditText comname,panno,tinno,sertaxno,services,charges;
    Button addservice,saveprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_company_layout);
        comname=(EditText)findViewById(R.id.companyNameInput);
        panno=(EditText)findViewById(R.id.companyPANInput);
        tinno=(EditText)findViewById(R.id.companyTINInput);
        sertaxno=(EditText)findViewById(R.id.companyServiceTAXInput);
        services=(EditText)findViewById(R.id.services);
        saveprofile=(Button)findViewById(R.id.saveCompanyProfileButton);
        addservice=(Button)findViewById(R.id.addServicesButton);
        charges=(EditText)findViewById(R.id.Charges);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerServiceSector);

        List<String> categories = new ArrayList<String>();
        try {
            db = openOrCreateDatabase("ServiceDB", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            Cursor c = db.rawQuery("SELECT services FROM service", null);

            c.moveToFirst();

            while (!c.isAfterLast()) {

                String service = c.getString(0);
                categories.add(service);
                c.moveToNext();
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
        } catch (SQLiteException e) {

        }
        try {
            db = openOrCreateDatabase("ProfileDB", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL("CREATE TABLE  profile(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,companyname VARCHAR, pannumber VARCHAR,companysertax VARCHAR,tinnumber VARCHAR);");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            db2 = openOrCreateDatabase("ServiceDB", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db2.execSQL("CREATE TABLE  service(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,services VARCHAR, charges VARCHAR);");
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comn= comname.getText().toString();
                String pano= panno.getText().toString();
                String serv= sertaxno.getText().toString();
                String tin= tinno.getText().toString();
                ContentValues value=new ContentValues();
                value.put("companyname",comn);
                value.put("pannumber",pano);
                value.put("companysertax",serv );
                value.put("tinnumber",tin);
                   if(db.insert("profile",null,value)!=-1){
                    Toast.makeText(UpdateCompanyLayout.this,"Record Inserted",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(UpdateCompanyLayout.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        addservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                services.setVisibility(View.VISIBLE);
                charges.setVisibility(View.VISIBLE);
                if(services.length()==0||services==null||services.equals("")) {
                    Toast.makeText(UpdateCompanyLayout.this, "Add new Service", Toast.LENGTH_SHORT).show();
                }
                else{
                    String service = services.getText().toString();
                    String charge = charges.getText().toString();
                    ContentValues values = new ContentValues();
                    values.put("services", service);
                    values.put("charges", charge);
                    if (db2.insert("service", null, values) != -1) {
                        Toast.makeText(UpdateCompanyLayout.this, "Record Inserted", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(UpdateCompanyLayout.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
