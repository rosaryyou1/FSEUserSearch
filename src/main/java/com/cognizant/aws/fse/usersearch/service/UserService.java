package com.cognizant.aws.fse.usersearch.service;

import java.util.List;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.cognizant.aws.fse.usersearch.json.domain.UserJsonModel;
import com.cognizant.aws.fse.usersearch.queryexecutor.CriteriaQueryExecutor;
import com.cognizant.aws.fse.usersearch.queryexecutor.CriteriaQueryFactory;
import com.cognizant.aws.fse.usersearch.util.ValidationException;
import com.cognizant.aws.fse.usersearch.util.ValidationUtil;

@Service
public class UserService {

	@Autowired
	CriteriaQueryFactory queryFactory;

	@Autowired
	PooledConnectionFactory pooledActiveMQConnectionFactory;

	@Autowired
	ActiveMQConnectionFactory activeMQConnectionFactory;
	
	@Value("${consumer.queues}")
	String queueName;
	
	@Autowired
	AmazonDynamoDB amzonDynamoDB;

/*	@JmsListener(destination = "${consumer.queues}")
	private void  receiveMessage(TextMessage message) throws JMSException, JsonMappingException, JsonProcessingException {
		System.out.println("jms message:" + message.getText());
	}

	public void publishCriteria(Criteria criteria) throws JMSException, JsonProcessingException {
		Connection producerConnection = pooledActiveMQConnectionFactory.createConnection();
		producerConnection.start();
		final Session producerSession = producerConnection
				.createSession(false, Session.AUTO_ACKNOWLEDGE);
		final Destination producerDestination = producerSession
				.createQueue("fsesearch-queue");
		final MessageProducer producer = producerSession
				.createProducer(producerDestination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		ObjectMapper obj = new ObjectMapper();
		String criteriaJson = obj.writeValueAsString(criteria);
		final TextMessage criteriaMsg = producerSession
				.createTextMessage(criteriaJson);
		producer.send(criteriaMsg);
		producer.close();
		producerSession.close();
		producerConnection.close();
	}*/


	public  List<UserJsonModel> findByNameAndAssociateIdAndSkill(String criteriaName, String criteriaValue,int pageNo) throws ValidationException{
		//Criteria criteria = new Criteria(criteriaName,criteriaValue);
		/*try {
			this.publishCriteria(criteria);
		} catch (JsonProcessingException | JMSException e) {
			e.printStackTrace();
		}*/
		ValidationUtil.validateCriteria(criteriaName,criteriaValue);
		DynamoDB dynamoDB = new DynamoDB(amzonDynamoDB);
		CriteriaQueryExecutor criteriaQryExec = queryFactory.getCriteriaQueryExecutor(criteriaName, criteriaValue);
		List<UserJsonModel> lstUserModel =criteriaQryExec.executeQuery(criteriaValue,dynamoDB,pageNo);
		return lstUserModel;
	}


}
