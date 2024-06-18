package {BootGenie}.user.domain.model;

import {BootGenie}.user.domain.valueobject.Email;

public class User {
    private Long id;
    private String name;
    private Email email;

    public User(Long id, String name, Email email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
