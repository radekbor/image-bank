package org.radekbor.domains.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {

    @GetMapping(value = "/my/name")
    public String userName(Principal principal) {
        return principal.getName();
    }

}