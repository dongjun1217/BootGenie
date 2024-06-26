package {BootGenie}.user.ui;

import {BootGenie}.user.application.UserFacade;
import {BootGenie}.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userFacade.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userFacade.createUser(user);
    }
}
