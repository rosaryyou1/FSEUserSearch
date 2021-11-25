package com.cognizant.aws.fse.usersearch.queryexecutor;

import org.springframework.stereotype.Component;

@Component
public class CriteriaQueryFactory {
	public CriteriaQueryExecutor getCriteriaQueryExecutor(String criteriaName,String criteriaValue) {
		CriteriaQueryExecutor criteriaQueryExecutor = null;
		switch(criteriaName){
		case "searchName" :
			criteriaQueryExecutor = 
				 new NameAssociateIdQueryExecutor();
			break;
		case "searchSkill" :
			criteriaQueryExecutor = 
			 new SkillQueryExecutor();
			break;
		}
		return criteriaQueryExecutor;
	}
}
