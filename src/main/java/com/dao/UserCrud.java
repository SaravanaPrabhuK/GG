package com.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.controller.SpringMongoConfig;
import com.controller.User;

public class UserCrud {
	
	public List<User> getUserData(User theUser) {
	    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		
	    Query query = new Query();
		query = !theUser.getFirstName().equals("")?
				query.addCriteria(Criteria.where("firstName").is(theUser.getFirstName())):
	    !theUser.getLastName().equals("")?
	    		query.addCriteria(Criteria.where("lastName").is(theUser.getLastName())):
		!theUser.getProfession().equals("")?
	       		query.addCriteria(Criteria.where("profession").is(theUser.getProfession())):	    			
	    !theUser.getAge().equals("")?
	       		query.addCriteria(Criteria.where("age").is(theUser.getAge())):
	    !theUser.getSex().equals("")?
	       		query.addCriteria(Criteria.where("sex").is(theUser.getSex())):
	       	    query.addCriteria(Criteria.where("1").is("1"));	
	    
        List<User> listUser = mongoOperation.find(query, User.class);
	    return listUser;
		
	}

}