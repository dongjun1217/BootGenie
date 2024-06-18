package {BootGenie}.domain.port.output;

import {BootGenie}.domain.model.User;

public interface UserRepositoryPort {
    User findById(Long id);
    void save(User user);
}
