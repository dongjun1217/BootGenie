package {BootGenie}.application.service;

import {BootGenie}.domain.model.User;
import {BootGenie}.domain.port.in.UserServicePort;
import {BootGenie}.domain.port.out.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServicePort {

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Override
    public User getUserById(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public void createUser(User user) {
        userRepositoryPort.save(user);
    }
}
