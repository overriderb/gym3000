package org.gym.repository;

import org.gym.activity.R;
import org.gym.domain.AttemptEntity;
import org.gym.domain.ExerciseEntity;
import org.gym.domain.ExerciseTypeEntity;
import org.gym.domain.ProgramEntity;
import org.gym.domain.UserEntity;
import org.gym.domain.WorkoutEntity;

import java.util.Calendar;

/**
 * TODO: Add comment
 */
public class InitializeUtils {

    /**
     * Method sets to DB programs and workouts, which user will see after starting this application first time
     */
    public static void initTestPrograms() {
        UserRepository userRepository = UserRepository.getInstance();
        ProgramRepository programRepository = ProgramRepository.getInstance();
        ExerciseTypeRepository exerciseTypeRepository = ExerciseTypeRepository.getInstance();
        WorkoutRepository workoutRepository = WorkoutRepository.getInstance();
        AttemptRepository attemptRepository = AttemptRepository.getInstance();
        ExerciseRepository exerciseRepository = ExerciseRepository.getInstance();

        UserEntity user = new UserEntity("Andrew");
        Long userStoredId = userRepository.store(user);
        user.setId(userStoredId);

        ProgramEntity handsChestFirstProgramEntity = new ProgramEntity("1. Руки/грудь", "Тренировка групп мышц груди и рук", 1);
        Long handsChestProgramStoredId = programRepository.store(handsChestFirstProgramEntity);
        handsChestFirstProgramEntity.setId(handsChestProgramStoredId);

        ProgramEntity legsBackProgramEntity = new ProgramEntity("2. Ноги/спина", "Тренировка групп мышц спины и ног", 2);
        Long legsBackProgramStoredId = programRepository.store(legsBackProgramEntity);
        legsBackProgramEntity.setId(legsBackProgramStoredId);

        ProgramEntity handsChestSecondProgramEntity = new ProgramEntity("3. Руки/грудь", "Тренировка групп мышц груди и рук", 3);
        Long handsChestSecondProgramStoredId = programRepository.store(handsChestSecondProgramEntity);
        handsChestSecondProgramEntity.setId(handsChestSecondProgramStoredId);

        ExerciseTypeEntity warmUpExerciseTypeEntity = new ExerciseTypeEntity(handsChestProgramStoredId, "Warm-up", "Some warm-up exercises. It is important to warming-up and " +
                "stretch every muscle which will be burden.", R.drawable.warm_up);
        Long warmUpExerciseTypeStoredId = exerciseTypeRepository.store(warmUpExerciseTypeEntity);
        warmUpExerciseTypeEntity.setId(warmUpExerciseTypeStoredId);

        ExerciseTypeEntity lateralRaiseExerciseTypeEntity = new ExerciseTypeEntity(handsChestProgramStoredId, "Lateral raise", "The lateral raise with dumbbells is an effective " +
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
                " lot of text", R.drawable.lateral_raise);
        Long lateralRaiseExerciseTypeStoredId = exerciseTypeRepository.store(lateralRaiseExerciseTypeEntity);
        lateralRaiseExerciseTypeEntity.setId(lateralRaiseExerciseTypeStoredId);

        ExerciseTypeEntity latPulldownExerciseTypeEntity = new ExerciseTypeEntity(handsChestProgramStoredId, "Lat pulldown", "The lat pulldown works the major muscles in the back and " +
                "also the biceps in the arms. It's a good exercise to progress up to doing pull ups as it develops " +
                "strength in the back and arms, so its ideal for beginners.", R.drawable.lat_pulldown);
        Long latPulldownExerciseTypeStoredId =exerciseTypeRepository.store(latPulldownExerciseTypeEntity);
        latPulldownExerciseTypeEntity.setId(latPulldownExerciseTypeStoredId);

        ExerciseTypeEntity cableRowsExerciseTypeEntity = new ExerciseTypeEntity(handsChestProgramStoredId, "Cable rows", "Seated cable rows are a great way to work the back muscles. " +
                "Varying the width of your hands focuses the intensity on different areas of the back. Because it's a " +
                "pulling exercise you are also working your biceps.", R.drawable.cable_rows);
        Long cableRowsExerciseTypeStoredId = exerciseTypeRepository.store(cableRowsExerciseTypeEntity);
        cableRowsExerciseTypeEntity.setId(cableRowsExerciseTypeStoredId);

        ExerciseTypeEntity tricepPushdownsExerciseTypeEntity = new ExerciseTypeEntity(legsBackProgramStoredId, "Tricep pushdowns", "Tricep pushdowns isolate the tricep muscles and are " +
                "usually done at the tail end of a workout to give the triceps a real pump. It's a very easy exercise " +
                "to perform and is ideal for beginners to weight training.", R.drawable.tricep_pushdowns);
        Long tricepPushdownsExerciseTypeStoredId =exerciseTypeRepository.store(tricepPushdownsExerciseTypeEntity);
        tricepPushdownsExerciseTypeEntity.setId(tricepPushdownsExerciseTypeStoredId);

        ExerciseTypeEntity crunchExerciseTypeEntity = new ExerciseTypeEntity(legsBackProgramStoredId, "Crunch", " The standard abdominal crunch exercise targets the stomach " +
                "muscles. The crunch is a safe and effective exercise that is great for beginners to help develop " +
                "strong abdominal muscles.", R.drawable.crunch);
        Long crunchExerciseTypeStoredId = exerciseTypeRepository.store(crunchExerciseTypeEntity);
        crunchExerciseTypeEntity.setId(crunchExerciseTypeStoredId);

        ExerciseTypeEntity backExtensionsExerciseTypeEntity = new ExerciseTypeEntity(legsBackProgramStoredId, "Back extensions", "Back extensions are also known as hyperextensions. It's " +
                "an exercise that works the lower back and also the buttocks and hamstrings.", R.drawable.back_extensions);
        Long backExtensionsExerciseTypeStoredId = exerciseTypeRepository.store(backExtensionsExerciseTypeEntity);
        backExtensionsExerciseTypeEntity.setId(backExtensionsExerciseTypeStoredId);

        ExerciseTypeEntity cardioTrainingsExerciseTypeEntity = new ExerciseTypeEntity(legsBackProgramStoredId, "Cardio trainings", "Training for pussy-man, who doesn't want to kick " +
                "somebody's ass on street, only likes pedalling", R.drawable.cardio_trainings);
        Long cardioTrainingsExerciseTypeStoredId = exerciseTypeRepository.store(cardioTrainingsExerciseTypeEntity);
        cardioTrainingsExerciseTypeEntity.setId(cardioTrainingsExerciseTypeStoredId);

        WorkoutEntity workoutEntity = new WorkoutEntity(handsChestProgramStoredId, Calendar.getInstance().getTimeInMillis(), Calendar.getInstance().getTimeInMillis(), "FINISHED");
        Long storedFirstWorkout = workoutRepository.store(workoutEntity);
        workoutEntity.setId(storedFirstWorkout);

        ExerciseEntity firstExerciseEntity = new ExerciseEntity("M", storedFirstWorkout, lateralRaiseExerciseTypeStoredId);
        Long storedFistExerciseId = exerciseRepository.store(firstExerciseEntity);
        firstExerciseEntity.setId(storedFistExerciseId);
        attemptRepository.store(new AttemptEntity(storedFistExerciseId, "10", 15));
        attemptRepository.store(new AttemptEntity(storedFistExerciseId, "15", 13));

        ExerciseEntity secondExerciseEntity = new ExerciseEntity("L",storedFirstWorkout, cableRowsExerciseTypeStoredId);
        Long storedSecondExerciseId = exerciseRepository.store(secondExerciseEntity);
        secondExerciseEntity.setId(storedSecondExerciseId);
        attemptRepository.store(new AttemptEntity(storedSecondExerciseId, "8", 20));
        attemptRepository.store(new AttemptEntity(storedSecondExerciseId, "10", 15));
        attemptRepository.store(new AttemptEntity(storedSecondExerciseId, "15", 15));
        attemptRepository.store(new AttemptEntity(storedSecondExerciseId, "20", 8));
        attemptRepository.store(new AttemptEntity(storedSecondExerciseId, "25", 15));
        attemptRepository.store(new AttemptEntity(storedSecondExerciseId, "115", 4));

        ExerciseEntity thirdExerciseEntity = new ExerciseEntity("S", storedFirstWorkout, backExtensionsExerciseTypeStoredId);
        Long storedThirdExerciseId = exerciseRepository.store(thirdExerciseEntity);
        thirdExerciseEntity.setId(storedThirdExerciseId);
        attemptRepository.store(new AttemptEntity(storedThirdExerciseId, "10", 15));
        attemptRepository.store(new AttemptEntity(storedThirdExerciseId, "15", 13));
    }
}
