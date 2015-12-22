package org.gym.domain;

/**
 * Created by anni0913 on 22.12.2015.
 */
public class ExerciseTemplateListEntity {

    public static final String TABLE_NAME = "EXERCISE_TEMPLATE_LIST";

    private Long id;
    private Long program_template_id;
    private Long exercise_template_id;

    public enum Column {
        ID,
        PROGRAM_TEMPLATE_ID,
        EXERCISE_TEMPLATE_ID
    }

    public ExerciseTemplateListEntity() {
    }

    public Long getProgram_template_id() {
        return program_template_id;
    }

    public void setProgram_template_id(Long program_template_id) {
        this.program_template_id = program_template_id;
    }

    public Long getExercise_template_id() {
        return exercise_template_id;
    }

    public void setExercise_template_id(Long exercise_template_id) {
        this.exercise_template_id = exercise_template_id;
    }

    public Long getId() {
        return id;
    }
}
