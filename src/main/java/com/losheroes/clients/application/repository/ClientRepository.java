package com.losheroes.clients.application.repository;

import com.losheroes.clients.application.model.ClientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<ClientModel, String> {
    @Query("{$or : [{'client_email':?0},{'client_rut':?1}]}")
    ClientModel getByClientEmailOrClientRut(String clientEmail, String clientRut);
    ClientModel getByClientEmail(String clientEmail);
    ClientModel getByClientRut(String clientRut);
}
