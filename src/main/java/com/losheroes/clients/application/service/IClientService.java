package com.losheroes.clients.application.service;

import com.losheroes.clients.application.dto.request.ClientCreateRequestDTO;
import com.losheroes.clients.application.dto.request.ClientLoginRequestDTO;
import com.losheroes.clients.application.dto.request.ClientUpdateRequestDTO;
import com.losheroes.clients.application.dto.response.ClientFullDTO;
import com.losheroes.clients.application.dto.response.ClientLoginResponseDTO;
import com.losheroes.clients.application.exception.ResourceBadRequestException;
import com.losheroes.clients.application.exception.ResourceInternalErrorException;

public interface IClientService {
    ClientFullDTO createClient(ClientCreateRequestDTO clientCreateRequestDTO) throws ResourceBadRequestException, ResourceInternalErrorException;
    ClientLoginResponseDTO loginClient(ClientLoginRequestDTO clientLoginRequestDTO) throws ResourceBadRequestException;
    ClientFullDTO getClient(String clientRut) throws ResourceBadRequestException;
    String updateClient(String clientRut, ClientUpdateRequestDTO clientUpdateRequestDTO) throws ResourceBadRequestException, ResourceInternalErrorException;

    String deleteClient(String clientRut) throws ResourceBadRequestException, ResourceInternalErrorException;
}
