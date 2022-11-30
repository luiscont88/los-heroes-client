package com.losheroes.clients.application.service.impl;

import com.losheroes.clients.application.dto.response.ClientFullDTO;
import com.losheroes.clients.application.dto.response.ClientSecurityDTO;
import com.losheroes.clients.application.mapper.ClientMapper;
import com.losheroes.clients.application.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientSecurityService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String clientEmail) throws UsernameNotFoundException {
        ClientFullDTO clientFullDTO = getClient(clientEmail);
        return buildSecurityClient(clientFullDTO);
    }

    private ClientFullDTO getClient(String clientEmail) {
        return ClientMapper.INSTANCE.entityToDto(
                clientRepository.getByClientEmail(clientEmail)
        );
    }

    private ClientSecurityDTO buildSecurityClient(ClientFullDTO clientFullDTO) {
        ClientSecurityDTO clientSecurityDTO = new ClientSecurityDTO();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("CLIENT"));
        clientSecurityDTO.setClientEmail(clientFullDTO.getClientEmail());
        clientSecurityDTO.setClientPassword(clientFullDTO.getClientPassword());
        clientSecurityDTO.setAuthorities(authorities);
        return clientSecurityDTO;
    }
}
