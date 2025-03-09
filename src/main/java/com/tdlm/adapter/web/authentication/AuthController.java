package com.tdlm.adapter.web.authentication;

import com.tdlm.adapter.wrapper.ResponseWrapper;
import com.tdlm.application.user.record.request.LoginDTO;
import com.tdlm.application.user.record.request.RegisterDTO;
import com.tdlm.application.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.custom(userService.registerUser(registerDTO), "User Created Successfully", HttpStatus.CREATED));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO){
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.custom(userService.authenticateUser(loginDTO), "User Authenticated Successfully", HttpStatus.OK));
    }
}
