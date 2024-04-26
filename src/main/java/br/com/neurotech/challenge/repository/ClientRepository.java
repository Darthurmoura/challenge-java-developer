package br.com.neurotech.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neurotech.challenge.entity.NeurotechClient;

public interface ClientRepository extends JpaRepository<NeurotechClient, String> {}
