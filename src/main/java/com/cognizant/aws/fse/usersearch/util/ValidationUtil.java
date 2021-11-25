package com.cognizant.aws.fse.usersearch.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidationUtil {
	
	public static void validateCriteria(String criteriaName,String criteriaValue) throws ValidationException {
		List<ValidationError> errorLst = new ArrayList<>();
		if(!("searchName".equals(criteriaName) || "searchSkill".equals(criteriaName))) {
			ValidationError validationError = new ValidationError("Criteria", "Criteria is incorrect");
			errorLst.add(validationError);
		}
		if("searchSkill".equals(criteriaName)) {
			String[] skillArray = new String[] {"Angular","HtmlCss","React","Spring","Rest","Hibernate","Git","Docker","Jenkins","Aws","Spoken","Communication","Aptitude"};
			List<String> skillLst = Arrays.asList(skillArray);
			if(!skillLst.contains(criteriaValue)) {
				ValidationError validationError = new ValidationError("Criteria", "Incorrect Skill");
				errorLst.add(validationError);
			}
		}
		if(!errorLst.isEmpty()) {
			constructError(errorLst);
		}
	}

	public static void constructError(List<ValidationError> errorLst) throws ValidationException {
		if(!errorLst.isEmpty()) {
			ObjectMapper obj = new ObjectMapper();
			String json = "Validation Error";
			try {
				json = obj.writeValueAsString(errorLst);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			throw new ValidationException(json);
		}
	}

}
