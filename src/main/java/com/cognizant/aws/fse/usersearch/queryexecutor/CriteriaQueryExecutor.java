package com.cognizant.aws.fse.usersearch.queryexecutor;

import java.util.List;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.cognizant.aws.fse.usersearch.json.domain.UserJsonModel;

public interface CriteriaQueryExecutor {
	
	List<UserJsonModel>  executeQuery(String criteriaValue,DynamoDB dynamoDB);

}
