package br.com.santo.filipe.desafio_tecnico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.santo.filipe.desafio_tecnico.models.Webhook.WebhookEventDTO;
import br.com.santo.filipe.desafio_tecnico.service.WebhookService;

@RestController
public class WebhookController {

    @Autowired
    private WebhookService webhookService;

    @GetMapping("/webhook")
    public WebhookEventDTO handleContactCreation(
            @RequestHeader(value = "Authorization", required = true) final String authorization) {
        return webhookService.processContactCreation(authorization);
    }
}