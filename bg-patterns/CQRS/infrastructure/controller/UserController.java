package {BootGenie}.infrastructure.controller;

import {BootGenie}.command.CreateUserCommand;
import {BootGenie}.command.handler.CreateUserHandler;
import {BootGenie}.domain.model.User;
import {BootGenie}.query.GetUserQuery;
import {BootGenie}.query.handler.GetUserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserHandler createUserHandler;
    private final GetUserHandler getUserHandler;

    @Autowired
    public UserController(CreateUserHandler createUserHandler, GetUserHandler getUserHandler) {
        this.createUserHandler = createUserHandler;
        this.getUserHandler = getUserHandler;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        GetUserQuery query = new GetUserQuery();
        query.setId(id);
        return getUserHandler.handle(query);
    }

    @PostMapping
    public void createUser(@RequestBody CreateUserCommand command) {
        createUserHandler.handle(command);
    }
}
