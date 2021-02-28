package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	
	private static final Long ID = 1L;
	private static final String EMAIL = "email@teste.com";
	private static final String NAME = "User Test";
	private static final String PASSWORD = "123456";
	private static final String URL = "/user";
	
	@MockBean
	UserService service;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSave() throws Exception {
		
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, EMAIL, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.name").value(NAME))
		.andExpect(jsonPath("$.data.email").value(EMAIL))
		.andExpect(jsonPath("$.data.password").doesNotExist());
	
	}
	
	@Test
	public void testSaveInvalidUser() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, "email", PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0]").value("Email inválido"));
	}
	
	public User getMockUser() {
		User u = new User();
		u.setId(ID);
		u.setEmail(EMAIL);
		u.setName(NAME);
		u.setPassword(PASSWORD);
		
		return u;
	}
	
	public String getJsonPayload(Long id, String name, String email, String password) {
		
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setEmail(email);
		dto.setName(name);
		dto.setPassword(password);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
