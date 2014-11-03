package org.gym.dao;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.gym.Factory;
import org.gym.object.Exercise;
import org.gym.object.Program;
import org.gym.object.Set;
import org.gym.object.Workout;

import java.sql.SQLException;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME ="gym3000.db";

    //onUpgrade method will be called on every increase of DB version
    private static final int DATABASE_VERSION = 1;

    private ProgramDAO programDAO = null;
    private WorkoutDAO workoutDAO = null;
    private ExerciseDAO exerciseDAO = null;
    private SetDAO setDAO = null;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    //if DB doesn't found
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
        try
        {
            TableUtils.createTable(connectionSource, Program.class);
            TableUtils.createTable(connectionSource, Workout.class);
            TableUtils.createTable(connectionSource, Exercise.class);
            TableUtils.createTable(connectionSource, Set.class);
        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    //if DB version doesn't equals current version
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer, int newVer){
        try{
            TableUtils.dropTable(connectionSource, Program.class, true);
            TableUtils.dropTable(connectionSource, Workout.class, true);
            TableUtils.dropTable(connectionSource, Exercise.class, true);
            TableUtils.dropTable(connectionSource, Set.class, true);
            onCreate(db, connectionSource);
        }
        catch (SQLException e){
            Log.e(TAG,"error upgrading db "+DATABASE_NAME+" from ver "+oldVer);
            throw new RuntimeException(e);
        }
    }

    //singeltones for dao objects
    public ProgramDAO getProgramDAO() throws SQLException{
        if(programDAO == null){
            programDAO = new ProgramDAO(getConnectionSource(), Program.class);
        }
        return programDAO;
    }

    public WorkoutDAO getWorkoutDAO() throws SQLException{
        if(workoutDAO == null){
            workoutDAO = new WorkoutDAO(getConnectionSource(), Workout.class);
        }
        return workoutDAO;
    }

    public ExerciseDAO getExerciseDAO() throws SQLException{
        if(exerciseDAO == null){
            exerciseDAO = new ExerciseDAO(getConnectionSource(), Exercise.class);
        }
        return exerciseDAO;
    }

    public SetDAO getSetDAO() throws SQLException{
        if(setDAO == null){
            setDAO = new SetDAO(getConnectionSource(), Set.class);
        }
        return setDAO;
    }

    //onClose of application
    @Override
    public void close(){
        super.close();
        programDAO = null;
        workoutDAO = null;
        exerciseDAO = null;
        setDAO = null;
    }
}
