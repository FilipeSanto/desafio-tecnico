package br.com.santo.filipe.desafio_tecnico.service;

import br.com.santo.filipe.desafio_tecnico.domain.Contatos.ContatoDTO;
import br.com.santo.filipe.desafio_tecnico.itg.service.HubspotContatosService;
import br.com.santo.filipe.desafio_tecnico.models.HubSpotContacts;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Service
public class ContactsService {

    @Autowired
    private HubspotContatosService service;

    private static final String API_KEY = "Y3b92748a-8b7d-474f-8717-49942616b542";
    private static final String BASE_URL = "https://app.hubspot.com/oauth/authorize?client_id=3b92748a-8b7d-474f-8717-49942616b542&redirect_uri=http://localhost:3000&scope=oauth";

    // public static void main(String[] args) {
    // String endpoint = "/contacts/v1/lists/all/contacts/all";
    // String url = BASE_URL + endpoint + "?hapikey=" + API_KEY;

    // try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
    // HttpGet request = new HttpGet(url);
    // HttpResponse response = httpClient.execute(request);
    // HttpEntity entity = response.getEntity();

    // if (entity != null) {
    // String result = EntityUtils.toString(entity);
    // System.out.println("Resultado da consulta: " + result);
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
    // HttpGet request = new HttpGet(url);
    // HttpResponse response = httpClient.execute(request);
    // HttpEntity entity = response.getEntity();

    // if (entity != null) {
    // String result = EntityUtils.toString(entity);
    // ObjectMapper mapper = new ObjectMapper();
    // HubSpotContacts contacts = mapper.readValue(result, HubSpotContacts.class);
    // System.out.println(contacts);
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    public ResponseEntity cadastrarContato(ContatoDTO request, String authorization) {

        return service.cadastraContato(request, authorization);

    }
}
