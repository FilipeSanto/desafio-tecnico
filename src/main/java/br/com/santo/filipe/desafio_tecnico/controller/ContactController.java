package br.com.santo.filipe.desafio_tecnico.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santo.filipe.desafio_tecnico.models.Contact.ContactDTO;
import br.com.santo.filipe.desafio_tecnico.service.ContactsService;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactsService contactsService;

    @PostMapping("/register")
    public ResponseEntity<?> contact(final @RequestBody @Valid ContactDTO contact,
            final @RequestHeader(value = "Authorization", required = true) String authorization) {
        return contactsService.registerContact(contact, authorization);
    }
}
