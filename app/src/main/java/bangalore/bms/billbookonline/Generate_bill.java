package bangalore.bms.billbookonline;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static bangalore.bms.billbookonline.R.id.email;

/**
 * Created by Harshit on 08-07-2017.
 */

public class Generate_bill extends AppCompatActivity {
    EditText client_name,address_client,mobile_client,email_client,service_charges,discount,quantity;
    Spinner service_type,template_type;
    Button generate;
    SQLiteDatabase db,sd;
    String selected;
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_bill);
            client_name= (EditText) findViewById(R.id.ET_nameclient);
            address_client=(EditText)findViewById(R.id.ET_addressclient);
            mobile_client=(EditText)findViewById(R.id.ET_mobileclient);
            email_client=(EditText)findViewById(R.id.ET_emailclient);
            service_charges=(EditText)findViewById(R.id.ET_servicecharge);
            quantity=(EditText)findViewById(R.id.ET_quantity);
            discount=(EditText)findViewById(R.id.ET_perdis);
            generate=(Button)findViewById(R.id.generatebill);
            service_type=(Spinner)findViewById(R.id.spin_selectservice);
            template_type=(Spinner)findViewById(R.id.spin_templatetype);

            List<String> service=new ArrayList<>();

            service.add("abc");
            service.add("def");
            service.add("ghi");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, service);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            service_type.setAdapter(dataAdapter);
            final Context context=this;
            List<String> template=new ArrayList<>();
            try{
                sd=openOrCreateDatabase("TemplateDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);
                Cursor c=sd.rawQuery("SELECT * FROM template",null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String s1=c.getString(1);
                    template.add(s1);
                    c.moveToNext();
                }

                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, template);
                dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                template_type.setAdapter(dataAdapter1);
            }catch (SQLiteException e){

            }



            try{
                db=openOrCreateDatabase("BillDB", SQLiteDatabase.CREATE_IF_NECESSARY,null);
                db.execSQL("CREATE TABLE  bill(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, clientname VARCHAR,clientaddress VARCHAR,clientcontact VARCHAR,clientemail VARCHAR,service VARCHAR,servicecharges VARCHAR,quantity VARCHAR,discount VARCHAR);");
            }catch (Exception e){
                e.printStackTrace();
            }

            generate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String clientname = client_name.getText().toString();
                    String addressclient = address_client.getText().toString();
                    String mobileclient = mobile_client.getText().toString();
                    String emailclient = email_client.getText().toString();
                    String servicecharge = service_charges.getText().toString();
                    String quant = quantity.getText().toString();
                    String disc = discount.getText().toString();

                    ContentValues values = new ContentValues();


                    values.put("clientname", clientname);
                    values.put("clientaddress", addressclient);
                    values.put("clientcontact", mobileclient);
                    values.put("clientemail", emailclient);
                    values.put("servicecharges", servicecharge);
                    values.put("service", String.valueOf(service_type.getSelectedItem()));

                    values.put("quantity", quant);
                    values.put("discount", disc);
                    if (db.insert("bill", null, values) != -1){
                        Intent i=new Intent(Generate_bill.this,PreviewTemplate.class);
                        i.putExtra("service",String.valueOf(service_type.getSelectedItem()));
                        i.putExtra("servicecharges",servicecharge);
                        i.putExtra("quantity",quant);

                        startActivity(i);
                    }

                    else {
                        Toast.makeText(Generate_bill.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

