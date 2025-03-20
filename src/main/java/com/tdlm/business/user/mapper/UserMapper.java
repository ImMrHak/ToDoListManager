package com.tdlm.business.user.mapper;

import com.tdlm.business.user.record.request.RegisterDTO;
import com.tdlm.domain.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterDTO registerDTO);
}
