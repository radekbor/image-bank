package org.radekbor.domains.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @GetMapping(value = "/my/name")
    public String userName(@AuthenticationPrincipal User activeUser) {
        return activeUser.getUsername();
    }

}