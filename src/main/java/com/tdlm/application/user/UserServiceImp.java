package com.tdlm.application.user;

import com.tdlm.adapter.exception.user.UserNotFoundException;
import com.tdlm.application.user.record.request.LoginDTO;
import com.tdlm.application.user.record.response.TokenDTO;
import com.tdlm.domain.user.model.User;
import com.tdlm.domain.user.repository.UserDomainRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserDomainRepository userDomainRepository;
    private final AuthenticationManager authenticationManager;

    public UserServiceImp(UserDomainRepository userDomainRepository, @Lazy AuthenticationManager authenticationManager) {
        this.userDomainRepository = userDomainRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenDTO authenticateUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password())
        );

        if(!authentication.isAuthenticated()) {
            throw new UserNotFoundException("User not found");
        }

        return null;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDomainRepository.findByUsername(username);
    }
}
