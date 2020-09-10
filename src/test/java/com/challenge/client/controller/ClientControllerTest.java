package com.challenge.client.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.challenge.client.controller.dto.NewClientDTO;
import com.challenge.client.entity.Client;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
@WithMockUser(value = "user", password = "123")
class ClientControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@Order(1)  
	void mustCreateAnewCustomer() throws Exception {
		NewClientDTO newClientDTO = new NewClientDTO("Leandro da Luz da Costa", "890.300.282-20", "Avenida Cipriano Rodrigues, 218");
		mvc.perform(post("/api/client").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(newClientDTO)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@Order(2)  
	void notMustCreatedClientWithCpfRepeated() throws Exception {
		NewClientDTO newClientDTO = new NewClientDTO("Leandro da Luz da Costa", "890.300.282-20", "Avenida Cipriano Rodrigues, 218");
		mvc.perform(post("/api/client").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(newClientDTO)))
				.andExpect(status().isNotAcceptable());
	}
	
	@Test
	@Order(3)  
	void noShouldRepecifyAll() throws Exception {
		mvc.perform(get("/api/client").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	@Order(4)  
	void noShouldRefreshCustomer() throws Exception {
		Client client = new Client(1L, "Leandro da Luz da Costa", "890.300.282-20", "Avenida Cipriano Rodrigues, 218");
		mvc.perform(put("/api/client/1").contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(client)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", not(equalTo("Leandro Costa"))));
	}
	
	@Test
	@Order(5)  
	void noShouldRecoverCustomer() throws Exception {
		mvc.perform(get("/api/client/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Leandro da Luz da Costa")))
				.andExpect(jsonPath("$.cpf", equalTo("890.300.282-20")))
				.andExpect(jsonPath("$.address", equalTo("Avenida Cipriano Rodrigues, 218")));		
	}
	
	@Test
	@Order(6)  
	void noMustRemoveCustomer() throws Exception {
		mvc.perform(delete("/api/client/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
