package br.com.neurotech.challenge.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.ClientService;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ClientController {

  @Autowired
  ClientService clientService;

  @GetMapping("/client/{id}")
  public ResponseEntity<Object> getClient(@PathVariable String id) {
    NeurotechClient client = clientService.get(id);
    if (client == null) {
      return new ResponseEntity<>("get.client.error.not_found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(client, HttpStatus.OK);
  }

  @PostMapping("/client")
  public ResponseEntity<?> saveClient(@RequestBody NeurotechClient client) {
    String newClientId = clientService.save(client);
    if (newClientId == null) {
      return new ResponseEntity<>("save.client.error", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    URI locationUri = URI.create(String.format("localhost/api/client/%s", newClientId));
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(locationUri);
    return new ResponseEntity<>(newClientId, headers, HttpStatus.CREATED);
  }

}
