package com.neethi.service;

import com.neethi.response.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest req) throws Exception;
}
