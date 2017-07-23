package bangalore.bms.billbookonline;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Harshit on 20-07-2017.
 */

public class Ticket_generator extends AppCompatActivity {
    Context context;
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_table);
        context=this;
        TableHelper datahelper= new TableHelper(context);
        datahelper.insertData("SRI KRISHNA","Veg Resturant","New Municipal Blog","abc compound","mumbai 400007","01/07/17","COUNTER","BILL NO.-123","Perticulars","Quantity","Rate","gst","06.56AM");
        String[] title = new String[100];
        title=getFirstResult();
        TextView tv,tv1,tv2,tv3,tv4,tv5,tv6,tv8,tv9,tv10,tv7;

             tv=(TextView)findViewById(R.id._s1t1);
             tv1=(TextView)findViewById(R.id._s1t2);
             tv2=(TextView)findViewById(R.id._s1t3);
             tv3=(TextView)findViewById(R.id._s1t4);
             tv4=(TextView)findViewById(R.id._s1t5);
             tv5=(TextView)findViewById(R.id._s2t1);
//             tv6=(TextView)findViewById(R.id._s2t2);
             tv7=(TextView)findViewById(R.id._s2t3);

        setAtt(tv);
        setAtt(tv1);
        setAtt(tv2);
        setAtt(tv3);
        setAtt(tv4);
        setAtt(tv5);
//        setAtt(tv6);
        setAtt(tv7);

        tv.setText(title[0]);
        tv1.setText(title[1]);
        tv2.setText(title[2]);
        tv3.setText(title[3]);
        tv4.setText(title[4]);
        tv5.setText(title[5]);
//        tv6.setText(title[6]);
        tv7.setText(title[7]);
        populateTable(tableLayout);
        }
    public String[] getFirstResult(){
        String[] Result;
        Result=new String[100];
        context=this;
        TableHelper datahelper = new TableHelper(context);
        Cursor cursor = datahelper.getInformation();
        cursor.moveToFirst();
//        int n=cursor.getColumnCount();
        int i=0;
        try {
            while(i<=12) {
                Result[i] = cursor.getString(i);
                    i++;
                }
         }

        finally {
            cursor.close();
        }


        return Result;
    }
    public void setAtt(TextView tv){
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(16);
        tv.setPadding(5, 5, 5, 5);

    }
//    //public void setTitle(TextView tv,int postion,String[]arr){
//        tv.setText(arr[postion]);
//    }

    public void populateTable(TableLayout tableLayout){
        context=this;
        DatabaseHelper dataHelper=new DatabaseHelper(context);
        tableLayout=(TableLayout)findViewById(R.id.tablelayout4);
        tableLayout.setVisibility(View.VISIBLE);
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"ID","Service","Charge"};
        for(String c:headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM "+ DatabaseHelper.TABLE_SERVICES;
            Cursor c = db.rawQuery(selectQuery,null);
            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    // Read columns data
                    int _id = c.getInt(c.getColumnIndex("_id"));
                    String _services = c.getString(c.getColumnIndex("services"));
                    String _charges = c.getString(c.getColumnIndex("charges"));

                    // dara rows
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));


                    String[] colText = {_id + "", _services, _charges};
                    for (String text : colText) {
                        TextView textView = new TextView(this);
                        textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(16);
                        textView.setPadding(5, 5, 5, 5);
                        textView.setText(text);
                        textView.setTextColor(Color.parseColor("#000000"));
                        row.addView(textView);
                    }
                    tableLayout.addView(row);
                }
            }
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
    }

    }

