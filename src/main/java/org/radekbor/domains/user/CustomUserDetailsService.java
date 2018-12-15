/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.radekbor.domains.user;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.radekbor.domains.user.account.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    private UserRepository repo;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo
                .findByUsername(username)
                .map(this::mapUserToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("No user with the name " + username));
    }

    private CustomUserDetails mapUserToUserDetails(User u) {
        return new CustomUserDetails(
                u.getId(),
                u.getUsername(),
                u.getEmail(),
                u.getPassword(),
                u.isActive(),
                u.isActive(),
                u.isActive(),
                u.isActive(),
                AuthorityUtils.createAuthorityList(
                        u.getRolesNames()
                                .stream()
                                .map(r -> "ROLE_" + r.toUpperCase())
                                .collect(Collectors.toList())
                                .toArray(new String[]{})));
    }

}
