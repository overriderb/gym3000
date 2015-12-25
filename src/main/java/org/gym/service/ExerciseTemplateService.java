package org.gym.service;

import org.gym.model.ProgramTemplate;

import java.util.List;

/**
 * Created by anni0913 on 25.12.2015.
 */
public class ExerciseTemplateService {

    private static ExerciseTemplateService instance;

    public static ExerciseTemplateService getInstance() {
        if (instance == null) {
            instance = new ExerciseTemplateService();
        }
        return instance;
    }

   /* public Long save(ProgramTemplate);

    public List<ProgramTemplate> getAll();

    public ProgramTemplate getProgramTemplate(Long id);*/
}
