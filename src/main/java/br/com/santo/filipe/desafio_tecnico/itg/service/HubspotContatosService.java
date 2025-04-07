package br.com.santo.filipe.desafio_tecnico.itg.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.com.santo.filipe.desafio_tecnico.models.Contact.ContactDTO;
import br.com.santo.filipe.desafio_tecnico.models.Contact.ContactResponseDTO;
import br.com.santo.filipe.desafio_tecnico.utils.DesafioUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HubspotContatosService {

    @Value("${hubspot.register.contact.url}")
    private String hubspotRegisterContactUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<ContactResponseDTO> registerContact(ContactDTO contact, String authorization) {

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");
        try {
            headersMap.put("Authorization", DesafioUtils.decrypt(authorization));
        } catch (Exception e) {
            log.error("Erro ao decriptrogrfar Token", e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        headersMap.forEach(headers::set);

        HttpEntity<ContactDTO> requestEntity = new HttpEntity<>(contact, headers);

        return restTemplate.exchange(hubspotRegisterContactUrl, HttpMethod.POST,
                requestEntity,
                ContactResponseDTO.class);

    }

}
