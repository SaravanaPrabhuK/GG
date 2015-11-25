package com.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.controller.SpringMongoConfig;
import com.controller.User;
import com.controller.UserReport;

public class UserDaoImpl implements UserDao {
	
	 private static final String COLLECTION = "User";

	public List userDataDisplay(User theUser) {
		
		//Setting the context and mongo template configuration
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		List<User> userReport;
		
		//Conditional Query Formation
		Criteria theCrt =  new Criteria();
		Query theQuery = new Query();
		System.out.println(theUser.getLastName());
		if(!theUser.getFirstName().equals(""))
				theCrt = theCrt.where("firstName").is(theUser.getFirstName());
	    if(!theUser.getLastName().equals(""))
	    		theCrt = theCrt.and("lastName").is(theUser.getLastName());
		if(!theUser.getProfession().equals(""))
				theCrt.and("profession").is(theUser.getProfession());	    			
	    if(!theUser.getAge().equals(""))
	    		theCrt=theCrt.and("age").is(theUser.getAge());
	    if(!theUser.getSex().equals(""))
	    		theCrt=theCrt.and("sex").is(theUser.getSex());
	    if(!theUser.getCity().equals(""))
	    		theCrt.and("city").is(theUser.getCity());	    			
	    	       		
		//Aggregation
		/*if(!theUser.getProfession().equals("") || !theUser.getCity().equals("")){
			Aggregation aggregation = newAggregation(
						match(theCrt),
						group("profession","city").count().as("total"));
				
			AggregationResults groupResults = mongoOperation.aggregate(
					aggregation, User.class, UserReport.class);
					  
			 userReport = groupResults.getMappedResults();
		}
		else{*/
			theQuery = theQuery.addCriteria(theCrt);
			System.out.println(theQuery.toString());
			userReport = mongoOperation.find(theQuery, User.class);
		//}
		return userReport;
	}
	}