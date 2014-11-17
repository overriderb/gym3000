package org.gym;

import org.gym.object.Program;
import java.util.*;

/**
 * Created by anni0913 on 07.07.2014.
 */
public class Factory {

    /*public static void setPreparedProgramsAndWorkouts() throws SQLException {
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
        HandsChestProgram.addWorkout(new Workout("Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps.", R.drawable.cable_rows));

        Program LegsBackProgram = new Program("Legs/Back training day", "Description of Legs/Back program");
        HelperFactory.getHelper().getProgramDAO().create(LegsBackProgram);

        LegsBackProgram.addWorkout(new Workout("Tricep pushdowns", "Tricep pushdowns isolate the tricep muscles and are " +
                "usually done at the tail end of a workout to give the triceps a real pump. It's a very easy exercise " +
                "to perform and is ideal for beginners to weight training.", R.drawable.tricep_pushdowns));
        LegsBackProgram.addWorkout(new Workout("Crunch", " The standard abdominal crunch exercise targets the stomach " +
                "muscles. The crunch is a safe and effective exercise that is great for beginners to help develop " +
                "strong abdominal muscles.", R.drawable.crunch));
        LegsBackProgram.addWorkout(new Workout("Back extensions", "Back extensions are also known as hyperextensions. It's " +
                "an exercise that works the lower back and also the buttocks and hamstrings.", R.drawable.back_extensions));
        LegsBackProgram.addWorkout(new Workout("Cardio trainings", "Training for pussy-man, who doesn't want to kick " +
                "somebody's ass on street, only likes pedalling", R.drawable.cardio_trainings));
    }*/

    public static List<Program> getProgramList(){

        List<Program> programList = new LinkedList<Program>();
        programList.add(new Program("First program", "First description"));
        programList.add(new Program("Second program", "Second description"));
        programList.add(new Program("Third program", "Third description"));
        return programList;
    }
}
