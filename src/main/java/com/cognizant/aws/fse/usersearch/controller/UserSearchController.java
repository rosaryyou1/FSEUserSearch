package com.cognizant.aws.fse.usersearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.aws.fse.usersearch.json.domain.UserJsonModel;
import com.cognizant.aws.fse.usersearch.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.spy.memcached.MemcachedClient;

@CrossOrigin
@RestController
@RequestMapping("/skill-tracker/api/v1/admin")
public class UserSearchController {

	@Autowired
	UserService userService;

	//@Autowired
	MemcachedClient memcachedClient;
	
	@GetMapping("/{criteria}/{criteriavalue}")
	public String getUser(@PathVariable String criteria,@PathVariable String criteriavalue) {
		List<UserJsonModel> users;
		String result = null;
		try {
			users = userService.findByNameAndAssociateIdAndSkill(criteria, criteriavalue);
			ObjectMapper obj = new ObjectMapper();
			result = obj.writeValueAsString(users);
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;

	}
	
}
