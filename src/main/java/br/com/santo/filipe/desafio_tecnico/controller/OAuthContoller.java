package br.com.santo.filipe.desafio_tecnico.controller;

import br.com.santo.filipe.desafio_tecnico.service.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class OAuthContoller {

    @Autowired
    AuthenticationService authenticationService;

    private final Map<String, String> refreshTokenStore = new HashMap<>();

    @GetMapping("/install")
    public ResponseEntity<Void> installApp() {
        String url = authenticationService.generateAuthorizationUrl();
        return ResponseEntity.status(302).header("Location", url).build();
    }

    @GetMapping("/oauth-callback")
    public String handleCallback(@RequestParam("code") String code, HttpSession session)
            throws URISyntaxException, JsonProcessingException {
        authenticationService.geraToken(code, session);
        return "";

    }

    @GetMapping("/")
    public String getContacts(HttpSession session) {
        String accessToken = (String) session.getAttribute("accessToken");

        if (accessToken == null) {
            return "Not authorized. Please <a href=\"/install\">install the app</a>.";
        }

        // Use the access token to query the HubSpot API
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        ResponseEntity<Map> response = restTemplate.getForEntity("apiUrl", Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "Contacts retrieved: " + response.getBody();
        } else {
            return "Failed to retrieve contacts.";
        }
    }
}
