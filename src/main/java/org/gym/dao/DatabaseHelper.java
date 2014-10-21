package org.gym.dao;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.gym.Factory;
import org.gym.object.Program;
import org.gym.object.Workout;

import java.sql.SQLException;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    //имя файла базы данных который будет храниться в /data/data/APPNAME/DATABASE_NAME.db
    private static final String DATABASE_NAME ="gym300.db";

    //с каждым увеличением версии, при нахождении в устройстве БД с предыдущей версией будет выполнен метод onUpgrade();
    private static final int DATABASE_VERSION = 1;

    //ссылки на DAO соответсвующие сущностям, хранимым в БД
    private ProgramDAO programDAO = null;
    private WorkoutDAO workoutDAO = null;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Выполняется, когда файл с БД не найден на устройстве
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
        try
        {
            TableUtils.createTable(connectionSource, Program.class);
            TableUtils.createTable(connectionSource, Workout.class);
        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DATABASE_NAME); //TODO check it!!
            throw new RuntimeException(e);
        }
    }

    //Выполняется, когда БД имеет версию отличную от текущей
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer){
        try{
            //Так делают ленивые, гораздо предпочтительнее не удаляя БД аккуратно вносить изменения
            TableUtils.dropTable(connectionSource, Program.class, true);
            TableUtils.dropTable(connectionSource, Workout.class, true);
            onCreate(db, connectionSource);
        }
        catch (SQLException e){
            Log.e(TAG,"error upgrading db "+DATABASE_NAME+"from ver "+oldVer);  //TODO check it!!
            throw new RuntimeException(e);
        }
    }

    //синглтон для GoalDAO
    public ProgramDAO getProgramDAO() throws SQLException{
        if(programDAO == null){
            programDAO = new ProgramDAO(getConnectionSource(), Program.class);
        }
        return programDAO;
    }
    //синглтон для RoleDAO
    public WorkoutDAO getWorkoutDAO() throws SQLException{
        if(workoutDAO == null){
            workoutDAO = new WorkoutDAO(getConnectionSource(), Workout.class);
        }
        return workoutDAO;
    }

    //выполняется при закрытии приложения
    @Override
    public void close(){
        super.close();
        programDAO = null;
        workoutDAO = null;
    }
}
