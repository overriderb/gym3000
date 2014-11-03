package org.gym.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import org.gym.object.Exercise;
import org.gym.object.Program;

import java.sql.SQLException;

/**
 * Created by anni0913 on 22.10.2014.
 */
public class ExerciseDAO extends BaseDaoImpl<Exercise, Integer> {

    protected ExerciseDAO(ConnectionSource connectionSource, Class<Exercise> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
}