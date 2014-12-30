package org.gym;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.FrameLayout;
import org.gym.activity.R;
import org.gym.helper.SharedPreferencesHelper;
import org.gym.repository.DatabaseHelper;
import org.gym.domain.Attempt;
import org.gym.domain.Exercise;
import org.gym.domain.Program;
import org.gym.domain.Workout;


/**
 * Created by anni0913 on 15.10.2014.
 */
public class Gym3000 extends Application {

    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        databaseHelper = new DatabaseHelper(this);
        if(false){                                //For first start of application please change change isFirstStart()
            setInitialPrograms();
            setInitialPreferences();              //to true for correct setting to DB default programs
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
    private void setInitialPrograms() {
        Program handsChestProgram = new Program("Hands/Chest training day", "Blah-blah, hands and chest", 1);
        databaseHelper.getProgramRepository().storeProgram(handsChestProgram);

        Long id1 = databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram, "Warm-up", "Some warm-up exercises. It is important to warming-up and " +
                "stretch every muscle which will be burden.", R.drawable.warm_up, 1));

        Long id2 = databaseHelper.getExerciseRepository().storeExercise(new Exercise(id1, "11.01.1989", Exercise.TYPE.M));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id2, "10", 15));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id2, "15", 13));
        Long id3 = databaseHelper.getExerciseRepository().storeExercise(new Exercise(id1, "25.01.1989", Exercise.TYPE.L));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id3, "8", 20));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id3, "10", 15));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id3, "15", 15));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id3, "20", 8));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id3, "25", 15));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id3, "115", 4));
        Long id4 = databaseHelper.getExerciseRepository().storeExercise(new Exercise(id1, "20.01.1989", Exercise.TYPE.S));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id4, "10", 15));
        databaseHelper.getAttemptRepository().storeAttempt(new Attempt(id4, "15", 13));


        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram, "Lateral raise", "The lateral raise with dumbbells is an effective " +
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
                " lot of text", R.drawable.lateral_raise, 2));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram, "Lat pulldown", "The lat pulldown works the major muscles in the back and " +
                "also the biceps in the arms. It's a good exercise to progress up to doing pull ups as it develops " +
                "strength in the back and arms, so its ideal for beginners.", R.drawable.lat_pulldown, 3));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram, "Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps.", R.drawable.cable_rows, 4));

        Program legsBackProgram = new Program("Legs/Back training day", "Description of Legs/Back program", 2);
        databaseHelper.getProgramRepository().storeProgram(legsBackProgram);

        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram, "Tricep pushdowns", "Tricep pushdowns isolate the tricep muscles and are " +
                "usually done at the tail end of a workout to give the triceps a real pump. It's a very easy exercise " +
                "to perform and is ideal for beginners to weight training.", R.drawable.tricep_pushdowns, 6));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram, "Crunch", " The standard abdominal crunch exercise targets the stomach " +
                "muscles. The crunch is a safe and effective exercise that is great for beginners to help develop " +
                "strong abdominal muscles.", R.drawable.crunch, 7));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram, "Back extensions", "Back extensions are also known as hyperextensions. It's " +
                "an exercise that works the lower back and also the buttocks and hamstrings.", R.drawable.back_extensions, 8));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram, "Cardio trainings", "Training for pussy-man, who doesn't want to kick " +
                "somebody's ass on street, only likes pedalling", R.drawable.cardio_trainings, 9));

    }

    private void setInitialPreferences() {
        SharedPreferencesHelper.setBool(this, SharedPreferencesHelper.IS_PICTURE_OPEN, true);
        SharedPreferencesHelper.setBool(this, SharedPreferencesHelper.IS_DESCRIPTION_OPEN, true);
        SharedPreferencesHelper.setInt(this, SharedPreferencesHelper.PICTURE_HEIGHT, 200);
        SharedPreferencesHelper.setInt(this, SharedPreferencesHelper.DESCRIPTION_HEIGHT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }
}
