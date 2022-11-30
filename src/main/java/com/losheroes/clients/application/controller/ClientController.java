package com.losheroes.clients.application.controller;

import com.losheroes.clients.application.dto.request.ClientCreateRequestDTO;
import com.losheroes.clients.application.dto.request.ClientLoginRequestDTO;
import com.losheroes.clients.application.dto.request.ClientUpdateRequestDTO;
import com.losheroes.clients.application.dto.response.ClientFullDTO;
import com.losheroes.clients.application.dto.response.ClientLoginResponseDTO;
import com.losheroes.clients.application.exception.ResourceBadRequestException;
import com.losheroes.clients.application.exception.ResourceInternalErrorException;
import com.losheroes.clients.application.service.IClientService;
import com.losheroes.clients.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@SwaggerDefinition(tags = {
        @Tag(name = SwaggerConfig.CLIENT_TAG, description = "CRUD Clientes Los Héroes")
})
public class ClientController {

    @Autowired
    private IClientService clientService;

    @ApiOperation(
            value = "Crea un cliente para Los Héroes.",
            notes = "No permite crear clientes con rut o email repetidos.",
            response = ClientFullDTO.class
    )
    @PostMapping("/clients/create")
    public ResponseEntity<ClientFullDTO> createClient(@RequestBody ClientCreateRequestDTO clientCreateRequestDTO) throws ResourceInternalErrorException, ResourceBadRequestException {
        try {
            ClientFullDTO result = clientService.createClient(clientCreateRequestDTO);
            return ResponseEntity.ok(result);
        } catch (ResourceBadRequestException e) {
            throw new ResourceBadRequestException(e.getMessage());
        } catch (ResourceInternalErrorException ee) {
            throw new ResourceInternalErrorException("Error al crear el cliente -> " + ee.getMessage());
        }
    }

    @ApiOperation(
            value = "Inicia sesión para clientes de Los Héroes.",
            notes = "Enviar un email y password válidos.",
            response = ClientLoginResponseDTO.class
    )
    @PostMapping("/clients/login")
    public ResponseEntity<ClientLoginResponseDTO> loginClient(@RequestBody ClientLoginRequestDTO clientLoginRequestDTO) throws ResourceBadRequestException {
        try {
            ClientLoginResponseDTO clientLoginResponseDTO = clientService.loginClient(clientLoginRequestDTO);
            return ResponseEntity.ok(clientLoginResponseDTO);
        } catch (ResourceBadRequestException e) {
            throw new ResourceBadRequestException(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Obtiene un cliente para Los Héroes.",
            notes = "Enviar un rut válido.",
            response = ClientFullDTO.class
    )
    @GetMapping("/client/get/{rut}")
    public ResponseEntity<ClientFullDTO> getClient(@PathVariable(value = "rut") String clientRut) throws ResourceBadRequestException {
        try {
            ClientFullDTO clientFullDTO = clientService.getClient(clientRut);
            return ResponseEntity.ok(clientFullDTO);
        } catch (ResourceBadRequestException e) {
            throw new ResourceBadRequestException(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Borra un cliente para Los Héroes.",
            notes = "Enviar un rut válido.",
            response = String.class
    )
    @PutMapping("/client/update/{rut}")
    public ResponseEntity<String> updateClient(@PathVariable(value = "rut") String clientRut, @RequestBody ClientUpdateRequestDTO clientUpdateRequestDTO) throws ResourceBadRequestException, ResourceInternalErrorException {
        try {
            String result = clientService.updateClient(clientRut, clientUpdateRequestDTO);
            return ResponseEntity.ok(result);
        } catch (ResourceBadRequestException e) {
            throw new ResourceBadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/client/delete/{rut}")
    public ResponseEntity<String> deleteClient(@PathVariable(value = "rut") String clientRut) throws ResourceBadRequestException, ResourceInternalErrorException {
        try {
            String result = clientService.deleteClient(clientRut);
            return ResponseEntity.ok(result);
        } catch (ResourceBadRequestException e) {
            throw new ResourceBadRequestException(e.getMessage());
        }
    }

}