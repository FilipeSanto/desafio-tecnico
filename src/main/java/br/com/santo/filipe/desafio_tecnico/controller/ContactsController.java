package br.com.santo.filipe.desafio_tecnico.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.santo.filipe.desafio_tecnico.domain.Contatos.ContatoDTO;
import br.com.santo.filipe.desafio_tecnico.service.ContactsService;

@RestController
@RequestMapping("/contato")
public class ContactsController {

    @Autowired
    ContactsService contactsService;

    @PostMapping("/cadastrar")
    public ResponseEntity login(@RequestBody @Valid ContatoDTO contato, @RequestHeader String authorization) {

        return contactsService.cadastrarContato(contato, authorization);
    }
}
