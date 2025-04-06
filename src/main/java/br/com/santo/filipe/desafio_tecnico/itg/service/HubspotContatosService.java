package br.com.santo.filipe.desafio_tecnico.itg.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.santo.filipe.desafio_tecnico.domain.Contatos.ContatoDTO;
import br.com.santo.filipe.desafio_tecnico.domain.Contatos.ContatoResponseDTO;
import jakarta.servlet.http.HttpSession;

@Service
public class HubspotContatosService {

    @Value("${hubspot.cadastrar.contato.url}")
    private String hubspotCadastrarContatoUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<ContatoResponseDTO> cadastraContato(ContatoDTO dto, String authorization) {

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("authorization", authorization);

        HttpHeaders headers = new HttpHeaders();
        headersMap.forEach(headers::set);

        HttpEntity<ContatoDTO> requestEntity = new HttpEntity<>(dto, headers);

        return restTemplate.exchange(hubspotCadastrarContatoUrl, HttpMethod.POST, requestEntity,
                ContatoResponseDTO.class);

    }

}
