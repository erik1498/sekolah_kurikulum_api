package com.erickhene.jwt.service;

import com.erickhene.jwt.entity.User;
import com.erickhene.jwt.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User save(User user){
        return userRepository.save(user);
    }
    public Optional<User> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("user not found by email"));
    }
    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found by username"));
    }
}
