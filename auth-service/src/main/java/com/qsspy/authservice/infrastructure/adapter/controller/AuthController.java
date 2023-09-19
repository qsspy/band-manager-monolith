package com.qsspy.authservice.infrastructure.adapter.controller;

import com.qsspy.authservice.application.login.port.input.LoginService;
import com.qsspy.authservice.application.logout.port.input.LogoutCommand;
import com.qsspy.authservice.application.logout.port.input.LogoutCommandHandler;
import com.qsspy.authservice.application.register.port.input.RegisterCommandHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
class AuthController {

    private final RegisterCommandHandler registerCommandHandler;
    private final LoginService loginService;
    private final LogoutCommandHandler logoutCommandHandler;

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody final RegisterRequestBody request) {
        final var command = RequestToCommandMapper.toCommand(request);

        try {
            registerCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.info("An error occurred during registration", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody final LoginRequestBody request) {
        try {
            final var token = loginService.login(request.email(), request.password());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (final Exception exception) {
            log.info("An error occurred during login", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/logout")
    ResponseEntity<Void> logout(@RequestHeader(name = HttpHeaders.AUTHORIZATION) final String bearerToken) {
        final var logoutCommand = new LogoutCommand(bearerToken);
        try {
            logoutCommandHandler.handle(logoutCommand);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.info("An error occurred during logout", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostConstruct
    void post() {
        System.out.println();
    }

}
