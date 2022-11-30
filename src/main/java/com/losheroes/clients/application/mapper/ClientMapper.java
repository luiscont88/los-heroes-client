package com.losheroes.clients.application.mapper;

import com.losheroes.clients.application.dto.response.ClientFullDTO;
import com.losheroes.clients.application.model.ClientModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientFullDTO entityToDto(ClientModel entity);

    ClientModel dtoToEntity(ClientFullDTO dto);

}
