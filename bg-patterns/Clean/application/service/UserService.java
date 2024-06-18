package {BootGenie}.application.service;

import {BootGenie}.domain.model.User;
import {BootGenie}.usecase.CreateUser;
import {BootGenie}.usecase.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final GetUser getUser;
    private final CreateUser createUser;

    @Autowired
    public UserService(GetUser getUser, CreateUser createUser) {
        this.getUser = getUser;
        this.createUser = createUser;
    }

    public User getUserById(Long id) {
        return getUser.execute(id);
    }

    public void createUser(User user) {
        createUser.execute(user);
    }
}
