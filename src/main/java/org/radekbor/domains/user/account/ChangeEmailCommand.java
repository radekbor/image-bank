package org.radekbor.domains.user.account;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class ChangeEmailCommand {

    @Email
    private final String email;

    public ChangeEmailCommand(String email) {
        this.email = email;
    }

    @JsonCreator
    public static ChangeEmailCommand createChangeEmailCommand(
            @JsonProperty("email") String email) {
        return new ChangeEmailCommand(email);
    }

    public String getEmail() {
        return email;
    }

}
