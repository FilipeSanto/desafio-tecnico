package br.com.santo.filipe.desafio_tecnico.service;

import br.com.santo.filipe.desafio_tecnico.itg.service.HubspotContatosService;
import br.com.santo.filipe.desafio_tecnico.models.Contact.ContactDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Slf4j
public class ContactsService {

    @Autowired
    private HubspotContatosService service;

    public ResponseEntity registerContact(ContactDTO contact, String autorizacao) {
        try {
            return service.registerContact(contact, autorizacao);
        } catch (HttpClientErrorException e) {
            log.error("Erro ao cadastrar contato", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
