package com.nappstic.wodselector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class resultsDbAdapter extends SQLiteOpenHelper {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_ROUNDS = "rounds";
    public static final String KEY_TIME = "time";
    public static final String KEY_ID_WOD = "id_wod";
    public static final String KEY_FECHA = "fecha";
    private SQLiteDatabase mDB;

    private static final String DATABASE_CREATE = "CREATE TABLE wods ("
            + KEY_ROWID + " integer primary key autoincrement,"
            + KEY_ID_WOD + " INTEGER DEFAULT 0, "
            + KEY_ROUNDS + " INTEGER DEFAULT 0, "
            + KEY_FECHA + " TEXT NOT NULL , "
            + KEY_TIME + " INTEGER DEFAULT 0 )";

    private static final String DATABASE_NAME = "wodsdb";
    private static final String DATABASE_TABLE = "resultados";
    private static final int DATABASE_VERSION = 7;

    private final Context mCtx;
    private resultsDbAdapter.DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public resultsDbAdapter(Context ctx) {
        super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
        this.mCtx = ctx;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Wods Db Adapter", "Actualizando db de la version " + oldVersion + "a la version " + newVersion);
        }
    }

    resultsDbAdapter open() throws SQLException {
        mDbHelper = new resultsDbAdapter.DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        mDbHelper.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public long addResult(Integer idWod, @Nullable Integer rounds, @Nullable Integer time,String fecha) {

        mDb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_WOD, idWod);
        values.put(KEY_ROUNDS, rounds);
        values.put(KEY_TIME, time);
        values.put(KEY_FECHA,fecha);

        return mDb.insert(DATABASE_TABLE, null, values);
    }
    public Cursor fetchNameWodsWithResults ()throws SQLException{
        Cursor mCursor;
        String sql;

        sql = "SELECT W.nombre,W._id,W.result_unit,W.amrap_time FROM RESULTADOS R INNER JOIN wods W ON R.id_wod = W._id group by w.nombre order by nombre;";

        mCursor = mDb.rawQuery(sql,null);

        if (mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor fetchResults(Integer idWod) throws SQLException{

        Cursor mCursor;
        String[] tableColumns = new String[] {
                KEY_ROWID,
                KEY_ID_WOD,
                KEY_TIME,
                KEY_ROUNDS,
                KEY_FECHA
        };

        String whereClause = KEY_ID_WOD + " = ?";
        String [] whereArgs = new String[] {
                idWod.toString()
        };
        String orderBy = KEY_ROWID;
        mCursor =  mDb.query(DATABASE_TABLE,tableColumns,whereClause,whereArgs,null,null,orderBy);
        if (mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }



}
