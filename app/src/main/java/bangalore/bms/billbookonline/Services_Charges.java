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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.R.attr.id;

/**
 * Created by Harshit on 19-07-2017.
 */

public class Services_Charges extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_charges);
        context=this;
        DatabaseHelper dataHelper=new DatabaseHelper(context);
//        dataHelper.insertData("Preparation of a Teaser","1000");
//        dataHelper.insertData("Editing","1000");
//        dataHelper.insertData("Script of Teaser","500");
//        dataHelper.insertData("advertisement","2000");

        TableLayout tableLayout;
        tableLayout=(TableLayout)findViewById(R.id.table_layout);
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
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(16);
                        tv.setPadding(5, 5, 5, 5);
                        tv.setText(text);
                        row.addView(tv);
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
