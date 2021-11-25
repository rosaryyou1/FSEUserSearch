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
public class SkillQueryExecutor implements CriteriaQueryExecutor{

	
	
	public SkillQueryExecutor() {
	}

	@Override
	public List<UserJsonModel> executeQuery(String criteriaValue,DynamoDB dynamoDB) {
		String keyExpression = "";
		String indexName = "";
		ValueMap valueMap = new ValueMap();
		keyExpression = criteriaValue+" = :v1 and "+criteriaValue+"Level > :v2";
		System.out.println(keyExpression);
		valueMap.withString(":v1",criteriaValue);
		valueMap.withNumber(":v2", 10);
		if("Angular".equals(criteriaValue)) {
			indexName = "GSI-search-index1";
		}else if("HtmlCss".equals(criteriaValue)) {
			indexName = "GSI-search-index2";
		}else if("React".equals(criteriaValue)) {
			indexName = "GSI-search-index3";
		}
		List<UserJsonModel> lstUserJsonMdl = QueryUtil.querySpecExecution(keyExpression, indexName, valueMap, dynamoDB);
		return lstUserJsonMdl;
	}

}
