package br.com.santo.filipe.desafio_tecnico.service;

import br.com.santo.filipe.desafio_tecnico.itg.service.HubspotContatosService;
import br.com.santo.filipe.desafio_tecnico.models.Contact.ContactDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactsService {

    @Autowired
    private HubspotContatosService service;

    public ResponseEntity registerContact(ContactDTO contact, String autorizacao) {
        return service.registerContact(contact, autorizacao);
    }
}
