package com.qsspy.bands.command.infrastucture.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.bands.command.application.creation.port.input.CreateBandCommand;
import com.qsspy.bands.command.application.creation.port.input.CreateBandCommandHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bands")
@RequiredArgsConstructor
@Slf4j
class BandController {

    private final AuthInterceptor authInterceptor;
    private final CreateBandCommandHandler createBandCommandHandler;

    @PostMapping
    ResponseEntity<Object> createBand(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @RequestBody final CreateBandRequestBody request
    ) {
        return authInterceptor.withAuthorization(
                token,
                context -> {
                    final var command = CreateBandCommand.builder()
                            .creatorId(context.userId())
                            .userMemberBandId(context.userMemberBandId())
                            .userOwnBandId(context.userOwnBandId())
                            .bandName(request.name())
                            .build();

                    try {
                        createBandCommandHandler.handle(command);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred during creating band", exception);
                        return ResponseEntity.internalServerError().build();
                    }

                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

}
