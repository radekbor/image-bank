package org.radekbor.domains.user.account;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class RegisterCommand {

    static final String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d#$@!%&*?]{8,20}";

    @Length(min = 3)
    private String userName;

    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

    @Email
    private final String email;

    public RegisterCommand(String userName, String email, String password) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    @JsonCreator
    public static RegisterCommand createRegisterCommand(
            @JsonProperty("userName") String userName,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        return new RegisterCommand(userName, email, password);
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
