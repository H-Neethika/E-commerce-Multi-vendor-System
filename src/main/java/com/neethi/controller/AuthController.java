package com.neethi.controller;

import com.neethi.domain.USER_ROLE;
import com.neethi.modal.VerificationCode;
import com.neethi.repository.UserRepository;
import com.neethi.request.LoginOtpRequest;
import com.neethi.request.LoginRequest;
import com.neethi.response.ApiResponse;
import com.neethi.response.AuthResponse;
import com.neethi.request.SignupRequest;
import com.neethi.service.AuthService;
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
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {
        String jwt = authService.createUser(req);
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("Register Success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest req) throws Exception {
        AuthResponse authResponse = authService.signin(req);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sendOtpHandle(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sentLoginOtp(req.getEmail(), req.getRole());
        ApiResponse res = new ApiResponse();
        res.setMessage("otp sent successfully");
        return ResponseEntity.ok(res);
    }
}
