package {BootGenie}.user.application;

import {BootGenie}.user.domain.model.User;
import {BootGenie}.user.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {
    private final UserService userService;

    @Autowired
    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    public void createUser(User user) {
        userService.createUser(user);
    }
}
