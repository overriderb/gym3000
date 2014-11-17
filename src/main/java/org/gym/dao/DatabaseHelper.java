package org.gym.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.gym.object.Program;

/**
 * Created by AndreyNick on 12.11.2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sqlite_test";

    private ProgramAdapter programAdapter = null;

    private static final String CREATE_PROGRAM = "CREATE TABLE " + ProgramAdapter.TABLE_NAME + " ("
            + ProgramAdapter.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProgramAdapter.NAME + " TEXT NOT NULL, "
            + ProgramAdapter.DESCRIPTION + " TEXT);";

    private static final String CREATE_WORKOUT = "CREATE TABLE " + WorkoutAdapter.TABLE_NAME + " ("
            + WorkoutAdapter.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WorkoutAdapter.PARENT_PROGRAM_ID + " INTEGER NOT NULL, "
            + WorkoutAdapter.NAME + " TEXT NOT NULL, "
            + WorkoutAdapter.PICTURE_ID + " INTEGER, "
            + WorkoutAdapter.DESCRIPTION + " TEXT);";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROGRAM);
        db.execSQL(CREATE_WORKOUT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProgramAdapter.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutAdapter.TABLE_NAME);
        onCreate(db);
    }

    public ProgramAdapter getProgramAdapter(){
        if(programAdapter == null){
            programAdapter = new ProgramAdapter(this.getWritableDatabase());
        }
        return programAdapter;
    }

}
