package org.radekbor.domains.user.account;

import org.junit.Test;
import org.radekbor.domains.user.account.RegisterCommand;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterCommandTest {

    @Test
    public void tooShortPasswordShouldBeInvalid() {

        boolean matches = "x".matches(RegisterCommand.PASSWORD_PATTERN);

        assertThat(matches).isFalse();

    }

    @Test
    public void withoutLoweCharsPasswordShouldBeInvalid() {

        boolean matches = "13!ABCDEF".matches(RegisterCommand.PASSWORD_PATTERN);

        assertThat(matches).isFalse();

    }


    @Test
    public void withoutUpperCharsPasswordShouldBeInvalid() {

        boolean matches = "13!abcdefg".matches(RegisterCommand.PASSWORD_PATTERN);

        assertThat(matches).isFalse();

    }

    @Test
    public void withoutSpecialCharsPasswordShouldBeInvalid() {

        boolean matches = "134Abcdefg".matches(RegisterCommand.PASSWORD_PATTERN);

        assertThat(matches).isFalse();

    }

    @Test
    public void withoutNumbersPasswordShouldBeInvalid() {

        boolean matches = "!!!@Abcdefg".matches(RegisterCommand.PASSWORD_PATTERN);

        assertThat(matches).isFalse();

    }

    @Test
    public void shouldPassWhenProperPassword() {

        boolean matches = "13!Aabcdefg".matches(RegisterCommand.PASSWORD_PATTERN);

        assertThat(matches).isTrue();

    }
}