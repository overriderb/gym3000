package org.gym.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String DATABASE_NAME = "SQLite2";

    private ProgramAdapter programAdapter = null;
    private WorkoutAdapter workoutAdapter = null;
    private ExerciseAdapter exerciseAdapter = null;
    private AttemptAdapter attemptAdapter = null;

    private static final String CREATE_PROGRAM = "CREATE TABLE " + ProgramAdapter.TABLE_NAME + " ("
            + ProgramAdapter.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProgramAdapter.NAME + " TEXT NOT NULL, "
            + ProgramAdapter.DESCRIPTION + " TEXT);";

    private static final String CREATE_WORKOUT = "CREATE TABLE " + WorkoutAdapter.TABLE_NAME + " ("
            + WorkoutAdapter.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WorkoutAdapter.PARENT_ID + " INTEGER NOT NULL, "
            + WorkoutAdapter.NAME + " TEXT NOT NULL, "
            + WorkoutAdapter.PICTURE_ID + " INTEGER, "
            + WorkoutAdapter.DESCRIPTION + " TEXT);";

    private static final String CREATE_EXERCISE = "CREATE TABLE " + ExerciseAdapter.TABLE_NAME + " ("
            + ExerciseAdapter.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ExerciseAdapter.PARENT_ID + " INTEGER NOT NULL, "
            + ExerciseAdapter.DATE + " DATE NOT NULL, "
            + ExerciseAdapter.TYPE_OF_EXERCISE + " CHAR);";

    private static final String CREATE_ATTEMPT = "CREATE TABLE " + AttemptAdapter.TABLE_NAME + " ("
            + AttemptAdapter.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AttemptAdapter.PARENT_ID + " INTEGER NOT NULL, "
            + AttemptAdapter.WEIGHT + " INTEGER NOT NULL, "
            + AttemptAdapter.TIMES + " INTEGER NOT NULL);";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROGRAM);
        db.execSQL(CREATE_WORKOUT);
        db.execSQL(CREATE_EXERCISE);
        db.execSQL(CREATE_ATTEMPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProgramAdapter.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutAdapter.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseAdapter.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AttemptAdapter.TABLE_NAME);
        onCreate(db);
    }

    public ProgramAdapter getProgramAdapter(){
        if(programAdapter == null){
            programAdapter = new ProgramAdapter(this);
        }
        return programAdapter;
    }

    public WorkoutAdapter getWorkoutAdapter(){
        if(workoutAdapter == null){
            workoutAdapter = new WorkoutAdapter(this);
        }
        return workoutAdapter;
    }

    public ExerciseAdapter getExerciseAdapter(){
        if(exerciseAdapter == null){
            exerciseAdapter = new ExerciseAdapter(this);
        }
        return exerciseAdapter;

    }

    public AttemptAdapter getAttemptAdapter(){
        if(attemptAdapter == null){
            attemptAdapter = new AttemptAdapter(this);
        }
        return attemptAdapter;

    }

}
