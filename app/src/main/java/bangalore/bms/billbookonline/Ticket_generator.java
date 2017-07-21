package bangalore.bms.billbookonline;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Harshit on 20-07-2017.
 */

public class Ticket_generator extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_table);
        Context context;
        context=this;
        Data_Handler sdb= new Data_Handler(context);
        sdb.putInformation(sdb,"SRI KRISHNA","Veg Resturant","New Municipal Blog","johan-putra compound","nana chowk mumbai 400007","01/07/17","COUNTER","BILL NO.-123","Perticulars","Quantity","Rate","Medu wada","65","gst","06.56AM");
        SQLiteDatabase db = sdb.getReadableDatabase();
        db.beginTransaction();
        TextView tv;
        tv=(TextView)findViewById(R.id._s1t1);
        String selectQuery = "SELECT _s1_t1 FROM "+ Data_Handler.TABLE_TICKET;
        Cursor cr = db.rawQuery(selectQuery,null);
        String title= cr.getString(0);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(16);
        tv.setPadding(5, 5, 5, 5);
        tv.setText(title);
    }
}
