package org.gym.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.object.Program;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AndreyNick on 12.11.2014.
 */
public class ProgramAdapter {

    public static String TABLE_NAME = "program";
    public static String ID = "_id";
    public static String NAME = "name";
    public static String DESCRIPTION = "description";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    protected ProgramAdapter(DatabaseHelper dh){
        this.databaseHelper = dh;
    }


    public List<Program> getAllProgramsList(){
        instantiateDb();
        List<Program> programList = new LinkedList<Program>();
        String query = "SELECT  * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);
        Program program = null;
        if (cursor.moveToFirst()) {
            do {
                program = new Program();
                program.setId(Long.parseLong(cursor.getString(0)));
                program.setName(cursor.getString(1));
                program.setDescription(cursor.getString(2));

                programList.add(program);
            } while (cursor.moveToNext());
        }

        closeDb();
        return programList;
    }

    public Cursor getAllProgramsCursor(){
        instantiateDb();
        String query = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        closeDb();
        return cursor;
    }


    public void setPrograms(List<Program> programList){  //TODO think about posibility of adding collection and return their own ids
        for(Program program : programList){              //now it doesn't return
            setProgram(program);
        }
    }

    public long setProgram(Program program) {
        instantiateDb();

        ContentValues values = new ContentValues();
        values.put(NAME, program.getName());
        values.put(DESCRIPTION, program.getDescription());

        long id = database.insert(TABLE_NAME, null, values);

        closeDb();
        return id;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }

}
