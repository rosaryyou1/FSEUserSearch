package com.cognizant.aws.fse.usersearch.queryexecutor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.cognizant.aws.fse.usersearch.json.domain.UserJsonModel;
import com.cognizant.aws.fse.usersearch.util.QueryUtil;

@Service
public class NameAssociateIdQueryExecutor implements CriteriaQueryExecutor{
	
	
		
	public NameAssociateIdQueryExecutor() {
	}

	@Override
	public List<UserJsonModel> executeQuery(String criteriaValue,DynamoDB dynamoDB) {
		String keyExpression = "";
		String indexName = "";
		ValueMap valueMap = new ValueMap();
		String[] criteriaValueArray = criteriaValue.split("-");
		if(criteriaValueArray.length==2) {
			keyExpression = "AssociateId = :v1 and begins_with(AssociateName , :v2)";
			valueMap.withString(":v1", criteriaValueArray[0]);
			valueMap.withString(":v2", criteriaValueArray[1]);
		}else if(criteriaValueArray.length==1){
			keyExpression = "AssociateId = :v1";
			valueMap.withString(":v1", criteriaValueArray[0]);
		}
		List<UserJsonModel> lstUserJsonMdl = QueryUtil.querySpecExecution(keyExpression, indexName, valueMap, dynamoDB);
		return lstUserJsonMdl;
	}

}
