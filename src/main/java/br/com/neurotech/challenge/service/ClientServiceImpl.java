package br.com.neurotech.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;
  
  @Override
  public String save(NeurotechClient client) {
    NeurotechClient newClient = clientRepository.save(client);
    return newClient.getId();
  }

  @Override
  public NeurotechClient get(String id) {
    Optional<NeurotechClient> client = clientRepository.findById(id);
    if (client.isEmpty()) {
      return null;
    }
    return client.get();
  }

}
