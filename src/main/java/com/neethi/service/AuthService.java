package com.neethi.service;

import com.neethi.request.LoginRequest;
import com.neethi.response.AuthResponse;
import com.neethi.request.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse signin(LoginRequest req);
}
