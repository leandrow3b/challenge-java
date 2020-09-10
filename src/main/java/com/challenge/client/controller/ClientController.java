package com.challenge.client.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.client.controller.dto.ClientCreatedDTO;
import com.challenge.client.controller.dto.NewClientDTO;
import com.challenge.client.entity.Client;
import com.challenge.client.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;

@Api(value = "/client")
@RestController
@RequestMapping("/api")
public class ClientController {

	@Autowired
    private ClientService clientService;
	
	@Autowired
	private MapperFacade mapperFacade;
	
	@ApiOperation(value = "Operation responsible for creating a customer")
	@PostMapping(value="/client")
    public ResponseEntity<Object> add(@Valid @RequestBody NewClientDTO newClientDTO) {
		Client client = mapperFacade.map(newClientDTO, Client.class);
		Optional<Client> optionalClient = clientService.findByCpf(client.getCpf());
		if(optionalClient.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
        return new ResponseEntity<Object>(mapperFacade.map(
        		clientService.save(client), ClientCreatedDTO.class), HttpStatus.CREATED);
    }
    
	@ApiOperation(value = "Operation responsible for removing a client")
	@DeleteMapping(value = "/client/{id}")
    public ResponseEntity<Object> remove(@PathVariable(value = "id") long id) {
      
		Optional<Client> client = clientService.findById(id);
      
        if(client.isPresent()){
        	
        	clientService.delete(client.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@ApiOperation(value = "Operation responsible for listing a customer")
	@GetMapping(value = "/client")
	public List<Client> list() {
		return clientService.findAll();
	}
	
	@GetMapping(value = "/client/{id}")
    public ResponseEntity<Object> get(@PathVariable(value = "id") long id) {
      
		Optional<Client> client = clientService.findById(id);
      
        if(client.isPresent()){
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@ApiOperation(value = "Operation responsible for updating a client")
	@PutMapping(value = "/client/{id}")
    public ResponseEntity<Client> update(@PathVariable(value = "id") long id,
    		@Valid @RequestBody Client newClient){
        Optional<Client> oldClient = clientService.findById(id);

        if(oldClient.isPresent()){
        	
            Client client = oldClient.get();
            client.setName(newClient.getName());
            
            if(!client.getCpf().equals(newClient.getCpf())) {
            	Optional<Client> optionalClient = clientService.findByCpf(newClient.getCpf());
            	if(optionalClient.isPresent()) {
            		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            	}
            }
            client.setCpf(newClient.getCpf());
            client.setAddress(newClient.getAddress());
            
            clientService.save(client);
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
