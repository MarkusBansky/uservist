package com.markiian.benovskyi.auth.persistance.mapper;

import com.markiian.benovskyi.auth.model.UserAuthentication;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserAuthenticationDto;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationMapper implements Mapper<UserAuthentication, UserAuthenticationDto> {
    @Override
    public UserAuthenticationDto toDto(UserAuthentication userAuthentication) {
        return toDto(new UserAuthenticationDto(), userAuthentication);
    }

    @Override
    public UserAuthentication toBase(UserAuthenticationDto userAuthenticationDto) {
        return toBase(new UserAuthentication(), userAuthenticationDto);
    }

    @Override
    public UserAuthenticationDto toDto(UserAuthenticationDto userAuthenticationDto, UserAuthentication userAuthentication) {
        return userAuthenticationDto
                .username(userAuthentication.getUsername())
                .key(userAuthentication.getServiceKey())
                .hardwareId(userAuthentication.getHardwareId());
    }

    @Override
    public UserAuthentication toBase(UserAuthentication userAuthentication, UserAuthenticationDto userAuthenticationDto) {
        return userAuthentication
                .withUsername(userAuthenticationDto.getUsername())
                .withServiceKey(userAuthenticationDto.getKey())
                .withHardwareId(userAuthenticationDto.getHardwareId());
    }
}
