package br.com.santo.filipe.desafio_tecnico.itg.service;

import jakarta.servlet.http.HttpSession;
import br.com.santo.filipe.desafio_tecnico.models.Authorization.AuthorizationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HubspotAuthService {

    @Value("${hubspot.token.url}")
    private String hubspotTokenUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<Map> authorization(String code, AuthorizationDTO authorizationDto, HttpSession session) {

        log.info("Realizando chamada para criação de token de autorização.");
        String request = buildRequest(authorizationDto);
        HttpEntity<String> requestEntity = BuildRequestEntity(request);
        return restTemplate.postForEntity(hubspotTokenUrl, requestEntity, Map.class);
    }

    private HttpEntity<String> BuildRequestEntity(String request) {
        log.info("Criando request.");
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/x-www-form-urlencoded");

        HttpHeaders headers = new HttpHeaders();
        headersMap.forEach(headers::set);

        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);
        return requestEntity;
    }

    private String buildRequest(AuthorizationDTO authorizationDto) {
        log.info("Criando url com base nos dados de autorização.");

        return "grant_type=" + authorizationDto.getGrantType() + "&client_id="
                + authorizationDto.getClientId()
                + "&client_secret=" + authorizationDto.getClientSecret() + "&redirect_uri="
                + authorizationDto.getRedirectUri()
                + "&code="
                + authorizationDto.getCode();
    }
}
