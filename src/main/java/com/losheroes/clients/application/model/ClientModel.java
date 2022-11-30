package com.losheroes.clients.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clients")
public class ClientModel {

    @MongoId
    @Field("_id")
    private ObjectId id;
    @Field("client_name")
    private String clientName;
    @Field("client_lastname")
    private String clientLastname;
    @Field("client_age")
    private Integer clientAge;
    @Field("client_phone")
    private Long clientPhone;
    @Indexed(unique = true)
    @Field("client_email")
    private String clientEmail;
    @Field("client_password")
    private String clientPassword;
    @Indexed(unique = true)
    @Field("client_rut")
    private String clientRut;
    @Field("active")
    private Boolean active;
    @Field("creation_date")
    private LocalDateTime creationDate;
    @Field("update_date")
    private LocalDateTime updateTime;

}
