package org.radekbor.domains.user;

import com.drew.metadata.exif.PanasonicRawIFD0Descriptor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.radekbor.domains.user.account.CustomUserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    public void shouldCreateUser() {
        String name = "John";
        String email = "john@gmail.com";
        String password = "password";
        String passwordEncryped = "aasdasd";
        String roleUser = "USER";
        when(passwordEncoder.encode(password)).thenReturn(passwordEncryped);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(new Role(roleUser)));

        userService.createUser(name, email, password);

        verify(repo).save(userArgumentCaptor.capture());
        User savedUser = userArgumentCaptor.getValue();
        assertThat(savedUser.getPassword()).isEqualTo(passwordEncryped);
        assertThat(savedUser.getEmail()).isEqualTo(email);
        assertThat(savedUser.getUsername()).isEqualTo(name);
        assertThat(savedUser.getRolesNames()).contains(roleUser);
    }

    @Test
    @Ignore //TODO
    public void shouldChangeEmail() {
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);
//        userService.changeUserEmail()
    }
}