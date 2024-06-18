package {BootGenie}.infrastructure.adapter.out;

import {BootGenie}.domain.model.User;
import {BootGenie}.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository implements UserRepositoryPort {

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
