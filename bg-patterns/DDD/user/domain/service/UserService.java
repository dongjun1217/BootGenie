package {BootGenie}.user.domain.service;

import {BootGenie}.user.domain.model.User;
import {BootGenie}.user.domain.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
