package com.cognizant.aws.fse.usersearch.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.cognizant.aws.fse.usersearch.json.domain.UserJsonModel;
import com.cognizant.aws.fse.usersearch.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QueryUtil {
	public static List<UserJsonModel>  querySpecExecution(
			String keyExpression,String indexName,
	ValueMap valueMap, DynamoDB dynamoDB) {
		QuerySpec spec = new QuerySpec()
			    .withKeyConditionExpression(keyExpression)
			    .withValueMap(valueMap);

		Table table = dynamoDB.getTable("FSEProfile");
		ItemCollection<QueryOutcome> items = null;
		if(!indexName.isEmpty()) {
		Index index = table.getIndex(indexName);
		items= index.query(spec);
		}else {
			items = table.query(spec);
		}
		//List<User> users = userDao.findByNameAndAssociateIdAndSkill("*","357495","Java");
		Iterator<Item> iterator = items.iterator();
		List<UserJsonModel> lstUserModel = new ArrayList<>();
		while (iterator.hasNext()) {
		    String json = iterator.next().toJSON();
		    System.out.println(json);
		    ObjectMapper obj = new ObjectMapper();
		    try {
				User user = obj.readValue(json, User.class);
				UserJsonModel userJsonModel = UserModelJsonConverter.convertUser(user);
				 lstUserModel.add(userJsonModel);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lstUserModel;
	}

}
