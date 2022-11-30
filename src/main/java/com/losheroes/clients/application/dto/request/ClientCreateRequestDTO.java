package com.losheroes.clients.application.dto.request;

import lombok.Data;

@Data
public class ClientCreateRequestDTO {
    private String clientName;
    private String clientLastname;
    private Integer clientAge;
    private Long clientPhone;
    private String clientEmail;
    private String clientPassword;
    private String clientRut;
}