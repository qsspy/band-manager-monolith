package com.qsspy.bands.command.infrastucture.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.bands.command.application.creation.port.input.CreateBandCommand;
import com.qsspy.bands.command.application.creation.port.input.CreateBandCommandHandler;
import com.qsspy.bands.command.application.defaultprivileges.port.input.ChangeBandDefaultPrivilegesCommandHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bands")
@RequiredArgsConstructor
@Slf4j
class BandController {

    private final AuthInterceptor authInterceptor;
    private final CreateBandCommandHandler createBandCommandHandler;
    private final ChangeBandDefaultPrivilegesCommandHandler changeBandDefaultPrivilegesCommandHandler;

    @PostMapping
    ResponseEntity<Object> createBand(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @RequestBody final CreateBandRequestBody request
    ) {
        return authInterceptor.withUserWithoutBandAuthorization(
                token,
                context -> {
                    final var command = CreateBandCommand.builder()
                            .creatorId(context.userId())
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

    @PatchMapping("/{bandId}/privileges/default")
    ResponseEntity<Object> changeBandDefaultPrivileges(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final ChangeBandDefaultPrivilegesRequestBody request
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    final var command = RequestToCommandMapper.toCommand(bandId, request);
                    try {
                        changeBandDefaultPrivilegesCommandHandler.handle(command);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred during changing band default privileges", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }
}