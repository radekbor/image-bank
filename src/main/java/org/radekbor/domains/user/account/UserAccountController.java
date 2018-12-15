package org.radekbor.domains.user.account;

import org.radekbor.domains.user.User;
import org.radekbor.domains.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserAccountController {


    @Autowired
    UserAccountController(UserService service) {
        this.service = service;
    }

    private UserService service;

    @PostMapping("/register")
    public Long register(@RequestBody RegisterCommand registerCommand) {
        User user = service.createUser(registerCommand.getUserName(), registerCommand.getEmail(), registerCommand.getPassword());
        return user.getId();
    }

    @PutMapping("/email/change")
    public String changeEmail(@RequestBody ChangeEmailCommand newEmail) {
        CustomUserDetails details = getDetails();
        return service.changeUserEmail(details, newEmail.getEmail());
    }

    private CustomUserDetails getDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
