package br.com.santo.filipe.desafio_tecnico.controller;

import br.com.santo.filipe.desafio_tecnico.domain.AuthorizationDto;
import br.com.santo.filipe.desafio_tecnico.domain.LoginResponseDTO;
import br.com.santo.filipe.desafio_tecnico.domain.Usuario;
import br.com.santo.filipe.desafio_tecnico.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("desafio-tecnico/v1/auth")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    private static final String CLIENT_ID = "seu-client-id";
    private static final String REDIRECT_URI = "http://localhost:3000/callback";
    private static final String AUTHORIZATION_ENDPOINT = "https://example.com/oauth/authorize";

    private String generateAuthorizationUrl() {
        return "Ok";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthorizationDto data) {
         var userNamePass = new UsernamePasswordAuthenticationToken(data.login(), data.password());
         var auth = authenticationManager.authenticate(userNamePass);

         var token = tokenService.generateToken((Usuario) auth.getPrincipal());

         return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping("/auth-url")
    public String getAuthorizationUrl() {
        return generateAuthorizationUrl();
    }
}
