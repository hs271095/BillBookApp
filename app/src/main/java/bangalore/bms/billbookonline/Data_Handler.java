package bangalore.bms.billbookonline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Harshit on 20-07-2017.
 */

public class Data_Handler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ticketDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_TICKET = "tblticket";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE_S1T1 = "_s1_t1";
    public static final String COLUMN_TITLE_S1T2 = "_s1_t2";
    public static final String COLUMN_TITLE_S1T3 = "_s1_t3";
    public static final String COLUMN_TITLE_S1T4 = "_s1_t4";
    public static final String COLUMN_TITLE_S1T5 = "_s1_t5";
    public static final String COLUMN_TITLE_S2T1 = "_s2_t1";
    public static final String COLUMN_TITLE_S2T2 = "_s2_t2";
    public static final String COLUMN_TITLE_S2T3 = "_s2_t3";
    public static final String COLUMN_TITLE_S3T1 = "_s3_t1";
    public static final String COLUMN_TITLE_S3T2 = "_s3_t2";
    public static final String COLUMN_TITLE_S3T3 = "_s3_t3";
    public static final String COLUMN_SERVICE = "services";
    public static final String COLUMN_CHARGES = "charges";
    public static final String COLUMN_TITLE_F_T1 = "_F_t1";
    public static final String COLUMN_TITLE_F_T2 = "_F_t2";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE" + TABLE_TICKET + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE_S1T1 + "TEXT," +
            COLUMN_TITLE_S1T2 + "TEXT," + COLUMN_TITLE_S1T3 + "TEXT," + COLUMN_TITLE_S1T4 + "TEXT," + COLUMN_TITLE_S1T4 + "TEXT," + COLUMN_TITLE_S1T5 + "TEXT," +
            COLUMN_TITLE_S2T1 + "TEXT," + COLUMN_TITLE_S2T2 + "TEXT," + COLUMN_TITLE_S2T3 + "TEXT," + COLUMN_TITLE_S3T1 + "TEXT," + COLUMN_TITLE_S3T2 + "TEXT," + COLUMN_TITLE_S3T3 + "TEXT," + COLUMN_SERVICE + "TEXT," +
            COLUMN_CHARGES + "TEXT" + COLUMN_TITLE_F_T1 + "TEXT," + COLUMN_TITLE_F_T2 + "TEXT" + ")";

    public Data_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKET);
        onCreate(db);
        //Create tables again
    }

    public void putInformation(Data_Handler sdb, String _s1t1, String _s1t2, String _s1t3, String _s1t4, String _s1t5, String _s2t1, String _s2t2,
                               String _s2t3, String _s3t1, String _s3t2, String _s3t3, String services, String charges, String _ft1, String _ft2) {
        SQLiteDatabase sq = sdb.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE_S1T1, _s1t1);
        cv.put(COLUMN_TITLE_S1T2, _s1t2);
        cv.put(COLUMN_TITLE_S1T3, _s1t3);
        cv.put(COLUMN_TITLE_S1T4, _s1t4);
        cv.put(COLUMN_TITLE_S1T5, _s1t5);
        cv.put(COLUMN_TITLE_S2T1, _s2t1);
        cv.put(COLUMN_TITLE_S2T2, _s2t2);
        cv.put(COLUMN_TITLE_S2T3, _s2t3);
        cv.put(COLUMN_TITLE_S3T1, _s3t1);
        cv.put(COLUMN_TITLE_S3T2, _s3t2);
        cv.put(COLUMN_TITLE_S3T3, _s3t3);
        cv.put(COLUMN_SERVICE, services);
        cv.put(COLUMN_CHARGES, charges);
        cv.put(COLUMN_TITLE_F_T1, _ft1);
        cv.put(COLUMN_TITLE_F_T2, _ft2);
        long k = sq.insert(TABLE_TICKET, null, cv);

    }

    public Cursor getInformation(Data_Handler sdb) {
        SQLiteDatabase sq = sdb.getReadableDatabase();
        String[] columns = {COLUMN_TITLE_S1T1, COLUMN_TITLE_S1T2, COLUMN_TITLE_S1T3, COLUMN_TITLE_S1T4, COLUMN_TITLE_S1T5, COLUMN_TITLE_S1T5, COLUMN_TITLE_S2T1, COLUMN_TITLE_S2T2, COLUMN_TITLE_S2T3, COLUMN_TITLE_S3T1, COLUMN_TITLE_S3T2, COLUMN_TITLE_S3T3, COLUMN_ID, COLUMN_SERVICE, COLUMN_CHARGES, COLUMN_TITLE_F_T1, COLUMN_TITLE_F_T2};
        Cursor cr = sq.query(TABLE_TICKET, columns, null, null, null, null, null);
        return cr;
    }
}





