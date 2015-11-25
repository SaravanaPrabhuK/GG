package com.controller;

import java.util.*;

import com.controller.User;
import com.controller.UserRepository;
import com.controller.SpringMongoConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.junit.Assert.assertEquals;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMongoConfig.class})
@EnableAutoConfiguration
public class GlobalControllerTest {
	@Autowired
	UserRepository userRepository;
	
	@Before
	public void init() {
		userRepository.deleteAll();
		User user = new User();
		user.setUserName("sakaruna");
		user.setPassword("sak");
		user.setFirstName("Saravana");
		user.setLastName("Prabhu");
		user.setProfession("Programmer");
		user.setSex("male");
		user.setAge("30");
		user.setCity("SJC");
		
		userRepository.save(user);
		user = new User();
		user.setFirstName("Saravana");
		user.setLastName("Prabhu1");
		user.setProfession("Programmer1");
		user.setSex("male1");
		user.setAge("301");
		user.setCity("SJC1");
		userRepository.save(user);
	}
 
	@Test
	public void testFilesListing() throws JsonProcessingException{
		  RestTemplate restTemplate = new TestRestTemplate();
		  String[] forNow = restTemplate.getForObject("http://localhost:8080/global?directory=c:\\users", String[].class);
		  List<String> userList= Arrays.asList(forNow);
		  assertEquals(7, userList.size());
	}
	
	@Test
	public void healthCheck() throws JsonProcessingException{
		  RestTemplate restTemplate = new TestRestTemplate();
		  Map forNow = restTemplate.getForObject("http://localhost:8080/health", Map.class);
		  assertEquals(4, forNow.size());
	}
	
	@Test
	public void testFirstName() throws JsonProcessingException{
		  RestTemplate restTemplate = new TestRestTemplate();
		  User[] forNow = restTemplate.getForObject("http://localhost:8080/user?firstname=Saravana", User[].class);
		  List<User> userList= Arrays.asList(forNow);
		  assertEquals(2, userList.size());
	}
	
	@Test
	public void testFirstNameAndLastName() throws JsonProcessingException{
		  RestTemplate restTemplate = new TestRestTemplate();
		  User[] forNow = restTemplate.getForObject("http://localhost:8080/user?firstname=Saravana&lastname=Prabhu", User[].class);
		  List<User> userList= Arrays.asList(forNow);
		  assertEquals(1, userList.size());
	}
	
	@Test
	public void testFirstNameAndLastNameAndAge() throws JsonProcessingException{
		  RestTemplate restTemplate = new TestRestTemplate();
		  User[] forNow = restTemplate.getForObject("http://localhost:8080/user?firstname=Saravana&lastname=Prabhu&age=30", User[].class);
		  List<User> userList= Arrays.asList(forNow);
		  assertEquals(1, userList.size());
	}
	
	@Test
	public void testFirstNameAndLastNameAndAgeAndSex() throws JsonProcessingException{
		  RestTemplate restTemplate = new TestRestTemplate();
		  User[] forNow = restTemplate.getForObject("http://localhost:8080/user?firstname=Saravana&lastname=Prabhu&age=30&sex=male", User[].class);
		  List<User> userList= Arrays.asList(forNow);
		  assertEquals(1, userList.size());
	}
	
	@Test
	public void testFirstNameAndLastNameAndAgeAndSexAndCity() throws JsonProcessingException{
		  RestTemplate restTemplate = new TestRestTemplate();
		  User[] forNow = restTemplate.getForObject("http://localhost:8080/user?firstname=Saravana&lastname=Prabhu&age=30&sex=male&city=SJC", User[].class);
		  List<User> userList= Arrays.asList(forNow);
		  assertEquals(1, userList.size());
	}
	
	@Test
	public void testLoginAuthentication() throws JsonProcessingException{
		  List<User> userList= userRepository.findByUserNameAndPassword("sakaruna", "sak");
		  assertEquals(1, userList.size());
	}
}	