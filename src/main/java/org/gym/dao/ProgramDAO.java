package org.gym.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.gym.object.Program;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by anni0913 on 15.10.2014.
 */
public class ProgramDAO extends BaseDaoImpl<Program, Integer> {

    protected ProgramDAO(ConnectionSource connectionSource, Class<Program> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Program> getProgramByName(String name) throws SQLException {

        QueryBuilder<Program, Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq(Program.NAME, name);
        PreparedQuery<Program> preparedQuery = queryBuilder.prepare();
        List<Program> listOfPrograms = query(preparedQuery);
        return listOfPrograms;

    }
}
