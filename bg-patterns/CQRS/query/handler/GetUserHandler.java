package {BootGenie}.query.handler;

import {BootGenie}.domain.model.User;
import {BootGenie}.domain.repository.UserRepository;
import {BootGenie}.query.GetUserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserHandler {

    private final UserRepository userRepository;

    @Autowired
    public GetUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handle(GetUserQuery query) {
        return userRepository.findById(query.getId());
    }
}
