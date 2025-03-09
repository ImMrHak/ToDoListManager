package com.tdlm.domain.user.service;

import com.tdlm.domain.user.model.User;
import com.tdlm.domain.user.repository.UserDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserDomainRepository userDomainRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userDomainRepository.findByUsername(username);
    }
}
