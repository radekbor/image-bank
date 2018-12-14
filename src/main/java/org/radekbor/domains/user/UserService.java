package org.radekbor.domains.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    User createUser(String userName, String password) {
        String encodedPass = passwordEncoder.encode(password);
        Optional<Role> byId = roleRepository.findById(1L);
        User activeUser = User.createActiveUser(userName, encodedPass, Collections.singletonList(byId.get()));
        return repo.save(activeUser);
    }


}
