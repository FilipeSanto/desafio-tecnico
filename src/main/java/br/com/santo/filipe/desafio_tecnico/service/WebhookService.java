package br.com.santo.filipe.desafio_tecnico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santo.filipe.desafio_tecnico.itg.service.HubspotWebHookService;
import br.com.santo.filipe.desafio_tecnico.models.Webhook.WebhookEventDTO;

@Service
public class WebhookService {

    @Autowired
    HubspotWebHookService webhookService;

    /**
     * Processa a criação de um contato a partir de um evento de webhook.
     * 
     * <p>
     * Este método recebe um token de autorização e utiliza o serviço de webhook
     * para consultar e processar as informações relacionadas à criação de contatos.
     * </p>
     * 
     * @param authorization Token de autorização necessário para autenticação da
     *                      solicitação.
     * @return Um objeto {@link WebhookEventDTO} contendo os detalhes do evento de
     *         webhook processado.
     */
    public WebhookEventDTO processContactCreation(final String authorization) {
        return webhookService.consultaWebHook(authorization);
    }
}