package org.radekbor.domains.user;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User extends BaseEntity {

    private User() {
        // For Hibernate
    }

    public User(String username, String password, List<Role> roles, boolean isActive) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = isActive;
    }

    public static User createActiveUser(String username, String password, List<Role> roles) {
        return new User(username, password, roles, true);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }

    public List<String> getRolesNames() {
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }

    private String username;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;
    private boolean active;


}
