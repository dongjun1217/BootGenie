package {BootGenie}.service;

import {BootGenie}.domain.model.User;
import {BootGenie}.domain.repository.UserRepository;
import {BootGenie}.event.UserCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(User user) {
        userRepository.save(user);
        UserCreatedEvent event = new UserCreatedEvent();
        event.setUserId(user.getId());
        event.setUserName(user.getName());
        event.setUserEmail(user.getEmail());
        eventPublisher.publishEvent(event);
    }
}
