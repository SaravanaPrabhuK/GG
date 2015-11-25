package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dao.UserDaoImpl;


import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List sayHello(@RequestParam(value="firstname", required=false, defaultValue="") String firstName,
    								   @RequestParam(value="lastname", required=false, defaultValue="") String lastName,
    								   @RequestParam(value="profession", required=false, defaultValue="") String profession,
    								   @RequestParam(value="age", required=false, defaultValue="") String age,    
    								   @RequestParam(value="sex", required=false, defaultValue="") String sex,
    								   @RequestParam(value="city", required=false, defaultValue="") String city) {
    	
		/*UserCrud theUserCrud = new UserCrud();
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+firstName+lastName+profession+age+sex+city);
		//Setter for filter fields
		User theUser = new User();
		theUser.setFirstName(firstName);
		theUser.setLastName(lastName);
		theUser.setProfession(profession);
		theUser.setAge(age);
		theUser.setSex(sex);
		theUser.setCity(city);*/
		
		//Search invocation
		UserDaoImpl theUserDao = new UserDaoImpl();
		User theUser = new User();
		theUser.setFirstName(firstName);
		theUser.setLastName(lastName);
		theUser.setProfession(profession);
		theUser.setAge(age);
		theUser.setSex(sex);
		theUser.setCity(city);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+firstName+lastName+profession+age+sex+city);
		List<User> theUserData = theUserDao.userDataDisplay(theUser);
		return theUserData;
    }	
}