package {BootGenie}.domain.port.input;

import {BootGenie}.domain.model.User;

public interface UserServicePort {
    User getUserById(Long id);
    void createUser(User user);
}
