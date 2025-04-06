package br.com.santo.filipe.desafio_tecnico.service;

import br.com.santo.filipe.desafio_tecnico.domain.AuthDTO;
import br.com.santo.filipe.desafio_tecnico.itg.service.HubspotAuthService;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class AuthenticationService {

    @Value("${hubspot.client.id}")
    private String clientId;
    @Value("${hubspot.client.secret}")
    private String clientSecret;
    @Value("${hubspot.redirect.uri}")
    private String redirectUri;
    @Value("${hubspot.auth.url}")
    private String authUrl;
    @Value("${hubspot.token.url}")
    private String tokenUrl;
    @Value("${hubspot.api.url}")
    private String apiUrl;
    @Value("${hubspot.scope}")
    private String escope;

    @Autowired
    HubspotAuthService hubspotAuthService;

    public String generateAuthorizationUrl() {
        return authUrl +
                "?client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8) +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                "&scope=" + escope +
                "&response_type=code";
    }

    public String geraToken(String code, HttpSession session)
            throws URISyntaxException, JsonProcessingException {
        return hubspotAuthService.geraToken(code, buildAuthorizeDto(code), session);
    }

    private AuthDTO buildAuthorizeDto(String code) {
        return AuthDTO.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .code(code)
                .grantType("authorization_code")
                .redirectUri(redirectUri)
                .build();
    }

}
