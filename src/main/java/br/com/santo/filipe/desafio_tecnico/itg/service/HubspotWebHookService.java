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

@Service
public class HubspotWebHookService {

    @Value("${hubspot.webhook.url}")
    private String hubspotWebhookUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WebhookEventDTO consultaWebHook(String authorization) {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("authorization", authorization);

        HttpHeaders headers = new HttpHeaders();
        headersMap.forEach(headers::set);

        HttpEntity<ContactDTO> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(hubspotWebhookUrl, HttpMethod.GET, requestEntity, WebhookEventDTO.class).getBody();
    }

}
