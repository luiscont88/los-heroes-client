package com.losheroes.clients.application.dto.response;

import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
public class ClientFullDTO {

    private ObjectId id;
    private String clientName;
    private String clientLastname;
    private Integer clientAge;
    private Long clientPhone;
    private String clientEmail;
    private String clientPassword;
    private String clientRut;
    private Boolean active;
    private LocalDateTime creationDate;
    private LocalDateTime updateTime;

}
