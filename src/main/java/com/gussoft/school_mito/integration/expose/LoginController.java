package com.gussoft.school_mito.integration.expose;

import com.gussoft.school_mito.core.business.UserService;
import com.gussoft.school_mito.core.models.Registration;
import com.gussoft.school_mito.core.models.User;
import com.gussoft.school_mito.core.security.JwtUtil;
import com.gussoft.school_mito.integration.transfer.request.AuthRequest;
import com.gussoft.school_mito.integration.transfer.request.RegistrationRequest;
import com.gussoft.school_mito.integration.transfer.request.UserRequest;
import com.gussoft.school_mito.integration.transfer.response.AuthResponse;
import java.util.Date;

import com.gussoft.school_mito.integration.transfer.response.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final JwtUtil jwtUtil;
    private final UserService service;
    private final ModelMapper mapper;

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest request) {
        return service.searchByUser(request.getUsername())
                .map(user -> {
                    if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
                        String token = jwtUtil.generateToken(user);
                        Date expiration = jwtUtil.getExpirationToken(token);
                        return ResponseEntity.ok(new AuthResponse(token, expiration));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    }
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<?>> register(@RequestBody UserRequest request) {
        return service.saveHash(entityToRequest(request))
                .map(ResponseEntity::ok);
    }

    private User entityToRequest(UserRequest obj) {
        return mapper.map(obj, User.class);
    }

}
