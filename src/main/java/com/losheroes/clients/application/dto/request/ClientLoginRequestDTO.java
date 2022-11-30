package com.losheroes.clients.application.dto.request;

import lombok.Data;

@Data
public class ClientLoginRequestDTO {
    private String clientEmail;
    private String clientRut;
    private String clientPassword;
}