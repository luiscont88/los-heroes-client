package com.losheroes.clients.application.dto.response;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class ClientLoginResponseDTO {

    private ObjectId id;
    private String clientEmail;
    private String clientName;
    private String clientLastname;
    private String clientRut;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

}
