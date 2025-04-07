package br.com.santo.filipe.desafio_tecnico.itg.service;

import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.santo.filipe.desafio_tecnico.models.Authorization.AuthorizationDTO;
import br.com.santo.filipe.desafio_tecnico.utils.DesafioUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HubspotAuthService {

    @Value("${hubspot.token.url}")
    private String hubspotTokenUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public HttpSession authorization(String code, AuthorizationDTO authorizationDto, HttpSession session)
            throws URISyntaxException, JsonProcessingException {

        String requestBody = "grant_type=" + authorizationDto.getGrantType() + "&client_id="
                + authorizationDto.getClientId()
                + "&client_secret=" + authorizationDto.getClientSecret() + "&redirect_uri="
                + authorizationDto.getRedirectUri()
                + "&code="
                + authorizationDto.getCode();

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
            try {
                session.setAttribute("Authorization", "Bearer " + DesafioUtils.encrypt(accessToken));
            } catch (Exception e) {
                log.error("Erro ao criptrografar token", e.getMessage());
            }
            session.setAttribute(session.getId(), refreshToken);

            return session;
        } else {
            return session;
        }

    }

}
