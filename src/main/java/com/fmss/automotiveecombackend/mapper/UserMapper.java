package com.fmss.automotiveecombackend.mapper;

import com.fmss.automotiveecombackend.data.dbmodel.User;
import com.fmss.automotiveecombackend.data.dto.request.CreateUserPayload;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(CreateUserPayload createUserPayload);

    CreateUserPayload toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(CreateUserPayload createUserPayload, @MappingTarget User user);
}