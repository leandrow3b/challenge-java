package com.challenge.client.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.client.entity.Client;
import com.challenge.client.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) {
		return clientRepository.save(client);
	}

	public Optional<Client> findById(long id) {
		return clientRepository.findById(id);
	}

	public void delete(Client client) {
		clientRepository.delete(client);
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Optional<Client> findByCpf(String cpf) {
		return clientRepository.findByCpf(cpf);
	}

}
