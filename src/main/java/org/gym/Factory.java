package org.gym;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import org.gym.activity.R;
import org.gym.dao.HelperFactory;
import org.gym.dao.WorkoutDAO;
import org.gym.object.Program;
import org.gym.object.Workout;
import android.util.Log;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by anni0913 on 07.07.2014.
 */
public class Factory {

    public static void setWorkoutListToDb() throws SQLException{

            //Workout copipasted from: http://www.workoutbox.com/workouts/muscle-building-workouts/team-player/2-day-split-muscle-building-workout/

        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Warm-up", "Some warm-up exercises. It is important to warming-up and " +
                "stretch every muscle which will be burden.", R.drawable.warm_up));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Lateral raise", "The lateral raise with dumbbells is an effective " +
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
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Lat pulldown", "The lat pulldown works the major muscles in the back and " +
                "also the biceps in the arms. It's a good exercise to progress up to doing pull ups as it develops " +
                "strength in the back and arms, so its ideal for beginners.", R.drawable.lat_pulldown));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps.", R.drawable.cable_rows));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Tricep pushdowns", "Tricep pushdowns isolate the tricep muscles and are " +
                "usually done at the tail end of a workout to give the triceps a real pump. It's a very easy exercise " +
                "to perform and is ideal for beginners to weight training.", R.drawable.tricep_pushdowns));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Crunch", " The standard abdominal crunch exercise targets the stomach " +
                "muscles. The crunch is a safe and effective exercise that is great for beginners to help develop " +
                "strong abdominal muscles.", R.drawable.crunch));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Back extensions", "Back extensions are also known as hyperextensions. It's " +
                "an exercise that works the lower back and also the buttocks and hamstrings.", R.drawable.back_extensions));
        HelperFactory.getHelper().getWorkoutDAO().create(new Workout("Cardio trainings", "Training for pussy-man, which doesn't want to kick " +
                "somebody's ass on street, only likes pedalling", R.drawable.cardio_trainings));
    }

    public static void setPreparedProgramsAndWorkouts() throws SQLException {
        Program HandsChestProgram = new Program("Hands/Chest training day", "Blah-blah, hands and chest");
        HelperFactory.getHelper().getProgramDAO().create(HandsChestProgram);

        HandsChestProgram.addWorkout(new Workout("Warm-up", "Some warm-up exercises. It is important to warming-up and " +
                "stretch every muscle which will be burden.", R.drawable.warm_up));
        HandsChestProgram.addWorkout(new Workout("Lateral raise", "The lateral raise with dumbbells is an effective " +
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
        HandsChestProgram.addWorkout(new Workout("Lat pulldown", "The lat pulldown works the major muscles in the back and " +
                "also the biceps in the arms. It's a good exercise to progress up to doing pull ups as it develops " +
                "strength in the back and arms, so its ideal for beginners.", R.drawable.lat_pulldown));




        Program LegsBackProgram = new Program("Legs/Back training Program", "Description of Legs/Back program");
        HelperFactory.getHelper().getProgramDAO().create(LegsBackProgram);

        LegsBackProgram.addWorkout(new Workout("Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps.", R.drawable.cable_rows));
        LegsBackProgram.addWorkout(new Workout("Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps.", R.drawable.cable_rows));
        LegsBackProgram.addWorkout(new Workout("Cardio trainings", "Training for pussy-man, which doesn't want to kick " +
                "somebody's ass on street, only likes pedalling", R.drawable.cardio_trainings));
    }

    public static void setTestProgramAndWorkouts() throws SQLException {
        Program program = new Program("Test_program_2", "Description");
        HelperFactory.getHelper().getProgramDAO().create(program);
        Workout workout1 = new Workout(program,"4-th program", "Description", R.drawable.warm_up);
        Workout workout2 = new Workout(program,"5-th program", "Description", R.drawable.cardio_trainings);
        Workout workout3 = new Workout(program,"6-th program", "Description", R.drawable.lat_pulldown);
        HelperFactory.getHelper().getWorkoutDAO().create(workout1);
        HelperFactory.getHelper().getWorkoutDAO().create(workout2);
        HelperFactory.getHelper().getWorkoutDAO().create(workout3);
    }

    public static List<Workout> getWorkoutsFromDb(){
        List <Workout> exercisesCollection = null;
        try {
            //exercisesCollection = HelperFactory.getHelper().getWorkoutDAO().queryForAll();

            List<Program> listOfPrograms = HelperFactory.getHelper().getProgramDAO().getProgramByName("Hands/Chest training day");
            if(listOfPrograms.isEmpty()){
                exercisesCollection = new LinkedList<Workout>();
            } else {
                Program result = listOfPrograms.get(0);
                exercisesCollection = new LinkedList<Workout>(result.getListOfWorkouts());
            }


            /*Collection<Workout> workouts = ;
            Iterator<Workout> iterator = workouts.iterator();
            while(iterator.hasNext()){
                exercisesCollection.add(iterator.next());
            }*/


        } catch (SQLException e) {
            Log.e("SQLException", e.getMessage());
        }
        return exercisesCollection;
    }

    public static List<Program> getAllProgramsFromDb(){
        List<Program> programsCollection = null;
        try {
            programsCollection = HelperFactory.getHelper().getProgramDAO().queryForAll();

        } catch (SQLException e) {
            Log.e("SQLException", e.getMessage());
        }
        return programsCollection;
    }
    public static List<Workout> getWorkoutsByProgramId (int programId){
        List<Workout> workoutsCollection = null;
        try {
            workoutsCollection = HelperFactory.getHelper().getWorkoutDAO().getWorkoutsByProgramId(programId);
        } catch (SQLException e) {
            Log.e("SQLException", e.getMessage());
        }
        return workoutsCollection;
    }
}
