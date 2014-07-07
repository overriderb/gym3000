package org.gym;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anni0913 on 07.07.2014.
 */
public class WorkoutFactory {

    public static List getExercisesCollection(){

        //Workout copipasted from: http://www.workoutbox.com/workouts/muscle-building-workouts/team-player/2-day-split-muscle-building-workout/

        List <Workout> exercisesCollection = new ArrayList<Workout>();

        exercisesCollection.add(new Workout("Warm-up", "Some warm-up exercises. It is important to warming-up and " +
                "stretch every muscle which will be burden."));
        exercisesCollection.add(new Workout("Lateral raise", "The lateral raise with dumbbells is an effective " +
                "exercise for developing the deltoids, and is performed by extending the arm to the side of the body " +
                "with the elbow extended."));
        exercisesCollection.add(new Workout("Lat pulldown", "The lat pulldown works the major muscles in the back and " +
                "also the biceps in the arms. It's a good exercise to progress up to doing pull ups as it develops " +
                "strength in the back and arms, so its ideal for beginners."));
        exercisesCollection.add(new Workout("Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps."));
        exercisesCollection.add(new Workout("Tricep pushdowns", "Tricep pushdowns isolate the tricep muscles and are " +
                "usually done at the tail end of a workout to give the triceps a real pump. It's a very easy exercise " +
                "to perform and is ideal for beginners to weight training."));
        exercisesCollection.add(new Workout("Crunch", " The standard abdominal crunch exercise targets the stomach " +
                "muscles. The crunch is a safe and effective exercise that is great for beginners to help develop " +
                "strong abdominal muscles."));
        exercisesCollection.add(new Workout("Back extensions", "Back extensions are also known as hyperextensions. It's " +
                "an exercise that works the lower back and also the buttocks and hamstrings."));
        exercisesCollection.add(new Workout("Cardio trainings", "Training for pussy-man, which doesn't want to kick " +
                "somebody's ass on street, only likes pedalling"));

        return exercisesCollection;
    }
}
