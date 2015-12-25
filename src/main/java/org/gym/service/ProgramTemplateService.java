package org.gym.service;

/**
 * Created by anni0913 on 25.12.2015.
 */
public class ProgramTemplateService {

    private static ProgramTemplateService instance;

    public static ProgramTemplateService getInstance() {
        if (instance == null) {
            instance = new ProgramTemplateService();
        }
        return instance;
    }

}
