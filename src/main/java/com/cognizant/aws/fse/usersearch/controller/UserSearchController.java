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

	@GetMapping("/{criteria}/{criteriavalue}/{pageNo}")
	public String getUser(@PathVariable String criteria,@PathVariable String criteriavalue,@PathVariable int pageNo) {
		List<UserJsonModel> users;
		String result = null;
		try {
			String cacheKey = criteriavalue+pageNo;
			if(memcachedClient!=null && memcachedClient.get(cacheKey)!=null) {
				users = (List<UserJsonModel>)memcachedClient.get(cacheKey);
				System.out.println("from cache>>>"+users.get(0).getAssociateId());
			}else {
				users = userService.findByNameAndAssociateIdAndSkill(criteria, criteriavalue,pageNo);
				if(memcachedClient!=null && memcachedClient.get(cacheKey)!=null) {
					memcachedClient.replace(cacheKey, 3600, users);
				}else if(memcachedClient!=null){
					memcachedClient.add(cacheKey, 3600, users);
				}
			}

			ObjectMapper obj = new ObjectMapper();
			result = obj.writeValueAsString(users);
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;

	}

	@GetMapping("/cache1")
	public void getCache() {
		memcachedClient.add("a", 86400, "test");
		memcachedClient.add("b", 86400, "test1");
		System.out.println(memcachedClient.get("a"));
		System.out.println(memcachedClient.get("b"));

	}


}
