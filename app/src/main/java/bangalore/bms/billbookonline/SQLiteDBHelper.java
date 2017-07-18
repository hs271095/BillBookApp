package bangalore.bms.billbookonline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 04-07-2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Main";
   public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID =  "userid";
    public static final String COLUMN_COMPANY_NAME =  "comapny name";
    public static final String COLUMN_PAN_NUMBER =  "pan number";
    public static final String COLUMN_EMAIL =  "email";
    public static final String COLUMN_PASSWORD =  "password";
    public static final String COLUMN_MOBILE =  "mobile";
    //public static final String COLUMN_IMEI="imei";
    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_COMPANY_NAME + " TEXT, "+
                    COLUMN_PAN_NUMBER + " TEXT, "+
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_MOBILE + " TEXT "+")";
    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void putInformation(SQLiteDBHelper sdb,String cname,String pno,String email,String pass,String mobile){
         SQLiteDatabase sq=sdb.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_COMPANY_NAME,cname);
        cv.put(COLUMN_PAN_NUMBER,pno);
        cv.put(COLUMN_EMAIL,email);
        cv.put(COLUMN_PASSWORD,pass);
        cv.put(COLUMN_MOBILE,mobile);
       // cv.put(COLUMN_IMEI,imei);
       long k= sq.insert(TABLE_NAME,null,cv);

    }
    public Cursor getInformation(SQLiteDBHelper sdb){
        SQLiteDatabase sq=sdb.getReadableDatabase();
        String[] columns={COLUMN_EMAIL,COLUMN_PASSWORD};
        Cursor cr=sq.query(TABLE_NAME,columns,null,null,null,null,null);
        return cr;
    }
}
