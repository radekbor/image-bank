package org.radekbor.domains.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationController(UserService service) {
        this.service = service;
    }

    private UserService service;

    @PostMapping("/register")
    public Long register(@RequestBody RegisterCommand registerCommand) {
        User user = service.createUser(registerCommand.getUserName(), registerCommand.getPassword());
        return user.getId();
    }
}
