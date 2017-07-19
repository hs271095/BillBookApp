package bangalore.bms.billbookonline;

/**
 * Created by hp on 05-07-2017.
 */

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class BillTemplateActivity extends AppCompatActivity{
        EditText temname, billnoformat, billdateformat, refno, orderno, termsdelivery;
      RadioButton mySelectedRadioButton1,mySelectedRadioButton2;
     RadioGroup radioGroup1,radioGroup2;
    Button create,preview;
    Spinner s1,s2;
    SQLiteDatabase db;
    String tname,bno,bdate;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_bill_template);
            temname= (EditText) findViewById(R.id.templatename);
            billnoformat= (EditText) findViewById(R.id.billnumber);
            billdateformat=(EditText)findViewById(R.id.billdate);
            refno=(EditText)findViewById(R.id.supplierno);
            orderno=(EditText)findViewById(R.id.buyersorderno);
            termsdelivery=(EditText)findViewById(R.id.deliveryterms);
            radioGroup1=(RadioGroup)findViewById(R.id.radiogrp1);
            radioGroup2=(RadioGroup)findViewById(R.id.radiogrp2);
            s1=(Spinner)findViewById(R.id.spinner1);
            s2=(Spinner)findViewById(R.id.spinner2);
            create=(Button)findViewById(R.id.createemplate);
            preview=(Button)findViewById(R.id.previewtemplate);
            List<String> categories=new ArrayList<>();
            categories.add("Automobile");
            categories.add("Business Services");
            categories.add("Computers");
            categories.add("Education");
            categories.add("Personal");
            categories.add("Travel");
            List<String> categories2 = new ArrayList<String>();
            categories2.add("A");
            categories2.add("B");
            categories2.add("C");
            categories2.add("D");
            categories2.add("P");
            categories2.add("T");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           s1.setAdapter(dataAdapter);

            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           s2.setAdapter(dataAdapter1);
            final Context context=this;
            try{
                db=openOrCreateDatabase("TemplateDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);
                db.execSQL("CREATE TABLE  template(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, templatename VARCHAR,billnumber VARCHAR,billdate VARCHAR,payment VARCHAR,suppliernumber VARCHAR,buyerordernumber VARCHAR,deliveryterms VARCHAR,servicesector VARCHAR,servicetype VARCHAR,vat VARCHAR);");
            }catch (Exception e){
                e.printStackTrace();
            }
            create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     tname=temname.getText().toString();
                     bno=billnoformat.getText().toString();
                    bdate=billdateformat.getText().toString();
                    int id=radioGroup1.getCheckedRadioButtonId();
                    mySelectedRadioButton1=(RadioButton)findViewById(id);
                    String paymentmode=mySelectedRadioButton1.getText().toString();
                    String supplier=refno.getText().toString();
                    String buyer=orderno.getText().toString();
                    String terms=termsdelivery.getText().toString();
                    int id1=radioGroup2.getCheckedRadioButtonId();
                    mySelectedRadioButton2=(RadioButton)findViewById(id1);
                    String vat=mySelectedRadioButton2.getText().toString();
                    ContentValues cv=new ContentValues();
                    cv.put("templatename",tname);
                    cv.put("billnumber",bno);
                    cv.put("billdate",bdate);
                    cv.put("payment",paymentmode);
                    cv.put("suppliernumber",supplier);
                    cv.put("buyerordernumber",buyer);
                    cv.put("deliveryterms",terms);
                    cv.put("servicesector",String.valueOf(s1.getSelectedItem()));
                    cv.put("servicetype",String.valueOf(s2.getSelectedItem()));
                    cv.put("vat",vat);
                    if(db.insert("bill",null,cv)!=-1){
                        Toast.makeText(BillTemplateActivity.this,"Template Creates Successfully",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(BillTemplateActivity.this,PreviewTemplate.class);
                        i.putExtra("n",tname);
                        i.putExtra("number",bno);
                        i.putExtra("date",bdate);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(BillTemplateActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }

                }
            });
            preview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(BillTemplateActivity.this,PreviewTemplate.class);
                    i.putExtra("n",tname);
                    i.putExtra("number",bno);
                    i.putExtra("date",bdate);
                    startActivity(i);
                }
            });


        }
}
