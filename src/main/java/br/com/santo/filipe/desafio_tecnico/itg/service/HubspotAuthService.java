package br.com.santo.filipe.desafio_tecnico.itg.service;

import br.com.santo.filipe.desafio_tecnico.domain.AuthDTO;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HubspotAuthService {

    @Value("${hubspot.token.url}")
    private String hubspotTokenUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public HttpSession geraToken(String codigo, AuthDTO authDTO, HttpSession session)
            throws URISyntaxException, JsonProcessingException {

        String requestBody = "grant_type=" + authDTO.getGrantType() + "&client_id=" + authDTO.getClientId()
                + "&client_secret=" + authDTO.getClientSecret() + "&redirect_uri=" + authDTO.getRedirectUri() + "&code="
                + authDTO.getCode();

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/x-www-form-urlencoded");

        HttpHeaders headers = new HttpHeaders();
        headersMap.forEach(headers::set);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(hubspotTokenUrl, requestEntity, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> tokens = response.getBody();
            assert tokens != null;
            String accessToken = (String) tokens.get("access_token");
            String refreshToken = (String) tokens.get("refresh_token");
            session.setAttribute("Authorization", "Bearer " + accessToken);
            session.setAttribute(session.getId(), refreshToken);

            return session;
        } else {
            return session;
        }

    }
}
