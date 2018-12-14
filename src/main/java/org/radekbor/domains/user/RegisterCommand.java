package org.radekbor.domains.user;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

public class RegisterCommand {

    static final String PASSWORD_PATTERN = "/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,20}$/\n";

    @Length(min = 3)
    private String userName;

    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

    public RegisterCommand(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @JsonCreator
    public static RegisterCommand createRegisterCommand(
            @JsonProperty("userName") String userName,
            @JsonProperty("password") String password) {
        return new RegisterCommand(userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
