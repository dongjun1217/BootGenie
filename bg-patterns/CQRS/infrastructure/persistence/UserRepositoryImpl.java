package {BootGenie}.infrastructure.persistence;

import {BootGenie}.domain.model.User;
import {BootGenie}.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private Map<Long, User> database = new HashMap<>();

    @Override
    public User findById(Long id) {
        return database.get(id);
    }

    @Override
    public void save(User user) {
        database.put(user.getId(), user);
    }
}
