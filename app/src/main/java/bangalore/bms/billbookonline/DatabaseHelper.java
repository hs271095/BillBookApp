package bangalore.bms.billbookonline;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Harshit on 20-07-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "servicesdb";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_SERVICES = "tblservicesdata";
    public static final String CREATE_TABLE_SERVICES= "CREATE TABLE IF NOT EXISTS "+ TABLE_SERVICES+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, services TEXT NULL, charges TEXT NULL)";
    public static final String DELETE_TABLE_SERVICES="DROP TABLE IF EXISTS " + TABLE_SERVICES;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_SERVICES);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_TABLE_SERVICES);
        //Create tables again
        onCreate(db);
    }
    public void insertData(String services,String charges){

        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try
        {
            values = new ContentValues();
            values.put("services",services);
            values.put("charges",charges);
            // Insert Row
            long i = db.insert(TABLE_SERVICES, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
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
