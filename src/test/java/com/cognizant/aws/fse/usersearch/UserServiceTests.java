package com.cognizant.aws.fse.usersearch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.cognizant.aws.fse.usersearch.json.domain.UserJsonModel;
import com.cognizant.aws.fse.usersearch.service.UserService;
import com.cognizant.aws.fse.usersearch.util.ValidationException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
@ActiveProfiles("test")
public class UserServiceTests {
	@Autowired
	UserService userService;
	
	@Test
	public void testgetUserBySearchName() {
		List<UserJsonModel> lstMockUsrJsonModel;
		try {
			lstMockUsrJsonModel = 
					userService.findByNameAndAssociateIdAndSkill("searchName", "CTS3574121-Cyril",1);
		} catch (ValidationException e) {
			assertFalse(e.getMessage()!=null);
		}
	}

	@Test
	public void testgetUserBySkill() {
		List<UserJsonModel> lstMockUsrJsonModel;
		try {
			lstMockUsrJsonModel = userService.findByNameAndAssociateIdAndSkill("searchSkill", "Angular",1);
		} catch (ValidationException e) {
			assertFalse(e.getMessage()!=null);
		}
		
	}
	
	@Test
	public void testgetUserByIncorrectCriteria() {
		List<UserJsonModel> lstMockUsrJsonModel;
		try {
			lstMockUsrJsonModel = 
					userService.findByNameAndAssociateIdAndSkill("searchName12", "CTS3574121-Cyril",1);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			assertTrue(e.getMessage()!=null);
		}
	}

	@Test
	public void testgetUserByIncorrectSkill() {
		List<UserJsonModel> lstMockUsrJsonModel;
		try {
			lstMockUsrJsonModel = 
					userService.findByNameAndAssociateIdAndSkill("searchSkill", "Japanese",1);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			assertTrue(e.getMessage()!=null);
		}
	}

}
