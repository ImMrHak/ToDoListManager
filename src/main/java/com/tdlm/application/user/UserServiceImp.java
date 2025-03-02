package com.tdlm.application.user;

import com.tdlm.domain.user.model.User;
import com.tdlm.domain.user.repository.UserDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserDomainRepository userDomainRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDomainRepository.findByUsername(username);
    }
}
