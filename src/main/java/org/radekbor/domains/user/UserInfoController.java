package org.radekbor.domains.user;

import org.radekbor.domains.user.account.CustomUserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {

    @GetMapping(value = "/my")
    public UserInfo userName(Principal principal) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        CustomUserDetails details = (CustomUserDetails) oAuth2Authentication.getUserAuthentication().getPrincipal();
        return new UserInfo(oAuth2Authentication.getName(), details.getEmail(), details.getId());
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