package br.com.santo.filipe.desafio_tecnico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santo.filipe.desafio_tecnico.itg.service.HubspotWebHookService;
import br.com.santo.filipe.desafio_tecnico.models.Webhook.WebhookEventDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WebhookService {

    @Autowired
    HubspotWebHookService webhookService;

    public WebhookEventDTO processContactCreation(final String authorization) {
        log.info("Realizando chamada para consulta ao Webhook.");
        return webhookService.webhookConsult(authorization);
    }
}