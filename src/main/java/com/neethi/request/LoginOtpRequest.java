package com.neethi.request;

import com.neethi.domain.USER_ROLE;
import lombok.Data;

@Data
public class LoginOtpRequest {

    private String email;
    private USER_ROLE role;
}
