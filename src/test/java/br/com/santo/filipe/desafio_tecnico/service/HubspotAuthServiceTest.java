package br.com.santo.filipe.desafio_tecnico.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;

import br.com.santo.filipe.desafio_tecnico.itg.service.HubspotAuthService;
import br.com.santo.filipe.desafio_tecnico.models.Authorization.AuthorizationDTO;
import jakarta.servlet.http.HttpSession;

class service {

    private AuthenticationService service;

    @Mock
    private HubspotAuthService hubspotAuthService;

    @Mock
    private HttpSession httpSession;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateToken() {
        String code = "test-code";
        AuthorizationDTO authorizationDTO = new AuthorizationDTO();
        authorizationDTO.setCode(code);
        ResponseEntity<Map> mockResponse = mock(ResponseEntity.class);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", "test-access-token");
        tokens.put("refresh_token", "test-refresh-token");

        when(hubspotAuthService.authorization(eq(code), any(), eq(httpSession))).thenReturn(mockResponse);
        when(mockResponse.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockResponse.getBody()).thenReturn(tokens);

        service.generateToken(code, httpSession);

        verify(httpSession).setAttribute("Authorization", "test-access-token");
        verify(httpSession).setAttribute(httpSession.getId(), "test-refresh-token");
    }
}