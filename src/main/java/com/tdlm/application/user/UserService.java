package com.tdlm.application.user;

import com.tdlm.application.user.record.request.LoginDTO;
import com.tdlm.application.user.record.response.TokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    TokenDTO authenticateUser(LoginDTO loginDTO);
}
