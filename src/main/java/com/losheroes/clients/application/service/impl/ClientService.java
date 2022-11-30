package com.losheroes.clients.application.service.impl;

import com.losheroes.clients.application.dto.request.ClientCreateRequestDTO;
import com.losheroes.clients.application.dto.request.ClientLoginRequestDTO;
import com.losheroes.clients.application.dto.request.ClientUpdateRequestDTO;
import com.losheroes.clients.application.dto.response.ClientFullDTO;
import com.losheroes.clients.application.dto.response.ClientLoginResponseDTO;
import com.losheroes.clients.application.exception.ResourceBadRequestException;
import com.losheroes.clients.application.exception.ResourceInternalErrorException;
import com.losheroes.clients.application.mapper.ClientMapper;
import com.losheroes.clients.application.model.ClientModel;
import com.losheroes.clients.application.repository.ClientRepository;
import com.losheroes.clients.application.service.IClientService;
import com.losheroes.clients.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public ClientFullDTO createClient(ClientCreateRequestDTO clientCreateRequestDTO) throws ResourceBadRequestException, ResourceInternalErrorException {
        ClientFullDTO clientFullDTO = validateUser(clientCreateRequestDTO.getClientEmail(), clientCreateRequestDTO.getClientRut());
        if (Objects.nonNull(clientFullDTO)) {
            throw new ResourceBadRequestException("El cliente con email " + clientCreateRequestDTO.getClientEmail() + " o rut " +
                    clientCreateRequestDTO.getClientRut() + " ya se encuentra registrado en la plataforma de gestión de clientes.");
        } else {
            clientFullDTO = new ClientFullDTO();
            clientFullDTO.setClientEmail(clientCreateRequestDTO.getClientEmail());
            clientFullDTO.setClientAge(clientCreateRequestDTO.getClientAge());
            clientFullDTO.setClientPhone(clientCreateRequestDTO.getClientPhone());
            clientFullDTO.setClientName(clientCreateRequestDTO.getClientName());
            clientFullDTO.setClientLastname(clientCreateRequestDTO.getClientLastname());
            clientFullDTO.setClientRut(clientCreateRequestDTO.getClientRut());
            clientFullDTO.setClientPassword(passwordEncoder.encode(clientCreateRequestDTO.getClientPassword()));
            clientFullDTO.setCreationDate(LocalDateTime.now());
            clientFullDTO.setUpdateTime(LocalDateTime.now());
            clientFullDTO.setActive(true);
            try {
                ClientModel clientModel = ClientMapper.INSTANCE.dtoToEntity(clientFullDTO);
                clientRepository.save(clientModel);
                return clientFullDTO;
            } catch (Exception e) {
                throw new ResourceInternalErrorException("Error al guardar cliente -> " + e.getMessage());
            }
        }
    }

    @Override
    public ClientLoginResponseDTO loginClient(ClientLoginRequestDTO clientLoginRequestDTO) throws ResourceBadRequestException {
        ClientFullDTO clientFullDTO = getClientByEmail(clientLoginRequestDTO.getClientEmail());
        if (Objects.isNull(clientFullDTO)) {
            throw new ResourceBadRequestException("El cliente con email " + clientLoginRequestDTO.getClientEmail() + " y rut " +
                    clientLoginRequestDTO.getClientRut() + " no se encuentra registrado en la plataforma de gestión de clientes.");
        } else {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(clientLoginRequestDTO.getClientEmail(), clientLoginRequestDTO.getClientPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            ClientLoginResponseDTO clientLoginResponseDTO = new ClientLoginResponseDTO();
            clientLoginResponseDTO.setClientEmail(userDetails.getUsername());
            clientLoginResponseDTO.setClientName(clientFullDTO.getClientName());
            clientLoginResponseDTO.setClientLastname(clientFullDTO.getClientLastname());
            clientLoginResponseDTO.setClientRut(clientFullDTO.getClientRut());
            clientLoginResponseDTO.setToken(jwt);
            clientLoginResponseDTO.setAuthorities(userDetails.getAuthorities());
            clientLoginResponseDTO.setId(clientFullDTO.getId());
            return clientLoginResponseDTO;
        }
    }

    @Override
    public ClientFullDTO getClient(String clientRut) throws ResourceBadRequestException {
        ClientFullDTO clientFullDTO = getByClientRut(clientRut);
        if (Objects.isNull(clientFullDTO)) {
            throw new ResourceBadRequestException("El cliente con rut " + clientRut + " no se encuentra registrado en la plataforma de gestión de clientes.");
        }
        return clientFullDTO;
    }

    @Override
    public String updateClient(String clientRut, ClientUpdateRequestDTO clientUpdateRequestDTO) throws ResourceBadRequestException, ResourceInternalErrorException {
        ClientFullDTO clientFullDTO = getByClientRut(clientRut);
        if (Objects.isNull(getByClientRut(clientRut))) {
            throw new ResourceBadRequestException("El cliente con rut " + clientRut + " no se encuentra registrado en la plataforma de gestión de clientes.");
        } else if (Objects.nonNull(getClientByEmail(clientUpdateRequestDTO.getClientEmail()))) {
            throw new ResourceBadRequestException("El cliente con email " + clientUpdateRequestDTO.getClientEmail() + " ya se encuentra registrado en la plataforma de gestión de clientes, por favor intenta con otro.");
        } else {
            clientFullDTO.setClientEmail(clientUpdateRequestDTO.getClientEmail());
            clientFullDTO.setClientAge(clientUpdateRequestDTO.getClientAge());
            clientFullDTO.setClientPhone(clientUpdateRequestDTO.getClientPhone());
            clientFullDTO.setClientName(clientUpdateRequestDTO.getClientName());
            clientFullDTO.setClientLastname(clientUpdateRequestDTO.getClientLastname());
            clientFullDTO.setClientPassword(passwordEncoder.encode(clientUpdateRequestDTO.getClientPassword()));
            clientFullDTO.setCreationDate(LocalDateTime.now());
            clientFullDTO.setUpdateTime(LocalDateTime.now());
            clientFullDTO.setActive(true);
            try {
                ClientModel clientModel = ClientMapper.INSTANCE.dtoToEntity(clientFullDTO);
                clientRepository.save(clientModel);
                return "Cliente con rut "+ clientRut + " actualizado exitosamente.";
            } catch (Exception e) {
                throw new ResourceInternalErrorException("Error al guardar cliente -> " + e.getMessage());
            }
        }
    }

    @Override
    public String deleteClient(String clientRut) throws ResourceBadRequestException, ResourceInternalErrorException {
        ClientFullDTO clientFullDTO = getByClientRut(clientRut);
        if (Objects.isNull(getByClientRut(clientRut))) {
            throw new ResourceBadRequestException("El cliente con rut " + clientRut + " no se encuentra registrado en la plataforma de gestión de clientes.");
        } else {
            try {
                ClientModel clientModel = ClientMapper.INSTANCE.dtoToEntity(clientFullDTO);
                clientRepository.delete(clientModel);
                return "Cliente con rut "+ clientRut + " eliminado exitosamente.";
            } catch (Exception e) {
                throw new ResourceInternalErrorException("Error al guardar cliente -> " + e.getMessage());
            }
        }
    }

    private ClientFullDTO getClientByEmail(String clientEmail) {
        return ClientMapper.INSTANCE.entityToDto(
                clientRepository.getByClientEmail(clientEmail)
        );
    }

    private ClientFullDTO getByClientRut(String clientRut) {
        return ClientMapper.INSTANCE.entityToDto(
                clientRepository.getByClientRut(clientRut)
        );
    }

    private ClientFullDTO validateUser(String clientEmail, String clientRut) {
        return ClientMapper.INSTANCE.entityToDto(
                clientRepository.getByClientEmailOrClientRut(clientEmail, clientRut)
        );
    }

}
