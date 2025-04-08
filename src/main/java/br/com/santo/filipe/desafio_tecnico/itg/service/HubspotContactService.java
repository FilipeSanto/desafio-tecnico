package br.com.santo.filipe.desafio_tecnico.itg.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.santo.filipe.desafio_tecnico.models.Contact.ContactDTO;
import br.com.santo.filipe.desafio_tecnico.models.Contact.ContactResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HubspotContactService {

    @Value("${hubspot.register.contact.url}")
    private String hubspotRegisterContactUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<ContactResponseDTO> registerContact(ContactDTO contact, String authorization) {

        RequestEntity<ContactDTO> requestEntity = buildRequest(contact, authorization);
        return restTemplate.postForEntity(hubspotRegisterContactUrl, requestEntity, ContactResponseDTO.class);

    }

    private RequestEntity<ContactDTO> buildRequest(ContactDTO contact, String authorization) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + authorization);

        RequestEntity<ContactDTO> requestEntity = new RequestEntity(contact, headers, HttpMethod.POST,
                createUri(hubspotRegisterContactUrl));
        return requestEntity;
    }

    private URI createUri(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("URL inválida: " + url, ex);
        }
    }

}
