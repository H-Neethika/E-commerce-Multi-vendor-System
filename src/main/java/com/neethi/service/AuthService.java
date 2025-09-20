package com.neethi.service;

import com.neethi.response.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email) throws Exception;
    String createUser(SignupRequest req) throws Exception;
}
