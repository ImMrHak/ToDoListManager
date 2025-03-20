package com.tdlm.business.user.service;

import com.tdlm.business.user.mapper.UserMapper;
import com.tdlm.business.user.record.request.LoginDTO;
import com.tdlm.business.user.record.request.RegisterDTO;
import com.tdlm.business.user.record.response.TokenDTO;
import com.tdlm.kernel.exception.user.UserAlreadyExistException;
import com.tdlm.kernel.exception.user.UserNotFoundException;
import com.tdlm.kernel.security.jwt.JwtService;
import com.tdlm.domain.user.model.User;
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
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Override
    public TokenDTO authenticateUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));

        if(!authentication.isAuthenticated()) throw new UserNotFoundException("User not found");

        User dbUser = userDomainRepository.findByUsername(loginDTO.username());

        return new TokenDTO(jwtService.generateAccessToken(dbUser), jwtService.generateRefreshToken(dbUser));
    }

    @Override
    public TokenDTO registerUser(RegisterDTO registerDTO) {
        User newUser = userDomainRepository.findByUsername(registerDTO.username());

        if(newUser != null) throw new UserAlreadyExistException("User already exist");

        newUser = userMapper.toUser(registerDTO);

        userDomainRepository.save(newUser);

        return new TokenDTO(jwtService.generateAccessToken(newUser), jwtService.generateRefreshToken(newUser));
    }
}
