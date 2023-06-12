package com.fmss.automotiveecombackend.mapper;

import com.fmss.automotiveecombackend.data.dbmodel.Address;
import com.fmss.automotiveecombackend.data.dto.request.AddressRequestPayload;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    Address toEntity(AddressRequestPayload addressRequestPayload);

    AddressRequestPayload toDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressRequestPayload addressRequestPayload, @MappingTarget Address address);
}