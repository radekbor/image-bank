package org.radekbor.domains.user;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    private Long id;

    private String username;

    private String email;

    private String password;

    private User() {
        // For Hibernate
    }

    public User(String username, String password, String email, List<Role> roles, boolean isActive) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.active = isActive;
    }

    public static User createActiveUser(String username, String password, String email, List<Role> roles) {
        return new User(username, password, email, roles, true);
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;
    private boolean active;


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String changeEmail(String newEmail) {
        this.email = newEmail;
        return this.email;
    }
}
