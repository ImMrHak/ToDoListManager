package com.tdlm.application.user.service;

import com.tdlm.application.user.record.request.LoginDTO;
import com.tdlm.application.user.record.response.TokenDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    TokenDTO authenticateUser(LoginDTO loginDTO);
}
