package {BootGenie}.command.handler;

import {BootGenie}.command.CreateUserCommand;
import {BootGenie}.domain.model.User;
import {BootGenie}.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserHandler {

    private final UserRepository userRepository;

    @Autowired
    public CreateUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handle(CreateUserCommand command) {
        User user = new User();
        user.setName(command.getName());
        user.setEmail(command.getEmail());
        userRepository.save(user);
    }
}
