package org.gym.repository;

import org.gym.activity.R;
import org.gym.domain.ExerciseTypeEntity;
import org.gym.domain.ProgramEntity;
import org.gym.domain.UserEntity;

/**
 * Class contains initialize data for first start application and functional testing
 */
public class InitializeUtils {

    private static UserRepository userRepository = UserRepository.getInstance();
    private static ProgramRepository programRepository = ProgramRepository.getInstance();
    private static ExerciseTypeRepository exerciseTypeRepository = ExerciseTypeRepository.getInstance();

    /**
     * Method sets to DB programs and workouts, which user will see after starting this application first time
     */
    public static void initTestPrograms() {

        UserEntity user = new UserEntity("Andrew");
        Long userStoredId = userRepository.store(user);
        user.setId(userStoredId);

        initFirstHandsChestProgram();
        initFirstLegsBackProgram();
        initSecondHandsChestProgram();
        initSecondLegsBackProgram();
    }

    // #1 First hands and chest program
    private static void initFirstHandsChestProgram() {
        ProgramEntity handsChestFirstProgramEntity = new ProgramEntity("1. Руки/грудь", "Тренировка групп мышц груди и рук", 1);
        Long handsChestFirstProgramStoredId = programRepository.store(handsChestFirstProgramEntity);
        handsChestFirstProgramEntity.setId(handsChestFirstProgramStoredId);

        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestFirstProgramStoredId, "Разминка", "Согревающие/разминающие упражнения:\n" +
                "-Растяжка/разминка\n" +
                "-Бег/велотренажер", R.drawable.warm_up, 1));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestFirstProgramStoredId, "Жим лежа, 90", "Жим лежа на лавке под углом 90", R.drawable.lateral_raise, 1));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestFirstProgramStoredId, "Разводы гантелей сидя, 45", "Развод гантелей сидя на лавке под углом 45", R.drawable.lat_pulldown, 3));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestFirstProgramStoredId, "Брусья","Отжимания от брусьев, можно с отягощением", R.drawable.cable_rows, 4));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestFirstProgramStoredId, "Французский жим", "Французский жим лежа на лавке", R.drawable.cable_rows, 5));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestFirstProgramStoredId, "Кроссовер, вниз, металл. ручка", "Трицепс на кроссовере с металлической ручкой", R.drawable.lat_pulldown, 6));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestFirstProgramStoredId, "Молотки","Поочередное поднимание гантелей к плечам на бицепс", R.drawable.cable_rows, 7));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestFirstProgramStoredId, "Штанга обратным хватом", "Поднимание штанги к плечам на бицепс обратным хватом", R.drawable.cable_rows, 8));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestFirstProgramStoredId, "Пресс", "Пресс на римском стуле", R.drawable.lat_pulldown, 9));
    }

    // #2 First legs and back program
    private static void initFirstLegsBackProgram() {
        ProgramEntity legsBackFirstProgramEntity = new ProgramEntity("2. Ноги/спина", "Тренировка групп мышц спины и ног", 2);
        Long legsBackFirstProgramStoredId = programRepository.store(legsBackFirstProgramEntity);
        legsBackFirstProgramEntity.setId(legsBackFirstProgramStoredId);

        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackFirstProgramStoredId, "Разминка", "Согревающие/разминающие упражнения:\n" +
                "-Растяжка/разминка\n" +
                "-Бег/велотренажер\n" +
                "-Гиперэкстензия\n" +
                "-Разминка ног на тренажерах", R.drawable.warm_up, 1));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackFirstProgramStoredId, "Приседания",
                "Приседания со штангой", R.drawable.crunch, 2));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackFirstProgramStoredId, "Армейский жим",
                "Поднимания штанги стоя/сидя над головой с груди. При выполнении смотреть строго перед собой, " +
                        "руки шире плеч", R.drawable.back_extensions, 3));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackFirstProgramStoredId, "Подьем гантелей",
                "Попеременный подьем гантелей перед собой на вытянутых руках. Подымать по широкой дуге, " +
                        "одна рука опускается, другая - поднимается, расходясь у лица", R.drawable.cardio_trainings, 4));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackFirstProgramStoredId, "Тяга к подбородку",
                "Тяга штанги к подбородку узким хватом", R.drawable.crunch, 5));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackFirstProgramStoredId, "Спина",
                "Упражнения на спину, которые я пока не придумал", R.drawable.crunch, 6));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackFirstProgramStoredId, "Пресс",
                "Пресс на сжигание", R.drawable.crunch, 7));
    }

    // #3 Second hands and chest program
    private static void initSecondHandsChestProgram() {
        ProgramEntity handsChestSecondProgramEntity = new ProgramEntity("3. Руки/грудь", "Тренировка групп мышц груди и рук", 3);
        Long handsChestSecondProgramStoredId = programRepository.store(handsChestSecondProgramEntity);
        handsChestSecondProgramEntity.setId(handsChestSecondProgramStoredId);

        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestSecondProgramStoredId, "Разминка", "Согревающие/разминающие упражнения:\n" +
                "-Растяжка/разминка\n" +
                "-Бег/велотренажер", R.drawable.warm_up, 1));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestSecondProgramStoredId, "Жим лежа, обратный угол", "Жим лежа на лавке под обратным углом", R.drawable.lateral_raise, 2));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestSecondProgramStoredId, "Бабочка", "Разведение вытянутых рукна тренажере", R.drawable.lat_pulldown, 3));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestSecondProgramStoredId, "Жим узким хватом, 90","Жим лежа на лавке под 90, узкий хват", R.drawable.cable_rows, 4));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestSecondProgramStoredId, "Кроссовер, вверх, веревка", "Трицепс на кроссовере с веревкой. Тяга троса из-за голвы вверх", R.drawable.cable_rows, 5));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestSecondProgramStoredId, "Кроссовер, вниз, веревка", "Трицепс на кроссовере с веревкой", R.drawable.lat_pulldown, 6));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestSecondProgramStoredId, "Штанга прямым хватом","Поднимание штанги к плечам на бицепс прямым хватом", R.drawable.cable_rows, 7));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestSecondProgramStoredId, "Гантель через колено", "Поднимание гантели к плечу через колено, выполняется сидя", R.drawable.cable_rows, 8));
        exerciseTypeRepository.store(new ExerciseTypeEntity(handsChestSecondProgramStoredId, "Пресс", "Пресс на римском стуле", R.drawable.lat_pulldown, 9));
    }

    // #4 Second legs and back program
    private static void initSecondLegsBackProgram() {
        ProgramEntity legsBackSecondProgramEntity = new ProgramEntity("4. Ноги/спина", "Тренировка групп мышц спины и ног", 4);
        Long legsBackSecondProgramStoredId = programRepository.store(legsBackSecondProgramEntity);
        legsBackSecondProgramEntity.setId(legsBackSecondProgramStoredId);

        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackSecondProgramStoredId, "Разминка", "Согревающие/разминающие упражнения:\n" +
                "-Растяжка/разминка\n" +
                "-Бег/велотренажер\n" +
                "-Гиперэкстензия\n" +
                "-Разминка ног на тренажерах", R.drawable.warm_up, 1));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackSecondProgramStoredId, "Становая тяга",
                "Тяга штанги", R.drawable.crunch, 2));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackSecondProgramStoredId, "Жим штанги из-за головы",
                "Выполняется стоя/сидя, голова прямо, не наклонять ее вперед, слегка прогнуться в пояснице", R.drawable.back_extensions, 3));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackSecondProgramStoredId, "Разведение гантелей",
                "Одновременное разведение гантелей стоя. Выполнение: не раскачиваться, в верхней точке разварачивать " +
                        "кисти рук внутрь, чтобы задняя часть гантели была выше передней", R.drawable.cardio_trainings, 4));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackSecondProgramStoredId, "Шраги",
                "Шраги гантелями/штангой", R.drawable.crunch, 5));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackSecondProgramStoredId, "Спина",
                "Упражнения на спину, которые я пока не придумал", R.drawable.crunch, 6));
        exerciseTypeRepository.store(new ExerciseTypeEntity(legsBackSecondProgramStoredId, "Пресс",
                "Пресс на сжигание", R.drawable.crunch, 7));
    }
}
