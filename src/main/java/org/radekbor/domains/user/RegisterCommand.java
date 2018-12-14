package org.radekbor.domains.user;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class RegisterCommand {

    @Length(min = 3)
    private String userName;
    // TODO add validation
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
