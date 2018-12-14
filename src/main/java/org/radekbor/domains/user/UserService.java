package org.radekbor.domains.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserService(UserRepository repo, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    private UserRepository repo;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;
    private long userRole;
    // todo replace by migration
    @PostConstruct
    public void roles() {
        userRole = roleRepository.save(new Role("user")).getId();
    }

    public User createUser(String userName, String password) {
        String encodedPass = passwordEncoder.encode(password);
        Optional<Role> byId = roleRepository.findById(userRole);
        User activeUser = User.createActiveUser(userName, encodedPass, Collections.singletonList(byId.get()));
        return repo.save(activeUser);
    }

    // TODO check can we reove
//    public void save(User user) {
//        user.changePassword(passwordEncoder.encode(user.getPassword()));
//        repo.save(user);
//    }

}
