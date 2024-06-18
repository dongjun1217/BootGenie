package {BootGenie}.domain.repository;

import {BootGenie}.domain.model.User;

public interface UserRepository {
    User findById(Long id);
    void save(User user);
}
