package com.nappstic.wodselector;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.Context.MODE_PRIVATE;

public class WodDbAdapter {



    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_TYPE = "type";
    public static final String KEY_AMRAP_TIME = "amrap_time";
    public static final String KEY_GIRL_HERO = "girl_hero";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_RESULT_UNITS = "result_unit";
    public static final String KEY_PISTOLS = "pistols";
    public static final String KEY_BACK_EXTENSIONS = "back_extensions";
    public static final String KEY_BEAR_CRAWL= "Bear_crawl";
    public static final String KEY_BENCH_PRESS = "bench_press";
    public static final String KEY_BOX_JUMP = "box_jump";
    public static final String KEY_BURPEES = "burpees";
    public static final String KEY_CHEST_TO_BAR = "chest_to_bar";
    public static final String KEY_CLEAN = "clean";
    public static final String KEY_CLEAN_AND_JERK = "clean_and_jerk";
    public static final String KEY_RUN = "run";
    public static final String KEY_DEADLIFT = "deadlift";
    public static final String KEY_DOUBLE_UNDERS = "double_unders";
    public static final String KEY_FRONT_SQUAT = "front_squat";
    public static final String KEY_GHD_SIT_UPS = "ghd_sit_ups";
    public static final String KEY_HANDSTAND_PUSH_UPS = "handstand_push_ups";
    public static final String KEY_HANG_POWER_CLEAN = "hang_power_clean";
    public static final String KEY_KETTLEBELL_SWING = "kettlebell_swing";
    public static final String KEY_KNEES_TO_ELBOW = "knees_to_elbow";
    public static final String KEY_L_PULL_UPS = "l_pull_ups";
    public static final String KEY_MUSCLE_UPS = "muscle_ups";
    public static final String KEY_OVERHEAD_SQUAT = "overhead_squat";
    public static final String KEY_OVERHEAD_WALK = "overhead_walk";
    public static final String KEY_POWER_SNATCH = "power_snatch";
    public static final String KEY_PULL_UPS = "pull_ups";
    public static final String KEY_PUSH_JERK = "push_jerk";
    public static final String KEY_PUSH_PRESS = "push_press";
    public static final String KEY_PUSH_UPS = "push_ups";
    public static final String KEY_ROW = "remo";
    public static final String KEY_RING_DIPS = "ring_Dips";
    public static final String KEY_RING_HANDSTAD_PUSHUPS = "ring_hashtand_pushups";
    public static final String KEY_ROMANIAN_DEADLIFT = "Romanian_deadlift";
    public static final String KEY_ROPE_CLIMB = "rope_Climb";
    public static final String KEY_SIT_UPS = "sit_ups";
    public static final String KEY_SNATCH = "snatch";
    public static final String KEY_SQUAT_CLEAN = "squat_clean";
    public static final String KEY_SQUATS = "squats";
    public static final String KEY_SUMO_DEADLIFT_HIGH_PULL = "sumo_deadlift_high_pull";
    public static final String KEY_THRUSTER = "thruster";
    public static final String KEY_TOES_TO_BAR = "toes_to_bar";
    public static final String KEY_TURKISH_GET_UPS = "turkish_get_ups";
    public static final String KEY_WALKING_LUNGE = "walking_lunge";
    public static final String KEY_WALL_BALL = "wall_ball";



    private static final String DATABASE_CREATE = "CREATE TABLE wods ("
            + KEY_ROWID + " integer primary key autoincrement,"
            + KEY_NOMBRE + " TEXT NOT NULL, "
            + KEY_TYPE + " TEXT NOT NULL, "
            + KEY_AMRAP_TIME + " INTEGER "
            + KEY_GIRL_HERO + " TEXT NOT NULL, "
            + KEY_DESCRIPCION + " TEXT NOT NULL, "
            + KEY_RESULT_UNITS + " TEXT NOT NULL, "
            + KEY_PISTOLS + " INTEGER DEFAULT 0, "
            + KEY_BACK_EXTENSIONS + " INTEGER DEFAULT 0, "
            + KEY_BEAR_CRAWL + " INTEGER DEFAULT 0,"
            + KEY_BENCH_PRESS + " INTEGER DEFAULT 0, "
            + KEY_BOX_JUMP + " INTEGER DEFAULT 0, "
            + KEY_BURPEES + " INTEGER DEFAULT 0, "
            + KEY_CHEST_TO_BAR + " INTEGER DEFAULT 0, "
            + KEY_CLEAN + " INTEGER DEFAULT 0, "
            + KEY_CLEAN_AND_JERK + " INTEGER DEFAULT 0, "
            + KEY_RUN + " INTEGER DEFAULT 0, "
            + KEY_DEADLIFT + " INTEGER DEFAULT 0, "
            + KEY_DOUBLE_UNDERS + " INTEGER DEFAULT 0, "
            + KEY_FRONT_SQUAT + " INTEGER DEFAULT 0, "
            + KEY_GHD_SIT_UPS + " INTEGER DEFAULT 0, "
            + KEY_HANDSTAND_PUSH_UPS + " INTEGER DEFAULT 0, "
            + KEY_HANG_POWER_CLEAN + " INTEGER DEFAULT 0, "
            + KEY_KETTLEBELL_SWING + " INTEGER DEFAULT 0, "
            + KEY_KNEES_TO_ELBOW + " INTEGER DEFAULT 0, "
            + KEY_L_PULL_UPS + " INTEGER DEFAULT 0, "
            + KEY_MUSCLE_UPS + " INTEGER DEFAULT 0, "
            + KEY_OVERHEAD_SQUAT + " INTEGER DEFAULT 0, "
            + KEY_OVERHEAD_WALK + " INTEGER DEFAULT 0, "
            + KEY_POWER_SNATCH + " INTEGER DEFAULT 0, "
            + KEY_PULL_UPS + " INTEGER DEFAULT 0, "
            + KEY_PUSH_JERK + " INTEGER DEFAULT 0, "
            + KEY_PUSH_PRESS + " INTEGER DEFAULT 0, "
            + KEY_PUSH_UPS + " INTEGER DEFAULT 0, "
            + KEY_ROW + " INTEGER DEFAULT 0, "
            + KEY_RING_DIPS + " INTEGER DEFAULT 0, "
            + KEY_RING_HANDSTAD_PUSHUPS + " INTEGER DEFAULT 0, "
            + KEY_ROPE_CLIMB + " INTEGER DEFAULT 0, "
            + KEY_ROMANIAN_DEADLIFT + " INTEGER DEFAULT 0, "
            + KEY_SIT_UPS + " INTEGER DEFAULT 0, "
            + KEY_SNATCH + " INTEGER DEFAULT 0, "
            + KEY_SQUAT_CLEAN + " INTEGER DEFAULT 0, "
            + KEY_SQUATS + " INTEGER DEFAULT 0, "
            + KEY_SUMO_DEADLIFT_HIGH_PULL + " INTEGER DEFAULT 0, "
            + KEY_THRUSTER + " INTEGER DEFAULT 0, "
            + KEY_TOES_TO_BAR + " INTEGER DEFAULT 0, "
            + KEY_TURKISH_GET_UPS + " INTEGER DEFAULT 0, "
            + KEY_WALKING_LUNGE + " INTEGER DEFAULT 0, "
            + KEY_WALL_BALL + " INTEGER DEFAULT 0 )";

    private static final String DATABASE_NAME = "wodsdb";
    private static final String DATABASE_TABLE = "wods";
    private static final int DATABASE_VERSION = 7;
    private static final String DB_PATH = "/data/data/com.nappstic.wodselector/databases/";

    private final Context mCtx;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;


    private void copyDataBase() throws IOException{
        //Abrimos la base de datos vacia.
        InputStream myInput = mCtx.getAssets().open("wodsdb");

        //creamos el path hasta la BBDD
        String outFileName = DB_PATH + DATABASE_NAME;

        //Hacemos la transferencia de un fichero a otro
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from inputfile to outputfile
        byte [] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer,0,length);
        }

        //close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDataBase();
        SQLiteDatabase db_Read = null;
        if(dbExist){
            //do nothing - database already exist
        }else{
            //By calling this method and empty database will be created into the default system path
            // of your application so we are gonna be able to overwrite that database with our database.
            mDbHelper = new DatabaseHelper(mCtx);
            db_Read = mDbHelper.getReadableDatabase();
            db_Read.close();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //database does't exist yet.
        } if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void openDataBase () throws SQLException{
        //open the database

        String myPath = DB_PATH + DATABASE_NAME;
        mDb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //db.execSQL(DATABASE_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Wods Db Adapter", "Actualizando db de la version " + oldVersion + "a la version " + newVersion);
        }
    }

    WodDbAdapter(Context context){
        this.mCtx = context;

    }

    WodDbAdapter open() throws SQLException{
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    void close(){
        mDbHelper.close();
    }
    Cursor fetchWod(String id) throws SQLException{
        Cursor mCursor = mDb.query(true,DATABASE_TABLE,new String []{KEY_NOMBRE,KEY_DESCRIPCION,KEY_TYPE},KEY_ROWID+ "="+id,null,null,null,null,null );
        if (mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor fetchFilteredWods(){
        Cursor mCursor;
        String whereClause = "";
        String[] whereArgs;
        String orderBy;
        SharedPreferences settings = mCtx.getSharedPreferences("filtersPrefs", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        Boolean firstOR = true;

        String[] tableColumns = new String[] {
                KEY_ROWID,
                KEY_NOMBRE,
                KEY_TYPE,
                KEY_DESCRIPCION,
                KEY_RESULT_UNITS,
                KEY_AMRAP_TIME,

        };

        if (settings.getBoolean("fGirls",true)){
            whereClause ="( " + KEY_GIRL_HERO + " = 'girl'";
            if (settings.getBoolean("fHeroes",true)){
                whereClause = whereClause + " OR " +KEY_GIRL_HERO + " = 'heroe' )";
            }else{
                whereClause = whereClause + ")";
            }
        }else{
            if (settings.getBoolean("fHeroes",true)){
                whereClause = "( " +KEY_GIRL_HERO + " = 'heroe' )";
            }else{

                whereClause =  "";
            }
        }
        whereClause = whereClause + " AND ( ";

        if(settings.getBoolean("back_extensions",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_BACK_EXTENSIONS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_BACK_EXTENSIONS + " =1";
            }
        }
        if(settings.getBoolean("Bear_crawl",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_BEAR_CRAWL + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_BEAR_CRAWL + " =1";
            }

        }
        if(settings.getBoolean("bench_press",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_BENCH_PRESS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_BENCH_PRESS + " =1";
            }

        }
        if(settings.getBoolean("box_jump",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_BOX_JUMP + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_BOX_JUMP + " =1";
            }

        }
        if(settings.getBoolean("burpees",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_BURPEES + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_BURPEES + " =1";
            }

        }
        if(settings.getBoolean("clean",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_CLEAN + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_CLEAN + " =1";
            }

        }
        if(settings.getBoolean("chest_to_bar",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_CHEST_TO_BAR + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_CHEST_TO_BAR + " =1";
            }

        }
        if(settings.getBoolean("clean_and_jerk",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_CLEAN_AND_JERK + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_CLEAN_AND_JERK + " =1";
            }

        }
        if(settings.getBoolean("deadlift",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_DEADLIFT + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_DEADLIFT + " =1";
            }

        }
        if(settings.getBoolean("double_unders",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_DOUBLE_UNDERS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_DOUBLE_UNDERS + " =1";
            }

        }
        if(settings.getBoolean("front_squat",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_FRONT_SQUAT + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_FRONT_SQUAT + " =1";
            }

        }
        if(settings.getBoolean("ghd_sit_ups",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_GHD_SIT_UPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_GHD_SIT_UPS + " =1";
            }

        }
        if(settings.getBoolean("handstand_push_ups",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_HANDSTAND_PUSH_UPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_HANDSTAND_PUSH_UPS + " =1";
            }

        }
        if(settings.getBoolean("hang_power_clean",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_HANG_POWER_CLEAN + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_HANG_POWER_CLEAN + " =1";
            }

        }
        if(settings.getBoolean("kettlebell_swing",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_KETTLEBELL_SWING + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_KETTLEBELL_SWING + " =1";
            }

        }
        if(settings.getBoolean("knees_to_elbow",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_KNEES_TO_ELBOW + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_KNEES_TO_ELBOW + " =1";
            }

        }
        if(settings.getBoolean("l_pull_ups",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_L_PULL_UPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_L_PULL_UPS + " =1";
            }

        }
        if(settings.getBoolean("muscle_ups",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_MUSCLE_UPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_MUSCLE_UPS + " =1";
            }

        }
        if(settings.getBoolean("overhead_squat",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_OVERHEAD_SQUAT + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_OVERHEAD_SQUAT + " =1";
            }

        }
        if(settings.getBoolean("overhead_walk",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_OVERHEAD_WALK + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_OVERHEAD_WALK + " =1";
            }

        }
        if(settings.getBoolean("pistols",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_PISTOLS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_PISTOLS + " =1";
            }

        }
        if(settings.getBoolean("power_snatch",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_POWER_SNATCH + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_POWER_SNATCH + " =1";
            }

        }
        if(settings.getBoolean("pull_ups",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_PULL_UPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_PULL_UPS + " =1";
            }

        }
        if(settings.getBoolean("push_jerk",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_PUSH_JERK + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_PUSH_JERK + " =1";
            }

        }
        if(settings.getBoolean("push_press",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_PUSH_PRESS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_PUSH_PRESS + " =1";
            }

        }
        if(settings.getBoolean("push_ups",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_PUSH_UPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_PUSH_UPS + " =1";
            }

        }
        if(settings.getBoolean("run",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_RUN + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_RUN + " =1";
            }

        }
        if(settings.getBoolean("remo",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_ROW + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_ROW + " =1";
            }

        }
        if(settings.getBoolean("ring_Dips",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_RING_DIPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_RING_DIPS + " =1";
            }

        }
        if(settings.getBoolean("ring_hashtand_pushups",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_RING_HANDSTAD_PUSHUPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_RING_HANDSTAD_PUSHUPS + " =1";
            }

        }
        if(settings.getBoolean("Romanian_deadlift",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_ROMANIAN_DEADLIFT + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_ROMANIAN_DEADLIFT + " =1";
            }

        }
        if(settings.getBoolean("rope_Climb",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_ROPE_CLIMB + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_ROPE_CLIMB + " =1";
            }

        }
        if(settings.getBoolean("sit_ups",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_SIT_UPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_SIT_UPS + " =1";
            }

        }
        if(settings.getBoolean("snatch",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_SNATCH + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_SNATCH + " =1";
            }

        }
        if(settings.getBoolean("squat_clean",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_SQUAT_CLEAN + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_SQUAT_CLEAN + " =1";
            }

        }
        if(settings.getBoolean("squats",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_SQUATS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_SQUATS + " =1";
            }

        }
        if(settings.getBoolean("sumo_deadlift_high_pull",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_SUMO_DEADLIFT_HIGH_PULL + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_SUMO_DEADLIFT_HIGH_PULL + " =1";
            }

        }
        if(settings.getBoolean("thruster",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_THRUSTER + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_THRUSTER + " =1";
            }

        }
        if(settings.getBoolean("toes_to_bar",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_TOES_TO_BAR + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_TOES_TO_BAR + " =1";
            }

        }
        if(settings.getBoolean("turkish_get_ups",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_TURKISH_GET_UPS + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_TURKISH_GET_UPS + " =1";
            }

        }
        if(settings.getBoolean("walking_lunge",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_WALKING_LUNGE + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_WALKING_LUNGE + " =1";
            }

        }
        if(settings.getBoolean("wall_ball",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_WALL_BALL + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_WALL_BALL + " =1";
            }

        }
        if(settings.getBoolean("yard_overhead_walk",true)){
            if(firstOR){
                whereClause = whereClause + " " + KEY_OVERHEAD_WALK + " =1";
                firstOR=false;
            }else{
                whereClause = whereClause + " OR " + KEY_OVERHEAD_WALK + " =1";
            }

        }


        whereClause = whereClause + " )";




        orderBy = KEY_NOMBRE;
        mCursor =  mDb.query(DATABASE_TABLE,tableColumns,whereClause,null,null,null,orderBy);
        if (mCursor != null){
            mCursor.moveToFirst();
        }



        return mCursor;
    }
    public Integer countAllWods(String tipo) {
        Integer total = null;
        Cursor mCursor;


        mCursor = fetchAllWods(tipo);

        total = mCursor.getCount();


        return total;

    }
    public Cursor fetchAllWods (String tipo) throws SQLException{
        Cursor mCursor;
        String whereClause;
        String[] whereArgs;

        String[] tableColumns = new String[] {
                KEY_ROWID,
                KEY_NOMBRE,
                KEY_TYPE,
                KEY_AMRAP_TIME,
                KEY_DESCRIPCION,
                KEY_RESULT_UNITS,
                KEY_PISTOLS,
                KEY_BACK_EXTENSIONS,
                KEY_BEAR_CRAWL,
                KEY_BENCH_PRESS,
                KEY_BOX_JUMP,
                KEY_BURPEES,
                KEY_CHEST_TO_BAR,
                KEY_CLEAN,
                KEY_CLEAN_AND_JERK,
                KEY_RUN,
                KEY_DEADLIFT,
                KEY_DOUBLE_UNDERS,
                KEY_FRONT_SQUAT,
                KEY_GHD_SIT_UPS,
                KEY_HANDSTAND_PUSH_UPS,
                KEY_HANG_POWER_CLEAN,
                KEY_KETTLEBELL_SWING,
                KEY_KNEES_TO_ELBOW,
                KEY_L_PULL_UPS,
                KEY_MUSCLE_UPS,
                KEY_OVERHEAD_SQUAT,
                KEY_OVERHEAD_WALK,
                KEY_POWER_SNATCH,
                KEY_PULL_UPS,
                KEY_PUSH_JERK,
                KEY_PUSH_PRESS,
                KEY_PUSH_UPS,
                KEY_ROW,
                KEY_RING_DIPS,
                KEY_RING_HANDSTAD_PUSHUPS,
                KEY_ROMANIAN_DEADLIFT,
                KEY_ROPE_CLIMB,
                KEY_SIT_UPS,
                KEY_SNATCH,
                KEY_SQUAT_CLEAN,
                KEY_SQUATS,
                KEY_SUMO_DEADLIFT_HIGH_PULL,
                KEY_THRUSTER,
                KEY_TOES_TO_BAR,
                KEY_TURKISH_GET_UPS,
                KEY_WALKING_LUNGE,
                KEY_WALL_BALL,

        };
        if (tipo != "all") {
            whereClause = KEY_GIRL_HERO + " = ? OR  " + KEY_GIRL_HERO + " = ?";
            whereArgs = new String[]{
                    tipo,
                    tipo.toUpperCase()
            };
        }else{
            whereArgs = null;
            whereClause = null;
        }
        String orderBy = KEY_NOMBRE;
        mCursor =  mDb.query(DATABASE_TABLE,tableColumns,whereClause,whereArgs,null,null,orderBy);

        if (mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;

    }
}


