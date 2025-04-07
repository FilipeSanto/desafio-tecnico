package br.com.santo.filipe.desafio_tecnico.controller;

import br.com.santo.filipe.desafio_tecnico.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
public class OAuthContoller {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/generate-url")
    public ResponseEntity<Void> generateUrl() {
        String url = authenticationService.generateAuthorizationUrl();
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", url).build();
    }

    @GetMapping("/oauth-callback")
    public ResponseEntity<Void> handleCallback(final @RequestParam("code") String code, final HttpSession session)
            throws JsonProcessingException, URISyntaxException {
        authenticationService.generateToken(code, session);
        String token = session.getAttribute("Authorization").toString();

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/register-contact.html?Authorization=" + token)
                .build();
    }
}
