package com.erickhene.jwt;

import com.erickhene.jwt.embeded.Role;
import com.erickhene.jwt.entity.User;
import com.erickhene.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitUser implements CommandLineRunner {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if (userService.findUserByEmail("admin@test.com").isEmpty()){
            User user = userService
                    .save(User.builder()
                            .username("Admin")
                            .email("admin@test.com")
                            .password(passwordEncoder.encode("test123"))
                            .role(Set.of(Role.ROLE_ADMIN))
                            .build());
            user.setEnabled(true);
            userService.save(user);
        }
    }
}
