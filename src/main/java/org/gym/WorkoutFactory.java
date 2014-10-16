package org.gym;

import org.gym.activity.R;
import org.gym.dao.HelperFactory;
import org.gym.object.Workout;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anni0913 on 07.07.2014.
 */
public class WorkoutFactory {

    public static List getTestExercisesCollection(){

        //Workout copipasted from: http://www.workoutbox.com/workouts/muscle-building-workouts/team-player/2-day-split-muscle-building-workout/

        List <Workout> exercisesCollection = new ArrayList<Workout>();

        exercisesCollection.add(new Workout("Warm-up", "Some warm-up exercises. It is important to warming-up and " +
                "stretch every muscle which will be burden.", R.drawable.warm_up));
        exercisesCollection.add(new Workout("Lateral raise", "The lateral raise with dumbbells is an effective " +
                "exercise for developing the deltoids, and is performed by extending the arm to the side of the body " +
                "with the elbow extended.\n lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text \n" +
                " lot of text", R.drawable.lateral_raise));
        exercisesCollection.add(new Workout("Lat pulldown", "The lat pulldown works the major muscles in the back and " +
                "also the biceps in the arms. It's a good exercise to progress up to doing pull ups as it develops " +
                "strength in the back and arms, so its ideal for beginners.", R.drawable.lat_pulldown));
        exercisesCollection.add(new Workout("Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps.", R.drawable.cable_rows));
        exercisesCollection.add(new Workout("Tricep pushdowns", "Tricep pushdowns isolate the tricep muscles and are " +
                "usually done at the tail end of a workout to give the triceps a real pump. It's a very easy exercise " +
                "to perform and is ideal for beginners to weight training.", R.drawable.tricep_pushdowns));
        exercisesCollection.add(new Workout("Crunch", " The standard abdominal crunch exercise targets the stomach " +
                "muscles. The crunch is a safe and effective exercise that is great for beginners to help develop " +
                "strong abdominal muscles.", R.drawable.crunch));
        exercisesCollection.add(new Workout("Back extensions", "Back extensions are also known as hyperextensions. It's " +
                "an exercise that works the lower back and also the buttocks and hamstrings.", R.drawable.back_extensions));
        exercisesCollection.add(new Workout("Cardio trainings", "Training for pussy-man, which doesn't want to kick " +
                "somebody's ass on street, only likes pedalling", R.drawable.cardio_trainings));

        return exercisesCollection;
    }

    public static void setWorkoutListToDb() throws SQLException{
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Warm-up", "Some warm-up exercises. It is important to warming-up and " +
                "stretch every muscle which will be burden.", R.drawable.warm_up));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Lat pulldown", "The lat pulldown works the major muscles in the back and " +
                "also the biceps in the arms. It's a good exercise to progress up to doing pull ups as it develops " +
                "strength in the back and arms, so its ideal for beginners.", R.drawable.lat_pulldown));
        HelperFactory.getHelper().getWorkoutDAO().create((new Workout("Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps.", R.drawable.cable_rows)));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Tricep pushdowns", "Tricep pushdowns isolate the tricep muscles and are " +
                "usually done at the tail end of a workout to give the triceps a real pump. It's a very easy exercise " +
                "to perform and is ideal for beginners to weight training.", R.drawable.tricep_pushdowns));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Back extensions", "Back extensions are also known as hyperextensions. It's " +
                "an exercise that works the lower back and also the buttocks and hamstrings.", R.drawable.back_extensions));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Cardio trainings", "Training for pussy-man, which doesn't want to kick " +
                "somebody's ass on street, only likes pedalling", R.drawable.cardio_trainings));

    }

    public static List<Workout> getWorkoutsFromDb(){
        List <Workout> exercisesCollection = new LinkedList<Workout>();
        try {
            //setWorkoutListToDb();
            exercisesCollection = HelperFactory.getHelper().getWorkoutDAO().queryForAll();
        } catch (SQLException e) {
            exercisesCollection.add(new Workout("DB exception", "DB exception", 0));
        }
        return exercisesCollection;
    }
}
