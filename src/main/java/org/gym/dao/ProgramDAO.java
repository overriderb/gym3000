package org.gym.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import org.gym.object.Program;

import java.sql.SQLException;


/**
 * Created by anni0913 on 15.10.2014.
 */
public class ProgramDAO extends BaseDaoImpl<Program, Integer> {

    protected ProgramDAO(ConnectionSource connectionSource, Class<Program> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    //make methods
}
