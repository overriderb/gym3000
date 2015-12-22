package org.gym.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.gym.domain.AttemptEntity;
import org.gym.domain.ExerciseEntity;
import org.gym.domain.ExerciseTemplateEntity;
import org.gym.domain.ExerciseTemplateListEntity;
import org.gym.domain.ExerciseTypeEntity;
import org.gym.domain.ProgramEntity;
import org.gym.domain.ProgramTemplateEntity;
import org.gym.domain.UserEntity;
import org.gym.domain.WorkoutEntity;

/**
 * Main DB work class
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "SQLite_10";
    private static DatabaseHelper instance;


    private static final String CREATE_PROGRAM = "CREATE TABLE " + ProgramEntity.TABLE_NAME + " ("
            + ProgramEntity.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProgramEntity.Column.NAME + " TEXT NOT NULL, "
            + ProgramEntity.Column.DESCRIPTION + " TEXT, "
            + ProgramEntity.Column.ORDER_NUMBER + " INTEGER NOT NULL);";

    private static final String CREATE_WORKOUT = "CREATE TABLE " + WorkoutEntity.TABLE_NAME + " ("
            + WorkoutEntity.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WorkoutEntity.Column.PROGRAM_ID + " INTEGER NOT NULL, "
            + WorkoutEntity.Column.START_DATE + " INTEGER, "
            + WorkoutEntity.Column.END_DATE + " INTEGER, "
            + WorkoutEntity.Column.STATUS + " TEXT, "
            + "FOREIGN KEY (" + WorkoutEntity.Column.PROGRAM_ID + ") "
            + "REFERENCES " + ProgramEntity.TABLE_NAME + "(" + ProgramEntity.Column.ID + ")"
            + ");";

    private static final String CREATE_EXERCISE = "CREATE TABLE " + ExerciseEntity.TABLE_NAME + " ("
            + ExerciseEntity.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ExerciseEntity.Column.LEVEL + " TEXT NOT NULL, "
            + ExerciseEntity.Column.TYPE + " INTEGER NOT NULL, "
            + ExerciseEntity.Column.WORKOUT_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + ExerciseEntity.Column.WORKOUT_ID + ") "
            + "REFERENCES " + WorkoutEntity.TABLE_NAME + "(" + WorkoutEntity.Column.ID + ")"
            + ");";

    private static final String CREATE_EXERCISE_TYPE = "CREATE TABLE " + ExerciseTypeEntity.TABLE_NAME + " ("
            + ExerciseTypeEntity.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ExerciseTypeEntity.Column.PROGRAM_ID + " INTEGER NOT NULL, "
            + ExerciseTypeEntity.Column.NAME + " TEXT, "
            + ExerciseTypeEntity.Column.DESCRIPTION + " TEXT, "
            + ExerciseTypeEntity.Column.PICTURE_ID + " INTEGER, "
            + ExerciseTypeEntity.Column.ORDER_NUMBER + " INTEGER, "
            + "FOREIGN KEY (" + ExerciseTypeEntity.Column.PROGRAM_ID + ") "
            + "REFERENCES " + ProgramEntity.TABLE_NAME + "(" + ProgramEntity.Column.ID + ")"
            + ");";

    private static final String CREATE_ATTEMPT = "CREATE TABLE " + AttemptEntity.TABLE_NAME + " ("
            + AttemptEntity.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AttemptEntity.Column.EXERCISE_ID + " INTEGER NOT NULL, "
            + AttemptEntity.Column.WEIGHT + " TEXT NOT NULL, "
            + AttemptEntity.Column.COUNT + " INTEGER NOT NULL, "
            + AttemptEntity.Column.COMMENT + " TEXT, "
            + "FOREIGN KEY (" + AttemptEntity.Column.EXERCISE_ID + ") "
            + "REFERENCES " + ExerciseEntity.TABLE_NAME + "(" + ExerciseEntity.Column.ID + ")"
            + ");";

    private static final String CREATE_USER = "CREATE TABLE " + UserEntity.TABLE_NAME + " ("
            + UserEntity.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UserEntity.Column.NAME + " TEXT NOT NULL);";

    private static final String CREATE_PROGRAM_TEMPLATE = "CREATE TABLE " + ProgramTemplateEntity.TABLE_NAME + " ("
            + ProgramTemplateEntity.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProgramTemplateEntity.Column.NAME + " TEXT, "
            + ProgramTemplateEntity.Column.DESCRIPTION + " TEXT);";

    private static final String CREATE_EXERCISE_TEMPLATE = "CREATE TABLE " + ExerciseTemplateEntity.TABLE_NAME + " ("
            + ExerciseTemplateEntity.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ExerciseTemplateEntity.Column.NAME + " TEXT, "
            + ExerciseTemplateEntity.Column.DESCRIPTION + " TEXT);";

    private static final String CREATE_EXERCISE_TEMPLATE_LIST = "CREATE TABLE " + ExerciseTemplateListEntity.TABLE_NAME + " ("
            + ExerciseTemplateListEntity.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ExerciseTemplateListEntity.Column.PROGRAM_TEMPLATE_ID + " INTEGER NOT NULL, "
            + ExerciseTemplateListEntity.Column.EXERCISE_TEMPLATE_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + ExerciseTemplateListEntity.Column.PROGRAM_TEMPLATE_ID + ") "
            + "REFERENCES " + ProgramTemplateEntity.TABLE_NAME + "(" + ProgramTemplateEntity.Column.ID + ")"
            + "FOREIGN KEY (" + ExerciseTemplateListEntity.Column.EXERCISE_TEMPLATE_ID + ") "
            + "REFERENCES " + ExerciseTemplateEntity.TABLE_NAME + "(" + ExerciseTemplateEntity.Column.ID + ")"
            + ");";

    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
    }

    public static DatabaseHelper getInstance() {
        return instance;
    }

    public static void closeDb() {
        instance.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_PROGRAM);
        db.execSQL(CREATE_WORKOUT);
        db.execSQL(CREATE_EXERCISE);
        db.execSQL(CREATE_EXERCISE_TYPE);
        db.execSQL(CREATE_ATTEMPT);
        db.execSQL(CREATE_PROGRAM_TEMPLATE);
        db.execSQL(CREATE_EXERCISE_TEMPLATE);
        db.execSQL(CREATE_EXERCISE_TEMPLATE_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProgramEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseTypeEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AttemptEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ProgramTemplateEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseTemplateEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseTemplateListEntity.TABLE_NAME);
        onCreate(db);
    }
}
