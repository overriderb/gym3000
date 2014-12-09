package org.gym.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.gym.domain.Attempt;
import org.gym.domain.Exercise;
import org.gym.domain.Program;
import org.gym.domain.Workout;

/**
 * Created by AndreyNick on 12.11.2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "SQLite3";

    private ProgramRepository programRepository = null;
    private WorkoutRepository workoutRepository = null;
    private ExerciseRepository exerciseRepository = null;
    private AttemptRepository attemptRepository = null;

    private static final String CREATE_PROGRAM = "CREATE TABLE " + Program.TABLE_NAME + " ("
            + Program.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Program.Column.NAME + " TEXT NOT NULL, "
            + Program.Column.DESCRIPTION + " TEXT);";

    private static final String CREATE_WORKOUT = "CREATE TABLE " + Workout.TABLE_NAME + " ("
            + Workout.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Workout.Column.PARENT_ID + " INTEGER NOT NULL, "
            + Workout.Column.NAME + " TEXT NOT NULL, "
            + Workout.Column.PICTURE_ID + " INTEGER, "
            + Workout.Column.DESCRIPTION + " TEXT);";

    private static final String CREATE_EXERCISE = "CREATE TABLE " + Exercise.TABLE_NAME + " ("
            + Exercise.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Exercise.Column.PARENT_ID + " INTEGER NOT NULL, "
            + Exercise.Column.DATE + " DATE NOT NULL, "
            + Exercise.Column.TYPE + " CHAR);";

    private static final String CREATE_ATTEMPT = "CREATE TABLE " + Attempt.TABLE_NAME + " ("
            + Attempt.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Attempt.Column.PARENT_ID + " INTEGER NOT NULL, "
            + Attempt.Column.WEIGHT + " TEXT NOT NULL, "
            + Attempt.Column.COUNT + " INTEGER NOT NULL);";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROGRAM);
        db.execSQL(CREATE_WORKOUT);
        db.execSQL(CREATE_EXERCISE);
        db.execSQL(CREATE_ATTEMPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Program.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Workout.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Exercise.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Attempt.TABLE_NAME);
        onCreate(db);
    }

    public ProgramRepository getProgramRepository(){
        if(programRepository == null){
            programRepository = new ProgramRepository(this);
        }
        return programRepository;
    }

    public WorkoutRepository getWorkoutRepository(){
        if(workoutRepository == null){
            workoutRepository = new WorkoutRepository(this);
        }
        return workoutRepository;
    }

    public ExerciseRepository getExerciseRepository(){
        if(exerciseRepository == null){
            exerciseRepository = new ExerciseRepository(this);
        }
        return exerciseRepository;

    }

    public AttemptRepository getAttemptRepository(){
        if(attemptRepository == null){
            attemptRepository = new AttemptRepository(this);
        }
        return attemptRepository;

    }

}
