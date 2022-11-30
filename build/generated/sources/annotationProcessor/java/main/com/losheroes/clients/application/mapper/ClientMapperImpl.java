package com.losheroes.clients.application.mapper;

import com.losheroes.clients.application.dto.response.ClientFullDTO;
import com.losheroes.clients.application.model.ClientModel;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-30T14:30:58-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientFullDTO entityToDto(ClientModel entity) {
        if ( entity == null ) {
            return null;
        }

        ClientFullDTO clientFullDTO = new ClientFullDTO();

        clientFullDTO.setId( entity.getId() );
        clientFullDTO.setClientName( entity.getClientName() );
        clientFullDTO.setClientLastname( entity.getClientLastname() );
        clientFullDTO.setClientAge( entity.getClientAge() );
        clientFullDTO.setClientPhone( entity.getClientPhone() );
        clientFullDTO.setClientEmail( entity.getClientEmail() );
        clientFullDTO.setClientPassword( entity.getClientPassword() );
        clientFullDTO.setClientRut( entity.getClientRut() );
        clientFullDTO.setActive( entity.getActive() );
        clientFullDTO.setCreationDate( entity.getCreationDate() );
        clientFullDTO.setUpdateTime( entity.getUpdateTime() );

        return clientFullDTO;
    }

    @Override
    public ClientModel dtoToEntity(ClientFullDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ClientModel clientModel = new ClientModel();

        clientModel.setId( dto.getId() );
        clientModel.setClientName( dto.getClientName() );
        clientModel.setClientLastname( dto.getClientLastname() );
        clientModel.setClientAge( dto.getClientAge() );
        clientModel.setClientPhone( dto.getClientPhone() );
        clientModel.setClientEmail( dto.getClientEmail() );
        clientModel.setClientPassword( dto.getClientPassword() );
        clientModel.setClientRut( dto.getClientRut() );
        clientModel.setActive( dto.getActive() );
        clientModel.setCreationDate( dto.getCreationDate() );
        clientModel.setUpdateTime( dto.getUpdateTime() );

        return clientModel;
    }
}
