package br.com.santo.filipe.desafio_tecnico.itg.service;

import br.com.santo.filipe.desafio_tecnico.domain.AuthDTO;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

@Service
public class HubspotAuthService {

    @Value("${hubspot.token.url}")
    private String hubspotTokenUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String geraToken(String codigo, AuthDTO authDTO, HttpSession session)
            throws URISyntaxException, JsonProcessingException {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("grant_type", authDTO.getGrantType());
        requestBody.put("client_id", authDTO.getClientId());
        requestBody.put("client_secret", authDTO.getClientSecret());
        requestBody.put("redirect_uri", authDTO.getRedirectUri());
        requestBody.put("code", authDTO.getCode());

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(requestBody);
        requestEntity.getHeaders().add("Content-Type", "application/x-www-form-urlencoded");

        ResponseEntity<Map> response = restTemplate.exchange(
                hubspotTokenUrl,
                HttpMethod.POST,
                requestEntity,
                Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> tokens = response.getBody();
            assert tokens != null;
            String accessToken = (String) tokens.get("access_token");
            String refreshToken = (String) tokens.get("refresh_token");
            session.setAttribute("accessToken", accessToken);
            session.setAttribute(session.getId(), refreshToken);

            System.out.println("Access Token: " + accessToken);
            System.out.println("Refresh Token: " + refreshToken);

            return "Token gerado com sucesso!";
        } else {
            return "Erro ao gerar token!";
        }

    }
}
