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

public class GoodsDbhelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "goodsdb";
        public static final int DATABASE_VERSION = 1;
        public static final String TABLE_GOODS = "tblgoodsdata";
        public static final String CREATE_TABLE_GOODS= "CREATE TABLE IF NOT EXISTS "+ TABLE_GOODS+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT NULL, quantity TEXT NULL,price TEXT NULL)";
        public static final String DELETE_TABLE_GOODS="DROP TABLE IF EXISTS " + TABLE_GOODS;
        public GoodsDbhelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_TABLE_GOODS);

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(DELETE_TABLE_GOODS);
            //Create tables again
            onCreate(db);
        }
        public void insertData(String items,String Quantity,String price){

            // Open the database for writing
            SQLiteDatabase db = this.getWritableDatabase();
            // Start the transaction.
            db.beginTransaction();
            ContentValues values;

            try
            {
                values = new ContentValues();
                values.put("item",items);
                values.put("quantity",Quantity);
                values.put("price",price);
                // Insert Row
                long i = db.insert(TABLE_GOODS, null, values);
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


