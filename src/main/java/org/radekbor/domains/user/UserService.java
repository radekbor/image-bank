package org.radekbor.domains.user;

import org.radekbor.domains.user.account.ChangeEmailCommand;
import org.radekbor.domains.user.account.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public User createUser(String userName, String email, String password) {
        String encodedPass = passwordEncoder.encode(password);
        List<Role> roles = roleRepository.findById(1L).map(Collections::singletonList).orElseGet(ArrayList::new);
        User activeUser = User.createActiveUser(userName, encodedPass, email, roles);
        return repo.save(activeUser);
    }


    public String changeUserEmail(CustomUserDetails userDetails, String newEmail) {
        User user = repo.getOne(userDetails.getId());
        String changeEmail = user.changeEmail(newEmail);
        repo.save(user);
        return changeEmail;
    }
}
