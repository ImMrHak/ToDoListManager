package com.tdlm.application.user.service;

import com.tdlm.adapter.exception.user.UserNotFoundException;
import com.tdlm.application.user.record.request.LoginDTO;
import com.tdlm.application.user.record.response.TokenDTO;
import com.tdlm.domain.user.repository.UserDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserDomainRepository userDomainRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenDTO authenticateUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));

        if(!authentication.isAuthenticated()) throw new UserNotFoundException("User not found");

        return null;
    }
}
