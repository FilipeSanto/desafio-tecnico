package br.com.santo.filipe.desafio_tecnico.itg.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.santo.filipe.desafio_tecnico.models.Contact.ContactDTO;
import br.com.santo.filipe.desafio_tecnico.models.Webhook.WebhookEventDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HubspotWebHookService {

    @Value("${hubspot.webhook.url}")
    private String hubspotWebhookUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WebhookEventDTO webhookConsult(String authorization) {
        HttpEntity<ContactDTO> requestEntity = buildRequest(authorization);
        log.info("Enviando requisição.");
        return restTemplate.exchange(hubspotWebhookUrl, HttpMethod.GET, requestEntity, WebhookEventDTO.class).getBody();
    }

    private HttpEntity<ContactDTO> buildRequest(String authorization) {
        log.info("Criando requisição.");
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("Authorization", authorization);

        HttpHeaders headers = new HttpHeaders();
        headersMap.forEach(headers::set);

        HttpEntity<ContactDTO> requestEntity = new HttpEntity<>(headers);
        return requestEntity;
    }

}
