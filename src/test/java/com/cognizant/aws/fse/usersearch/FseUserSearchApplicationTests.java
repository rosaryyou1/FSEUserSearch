package com.cognizant.aws.fse.usersearch;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.aws.fse.usersearch.json.domain.UserJsonModel;
import com.cognizant.aws.fse.usersearch.service.UserService;
import com.cognizant.aws.fse.usersearch.util.ValidationException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
@ActiveProfiles("test")
class FseUserSearchApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	static List<UserJsonModel> lstMockUsrJsonModel;
	
	@BeforeAll
	public static void setup() {
		UserJsonModel mockUserJsonModel = new UserJsonModel();
		lstMockUsrJsonModel = new ArrayList<>();
		lstMockUsrJsonModel.add(mockUserJsonModel);
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testgetUserBySearchName() {
		
		try {
			Mockito.when(userService.findByNameAndAssociateIdAndSkill(
					Mockito.anyString(), Mockito.anyString())).thenReturn(lstMockUsrJsonModel);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/skill-tracker/api/v1/admin/searchName/CTS3574121-Cyril").accept(
					MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			assertTrue(lstMockUsrJsonModel.size()==1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetUserBySkill() {
		
		try {
			Mockito.when(userService.findByNameAndAssociateIdAndSkill(
					Mockito.anyString(), Mockito.anyString())).thenReturn(lstMockUsrJsonModel);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/skill-tracker/api/v1/admin/searchSkill/Angular").accept(
					MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			assertTrue(lstMockUsrJsonModel.size()==1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetUserByCrteriaValidateError() {
		
		try {
			Mockito.when(userService.findByNameAndAssociateIdAndSkill(
					Mockito.anyString(), Mockito.anyString())).thenThrow(new ValidationException("{\"error\":\"Incorrect Skills\"}"));
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/skill-tracker/api/v1/admin/searchName/CTS3574121-Cyril").accept(
					MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());
		} catch (Exception e) {
			assertTrue(e.getMessage()!=null);
		}
	}

}
