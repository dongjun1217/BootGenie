package {BootGenie}.infrastructure.adapter.in;

import {BootGenie}.domain.model.User;
import {BootGenie}.domain.port.in.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServicePort userServicePort;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userServicePort.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userServicePort.createUser(user);
    }
}
