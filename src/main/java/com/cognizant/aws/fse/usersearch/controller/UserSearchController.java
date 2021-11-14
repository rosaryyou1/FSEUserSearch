package com.cognizant.aws.fse.usersearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.aws.fse.usersearch.json.domain.UserJsonModel;

@RestController
@RequestMapping("/v1")
public class UserSearchController {

	@GetMapping("/users/{criteria}/{criteriavalue}")
	public List<UserJsonModel> getUser(@PathVariable String criteria,@PathVariable String criteriavalue) {
		System.out.println(criteriavalue);
		List<UserJsonModel> users =  new ArrayList<>();
		return users;

	}

}
