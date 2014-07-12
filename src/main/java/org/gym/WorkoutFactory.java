package org.gym;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anni0913 on 07.07.2014.
 */
public class WorkoutFactory {

    public static List getExercisesCollection(List<String> exerciseTitles, List<String> exerciseTexts) {

        //Workout copipasted from: http://www.workoutbox.com/workouts/muscle-building-workouts/team-player/2-day-split-muscle-building-workout/

        List <Workout> exercisesCollection = new ArrayList<Workout>();

        int exerciseCount = exerciseTitles.size() > exerciseTexts.size() ? exerciseTexts.size() : exerciseTitles.size();

        for (int i = 0; i > exerciseCount; i++) {
            exercisesCollection.add(new Workout(exerciseTitles.get(i), exerciseTexts.get(i)));
        }

        return exercisesCollection;
    }
}
