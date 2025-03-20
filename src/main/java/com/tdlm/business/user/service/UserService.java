package com.tdlm.business.user.service;

import com.tdlm.business.user.record.request.LoginDTO;
import com.tdlm.business.user.record.request.RegisterDTO;
import com.tdlm.business.user.record.response.TokenDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    TokenDTO authenticateUser(LoginDTO loginDTO);
    TokenDTO registerUser(RegisterDTO registerDTO);
}
