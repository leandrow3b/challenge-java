package com.challenge.client.controller.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="New Client")
public class NewClientDTO {
	@ApiModelProperty("Full name")
	@NotBlank(message= "{name.not.blank}")
	private String name;
	@ApiModelProperty("CPF")
	@NotBlank(message= "{cpf.not.blank}")
	@CPF
	private String cpf;
	
	@ApiModelProperty("Address")
	private String address;


	public NewClientDTO() {
	}
	
	public NewClientDTO(@NotBlank(message = "{name.not.blank}") String name,
			@NotBlank(message = "{cpf.not.blank}") @CPF String cpf, String address) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.address = address;
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
