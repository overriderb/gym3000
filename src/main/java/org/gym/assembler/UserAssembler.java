package org.gym.assembler;

import org.gym.domain.UserEntity;
import org.gym.model.User;

/**
 * TODO: Add comment
 */
public class UserAssembler {

    private static UserAssembler instance;

    public static UserAssembler getInstance() {
        if (instance == null) {
            instance = new UserAssembler();
        }
        return instance;
    }

    public User domainToModel(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        return user;
    }

    public UserEntity modelToDomain(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        return userEntity;
    }
}
