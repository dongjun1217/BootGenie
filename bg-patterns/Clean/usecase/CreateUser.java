package {BootGenie}.usecase;

import {BootGenie}.domain.model.User;
import {BootGenie}.domain.repository.UserRepository;

public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user) {
        userRepository.save(user);
    }
}
