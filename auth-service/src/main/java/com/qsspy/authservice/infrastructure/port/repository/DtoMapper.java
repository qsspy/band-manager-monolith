package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DtoMapper {

    static UserContext toUserContext(final UserDataDto dataDto, @Nullable final UserBandPrivilegesDto privilegesDto) {
        return UserContext.builder()
                .userId(dataDto.userId())
                .email(dataDto.email())
                .firstName(dataDto.firstName())
                .lastName(dataDto.lastName())
                .userMemberBandId(dataDto.userMemberBandId())
                .userOwnBandId(dataDto.userOwnBandId())
                .bandPrivileges(
                        privilegesDto == null ? null :
                        UserContext.Privileges.builder()
                                .canAddFinanceEntries(privilegesDto.canAddFinanceEntries())
                                .build()
                )
                .build();
    }
}
