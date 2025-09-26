package com.neethi.service;

import com.neethi.domain.USER_ROLE;
import com.neethi.request.LoginRequest;
import com.neethi.response.AuthResponse;
import com.neethi.request.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse signin(LoginRequest req);
}
