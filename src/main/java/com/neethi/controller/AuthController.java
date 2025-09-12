package com.neethi.controller;

import com.neethi.modal.User;
import com.neethi.repository.UserRepository;
import com.neethi.response.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
