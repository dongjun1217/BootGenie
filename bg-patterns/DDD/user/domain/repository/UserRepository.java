package {BootGenie}.user.domain.repository;

import {BootGenie}.user.domain.model.User;

public interface UserRepository {
    User findById(Long id);
    void save(User user);
}
