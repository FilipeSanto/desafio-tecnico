package br.com.santo.filipe.desafio_tecnico.service;

import br.com.santo.filipe.desafio_tecnico.itg.service.HubspotAuthService;
import br.com.santo.filipe.desafio_tecnico.models.Authorization.AuthorizationDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class AuthenticationService {

    @Value("${hubspot.client.id}")
    private String clientId;
    @Value("${hubspot.client.secret}")
    private String clientSecret;
    @Value("${hubspot.redirect.uri}")
    private String redirectUri;
    @Value("${hubspot.auth.url}")
    private String authUrl;
    @Value("${hubspot.scope}")
    private String escope;

    @Autowired
    HubspotAuthService hubspotAuthService;

    public String generateAuthorizationUrl() {
        return authUrl +
                "?client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8)
                + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
                + "&scope=" + escope;
    }

    public HttpSession generateToken(String code, HttpSession session) {
        try {
            return hubspotAuthService.authorization(code, buildAuthorizeDto(code), session);

        } catch (URISyntaxException | JsonProcessingException e) {
            log.error("Erro ao gerar token", e.getMessage());
            return null;

        }

    }

    private AuthorizationDTO buildAuthorizeDto(String code) {
        return AuthorizationDTO.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .code(code)
                .grantType("authorization_code")
                .redirectUri(redirectUri)
                .build();
    }

}
