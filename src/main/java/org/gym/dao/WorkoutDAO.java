package org.gym.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.gym.object.Workout;

import java.sql.SQLException;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class WorkoutDAO extends BaseDaoImpl<Workout, Integer> {

    protected WorkoutDAO(ConnectionSource connectionSource, Class<Workout> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    //make methods
    /*public void createSome(Workout workout){
        QueryBuilder<Workout, String> queryBuilder = queryBuilder();
    }*/
}
