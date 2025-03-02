package com.tdlm.application.user;

import com.tdlm.domain.user.model.User;
import com.tdlm.domain.user.repository.UserDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserDomainRepository userDomainRepository;

    private AuthenticationManager authenticationManager;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDomainRepository.findByUsername(username);
    }

    @Override
    public Object authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        // NEED TO BE IMPROVED NOT RETURN TYPE OBJECT if(!authentication.isAuthenticated()) return "Wrong username or password";

        return null;
    }
}
