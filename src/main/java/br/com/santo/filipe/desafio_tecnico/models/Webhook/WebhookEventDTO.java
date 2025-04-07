package br.com.santo.filipe.desafio_tecnico.models.Webhook;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WebhookEventDTO {
    private int appId;
    private int eventId;
    private int subscriptionId;
    private int portalId;
    private long occurredAt;
    private String subscriptionType;
    private int attemptNumber;
    private int objectId;
    private String changeSource;
    private String changeFlag;

}
