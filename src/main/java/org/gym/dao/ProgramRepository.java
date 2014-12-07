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
public class ProgramRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public ProgramRepository(DatabaseHelper dh){
        this.databaseHelper = dh;
    }

    public Long storeProgram(Program program) {
        instantiateDb();

        ContentValues values = new ContentValues();
        values.put(Program.Column.NAME.name(), program.getName());
        values.put(Program.Column.DESCRIPTION.name(), program.getDescription());

        Long id = database.insert(Program.TABLE_NAME, null, values);
        program.setId(id);

        closeDb();
        return id;
    }

    public List<Program> findAllProgramsList(){
        instantiateDb();
        List<Program> programList = new LinkedList<Program>();
        String query = "SELECT  * FROM " + Program.TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Program program = new Program();
                program.setId(Long.parseLong(cursor.getString(0)));
                program.setName(cursor.getString(1));
                program.setDescription(cursor.getString(2));

                programList.add(program);
            } while (cursor.moveToNext());
        }

        closeDb();
        return programList;
    }

    // Do we need this?
    public Cursor getAllProgramsCursor(){
        instantiateDb();
        String query = "SELECT  * FROM " + Program.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        closeDb();
        return cursor;
    }


    public void storePrograms(List<Program> programList){  //TODO think about posibility of adding collection and return their own ids
        for(Program program : programList){              //now it doesn't return
            storeProgram(program);
        }
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }

}
