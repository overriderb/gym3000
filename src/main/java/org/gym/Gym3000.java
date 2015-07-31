package org.gym;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.FrameLayout;
import org.gym.activity.R;
import org.gym.domain.User;
import org.gym.helper.SharedPreferencesHelper;
import org.gym.repository.DatabaseHelper;
import org.gym.repository.InitializeUtils;

/**
 * Main application class
 */
public class Gym3000 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.init(this);
        if(isFirstStart()){
            InitializeUtils.initTestPrograms();
//        if(false){                                //For first start of application please change change isFirstStart()
//            setInitialPrograms();
//            setInitialPreferences();              //to true for correct setting to DB default programs
//        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        databaseHelper.close();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        databaseHelper.close();
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
        User user = new User("Andrew");
        Program handsChestProgram1 = new Program(user.getId(), "1. Руки/грудь", "Тренировка групп мышц груди и рук", 1);
        databaseHelper.getProgramRepository().storeProgram(handsChestProgram1);

        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram1, "Разминка", "Согревающие/разминающие упражнения:\n" +
                "-Растяжка/разминка\n" +
                "-Бег/велотренажер", R.drawable.warm_up, 1));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram1, "Жим лежа, 90", "Жим лежа на лавке под углом 90", R.drawable.lateral_raise, 2));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram1, "Разводы гантелей сидя, 45", "Развод гантелей сидя на лавке под углом 45", R.drawable.lat_pulldown, 3));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram1, "Брусья","Отжимания от брусьев, можно с отягощением", R.drawable.cable_rows, 4));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram1, "Французский жим", "Французский жим лежа на лавке", R.drawable.cable_rows, 5));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram1, "Кроссовер, вниз, металл. ручка", "Трицепс на кроссовере с металлической ручкой", R.drawable.lat_pulldown, 6));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram1, "Молотки","Поочередное поднимание гантелей к плечам на бицепс", R.drawable.cable_rows, 7));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram1, "Штанга обратным хватом", "Поднимание штанги к плечам на бицепс обратным хватом", R.drawable.cable_rows, 8));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram1, "Пресс", "Пресс на римском стуле", R.drawable.lat_pulldown, 9));

        Program legsBackProgram1 = new Program(user.getId(), "2. Ноги/спина", "Тренировка групп мышц спины и ног", 2);
        databaseHelper.getProgramRepository().storeProgram(legsBackProgram1);

        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram1, "Разминка", "Согревающие/разминающие упражнения:\n" +
                "-Растяжка/разминка\n" +
                "-Бег/велотренажер\n" +
                "-Гиперэкстензия\n" +
                "-Разминка ног на тренажерах", R.drawable.warm_up, 1));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram1, "Приседания",
                "Приседания со штангой", R.drawable.crunch, 2));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram1, "Армейский жим",
                "Поднимания штанги стоя/сидя над головой с груди. При выполнении смотреть строго перед собой, " +
                        "руки шире плеч", R.drawable.back_extensions, 3));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram1, "Подьем гантелей",
                "Попеременный подьем гантелей перед собой на вытянутых руках. Подымать по широкой дуге, " +
                        "одна рука опускается, другая - поднимается, расходясь у лица", R.drawable.cardio_trainings, 4));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram1, "Тяга к подбородку",
                "Тяга штанги к подбородку узким хватом", R.drawable.crunch, 5));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram1, "Спина",
                "Упражнения на спину, которые я пока не придумал", R.drawable.crunch, 6));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram1, "Пресс",
                "Пресс на сжигание", R.drawable.crunch, 7));

        Program handsChestProgram2 = new Program(user.getId(), "3. Руки/грудь", "Тренировка групп мышц груди и рук", 3);
        databaseHelper.getProgramRepository().storeProgram(handsChestProgram2);

        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram2, "Разминка", "Согревающие/разминающие упражнения:\n" +
                "-Растяжка/разминка\n" +
                "-Бег/велотренажер", R.drawable.warm_up, 1));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram2, "Жим лежа, обратный угол", "Жим лежа на лавке под обратным углом", R.drawable.lateral_raise, 2));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram2, "Бабочка", "Разведение вытянутых рукна тренажере", R.drawable.lat_pulldown, 3));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram2, "Жим узким хватом, 90","Жим лежа на лавке под 90, узкий хват", R.drawable.cable_rows, 4));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram2, "Кроссовер, вверх, веревка", "Трицепс на кроссовере с веревкой. Тяга троса из-за голвы вверх", R.drawable.cable_rows, 5));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram2, "Кроссовер, вниз, веревка", "Трицепс на кроссовере с веревкой", R.drawable.lat_pulldown, 6));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram2, "Штанга прямым хватом","Поднимание штанги к плечам на бицепс прямым хватом", R.drawable.cable_rows, 7));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram2, "Гантель через колено", "Поднимание гантели к плечу через колено, выполняется сидя", R.drawable.cable_rows, 8));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(handsChestProgram2, "Пресс", "Пресс на римском стуле", R.drawable.lat_pulldown, 9));

        Program legsBackProgram2 = new Program(user.getId(), "4. Ноги/спина", "Тренировка групп мышц спины и ног", 4);
        databaseHelper.getProgramRepository().storeProgram(legsBackProgram2);

        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram2, "Разминка", "Согревающие/разминающие упражнения:\n" +
                "-Растяжка/разминка\n" +
                "-Бег/велотренажер\n" +
                "-Гиперэкстензия\n" +
                "-Разминка ног на тренажерах", R.drawable.warm_up, 1));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram2, "Становая тяга",
                "Тяга штанги", R.drawable.crunch, 2));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram2, "Жим штанги из-за головы",
                "Выполняется стоя/сидя, голова прямо, не наклонять ее вперед, слегка прогнуться в пояснице", R.drawable.back_extensions, 3));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram2, "Разведение гантелей",
                "Одновременное разведение гантелей стоя. Выполнение: не раскачиваться, в верхней точке разварачивать " +
                        "кисти рук внутрь, чтобы задняя часть гантели была выше передней", R.drawable.cardio_trainings, 4));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram2, "Шраги",
                "Шраги гантелями/штангой", R.drawable.crunch, 5));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram2, "Спина",
                "Упражнения на спину, которые я пока не придумал", R.drawable.crunch, 6));
        databaseHelper.getWorkoutRepository().storeWorkout(new Workout(legsBackProgram2, "Пресс",
                "Пресс на сжигание", R.drawable.crunch, 7));
    }


    private void setInitialPreferences() {
        SharedPreferencesHelper.setBool(this, SharedPreferencesHelper.IS_PICTURE_OPEN, true);
        SharedPreferencesHelper.setBool(this, SharedPreferencesHelper.IS_DESCRIPTION_OPEN, true);
        SharedPreferencesHelper.setInt(this, SharedPreferencesHelper.PICTURE_HEIGHT, 200);
        SharedPreferencesHelper.setInt(this, SharedPreferencesHelper.DESCRIPTION_HEIGHT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }
}
