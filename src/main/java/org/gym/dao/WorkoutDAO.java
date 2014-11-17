package org.gym.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.gym.object.Workout;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class WorkoutDAO extends BaseDaoImpl<Workout, Integer> {

    protected WorkoutDAO(ConnectionSource connectionSource, Class<Workout> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Workout> getWorkoutsByProgramId(int id) throws SQLException {
        QueryBuilder<Workout, Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq(Workout.PARENT_PROGRAM_ID, id);
        PreparedQuery<Workout> preparedQuery = queryBuilder.prepare();
        List<Workout> listOfWorkouts = query(preparedQuery);
        return listOfWorkouts;
    }
}
