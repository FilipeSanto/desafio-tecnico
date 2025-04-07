package br.com.santo.filipe.desafio_tecnico.models.Authorization;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorizationDTO {
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String redirectUri;
    private String code;

}
