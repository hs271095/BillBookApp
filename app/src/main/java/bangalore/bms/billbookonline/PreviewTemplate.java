package bangalore.bms.billbookonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ruuha on 7/15/2017.
 */

public class PreviewTemplate extends AppCompatActivity {

    private TextView tempname,billnumber,billdate,items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_layout);

        tempname=(TextView)findViewById(R.id.company_name);
        billnumber=(TextView)findViewById(R.id.bill_number);
        billdate=(TextView)findViewById(R.id.bill_date);
        items=(TextView)findViewById(R.id.items);


        Intent i = getIntent();


        tempname.setText(i.getStringExtra("n"));
        billnumber.setText(i.getStringExtra("number"));
        billdate.setText(i.getStringExtra("date"));
        items.setText("\t\t"+i.getStringExtra("service")+"\t\t\t"+i.getStringExtra("quantity")+"\t\t"+i.getStringExtra("servicecharges"));


    }


}
