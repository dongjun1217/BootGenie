package {BootGenie}.domain.port.in;

import {BootGenie}.domain.model.User;

public interface UserServicePort {
    User getUserById(Long id);
    void createUser(User user);
}
