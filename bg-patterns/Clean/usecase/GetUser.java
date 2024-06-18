package {BootGenie}.usecase;

import {BootGenie}.domain.model.User;
import {BootGenie}.domain.repository.UserRepository;

public class GetUser {

    private final UserRepository userRepository;

    public GetUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id) {
        return userRepository.findById(id);
    }
}
