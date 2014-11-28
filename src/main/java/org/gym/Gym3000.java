package org.gym;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import org.gym.activity.R;
import org.gym.dao.DatabaseHelper;
import org.gym.object.Attempt;
import org.gym.object.Exercise;
import org.gym.object.Program;
import org.gym.object.Workout;


/**
 * Created by anni0913 on 15.10.2014.
 */
public class Gym3000 extends Application {

    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        databaseHelper = new DatabaseHelper(this);
        if(isFirstStart()){                                //For first start of application please change change isFirstStart()
            setInitialPrograms();                         //to true for correct setting to DB default programs
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    /**
     * Method needs for checking, is this a first start of application on this phone
     */
    private boolean isFirstStart() {
        Context context = this.getApplicationContext();
        SharedPreferences sp = context.getSharedPreferences("SharedPreferences", 0);
        boolean firstStartKey = sp.getBoolean("key", true);

        if (firstStartKey) {
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("key", false);
            e.commit();
        }
        return firstStartKey;
    }

    /**
     * Method sets to DB programs and workouts, which user will see after starting this application first time
     */
    private void setInitialPrograms(){
        Program handsChestProgram = new Program("Hands/Chest training day", "Blah-blah, hands and chest");
        databaseHelper.getProgramAdapter().setProgram(handsChestProgram);

        long id1 = databaseHelper.getWorkoutAdapter().setWorkout(new Workout(handsChestProgram, "Warm-up", "Some warm-up exercises. It is important to warming-up and " +
                "stretch every muscle which will be burden.", R.drawable.warm_up));

        long id2 = databaseHelper.getExerciseAdapter().setExercise(new Exercise(id1, "11.01.1989", "M"));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id2, 10, 15));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id2, 15, 13));
        long id3 = databaseHelper.getExerciseAdapter().setExercise(new Exercise(id1, "25.01.1989", "L"));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id3, 8, 20));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id3, 10, 15));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id3, 15, 15));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id3, 20, 8));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id3, 25, 15));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id3, 115, 4));
        long id4 = databaseHelper.getExerciseAdapter().setExercise(new Exercise(id1, "20.01.1989", "S"));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id4, 10, 15));
        databaseHelper.getAttemptAdapter().setAttempt(new Attempt(id4, 15, 13));


        databaseHelper.getWorkoutAdapter().setWorkout(new Workout(handsChestProgram, "Lateral raise", "The lateral raise with dumbbells is an effective " +
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
        databaseHelper.getWorkoutAdapter().setWorkout(new Workout(handsChestProgram, "Lat pulldown", "The lat pulldown works the major muscles in the back and " +
                "also the biceps in the arms. It's a good exercise to progress up to doing pull ups as it develops " +
                "strength in the back and arms, so its ideal for beginners.", R.drawable.lat_pulldown));
        databaseHelper.getWorkoutAdapter().setWorkout(new Workout(handsChestProgram, "Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps.", R.drawable.cable_rows));

        Program legsBackProgram = new Program("Legs/Back training day", "Description of Legs/Back program");
        databaseHelper.getProgramAdapter().setProgram(legsBackProgram);

        databaseHelper.getWorkoutAdapter().setWorkout(new Workout(legsBackProgram, "Tricep pushdowns", "Tricep pushdowns isolate the tricep muscles and are " +
                "usually done at the tail end of a workout to give the triceps a real pump. It's a very easy exercise " +
                "to perform and is ideal for beginners to weight training.", R.drawable.tricep_pushdowns));
        databaseHelper.getWorkoutAdapter().setWorkout(new Workout(legsBackProgram, "Crunch", " The standard abdominal crunch exercise targets the stomach " +
                "muscles. The crunch is a safe and effective exercise that is great for beginners to help develop " +
                "strong abdominal muscles.", R.drawable.crunch));
        databaseHelper.getWorkoutAdapter().setWorkout(new Workout(legsBackProgram, "Back extensions", "Back extensions are also known as hyperextensions. It's " +
                "an exercise that works the lower back and also the buttocks and hamstrings.", R.drawable.back_extensions));
        databaseHelper.getWorkoutAdapter().setWorkout(new Workout(legsBackProgram, "Cardio trainings", "Training for pussy-man, who doesn't want to kick " +
                "somebody's ass on street, only likes pedalling", R.drawable.cardio_trainings));

    }
}
