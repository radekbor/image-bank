package org.radekbor.domains.user;

import org.radekbor.domains.user.account.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    private CustomUserDetails getDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping(value = "/my")
    public UserInfo userName() {
        CustomUserDetails details = getDetails();
        return new UserInfo(details.getUsername(), details.getEmail(), details.getId());
    }

    static class UserInfo {
        private final long id;
        private final String name;
        private final String email;

        public UserInfo(String name, String email, long id) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public long getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }
    }


}