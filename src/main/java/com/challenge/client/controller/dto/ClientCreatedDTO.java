package com.challenge.client.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Client created")
public class ClientCreatedDTO {
	@ApiModelProperty("Identificador")
	private Long id;
	
	@ApiModelProperty("Name")
	private String name;
	
	@ApiModelProperty("CPF")
	private String cpf;
	
	@ApiModelProperty("Address")
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
