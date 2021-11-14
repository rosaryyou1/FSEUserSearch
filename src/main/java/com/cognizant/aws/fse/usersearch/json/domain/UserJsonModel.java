package com.cognizant.aws.fse.usersearch.json.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown =  true)
public class UserJsonModel {
	@JsonProperty("UserId")
	String userId;
	@JsonProperty("AssociateName")
	String associateName;
	@JsonProperty("AssociateId")
	String associateId;
	@JsonProperty("Mobile")
	String mobile;
	@JsonProperty("Email")
	String email;
	@JsonProperty("Skills")
	List<Skill> skills;
 
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public UserJsonModel() {

	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getAssociateName() {
		return associateName;
	}
	public void setAssociateName(String assoicateName) {
		this.associateName = assoicateName;
	}

	public String getAssociateId() {
		return associateId;
	}
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	

}
