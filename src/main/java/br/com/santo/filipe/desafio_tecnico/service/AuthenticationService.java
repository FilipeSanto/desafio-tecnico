package br.com.santo.filipe.desafio_tecnico.service;

import br.com.santo.filipe.desafio_tecnico.itg.service.HubspotAuthService;
import br.com.santo.filipe.desafio_tecnico.models.Authorization.AuthorizationDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

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
        log.info("Criando URL para busca de código de autenticação.");
        return String.format("%s?response_type=code&client_id=%s&scope=%s&redirect_uri=%s",
                authUrl,
                URLEncoder.encode(clientId, StandardCharsets.UTF_8),
                escope,
                URLEncoder.encode(redirectUri, StandardCharsets.UTF_8));
    }

    public void generateToken(String code, HttpSession session) {
        log.info("Realizando chamada para busca de token de autorização.");
        ResponseEntity<Map> response = hubspotAuthService.authorization(code, buildAuthorizeDto(code), session);
        validateResponse(session, response);
    }

    public void updateSessionData(HttpServletResponse response, HttpSession session) {

        log.info("Atualizando dados da sessão.");

        String token = session.getAttribute("Authorization").toString().replace("Bearer ", "");
        Cookie tokenCookie = new Cookie("Authorization", token);

        tokenCookie.setSecure(true);
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge(3600);

        response.addCookie(tokenCookie);
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

    private void validateResponse(HttpSession session, ResponseEntity<Map> response) {
        log.info("Validando resposta.");
        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> tokens = response.getBody();
            assert tokens != null;
            String accessToken = (String) tokens.get("access_token");
            String refreshToken = (String) tokens.get("refresh_token");
            try {
                session.setAttribute("Authorization", accessToken);
            } catch (Exception e) {
                log.error("Erro ao criptrografar token", e.getMessage());
            }
            session.setAttribute(session.getId(), refreshToken);
        }
    }

}
