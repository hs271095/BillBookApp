package bangalore.bms.billbookonline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Harshit on 18-07-2017.
 */

private class Validation extends AsyncTask<Void,Void,Integer> {
    EditText un,pwd;
    SQLiteDatabase db;
    private Activity activity;
    private ProgressDialog pd;

    public Validation(Activity activity) {
        this.activity = activity;
    }

    protected int doInBackground() {
        String u, p;
        u = un.getText().toString();
        p = pwd.getText().toString();
        db = openOrCreateDatabase("Main", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Cursor c = db.rawQuery("SELECT * FROM users WHERE TRIM(email) = '" + u.trim() + "' and TRIM(password)='" + p.trim() + "'", null);
        int cnt=c.getCount();
        c.close();
        return cnt;
    }
    protected void onPostExecute(int cnt){
        if (cnt == 0) {

            Toast.makeText(activity, "Valid user", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(activity, "Not Valid user", Toast.LENGTH_SHORT).show();

        }

    }

}
