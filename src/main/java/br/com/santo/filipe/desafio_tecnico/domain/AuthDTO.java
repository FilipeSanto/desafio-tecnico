package br.com.santo.filipe.desafio_tecnico.domain;

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
public class AuthDTO {
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String redirectUri;
    private String code;

}
