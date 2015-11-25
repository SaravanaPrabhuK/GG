package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.UserRepository;

@Controller
@RequestMapping(value="/log")
public class loginController {
	
	@Autowired
	private UserRepository userrepository;
	Log log = LogFactory.getLog(this.getClass());
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody boolean validateUserName(HttpServletRequest request, @RequestBody Map<String,String> userDetails) {
		boolean isSuccess = false;
		try {
			log.info("UserName:"+userDetails.get("uname")+"Password:"+userDetails.get("password")+"ListSize:"+userrepository.findByUserNameAndPassword(userDetails.get("uname"), userDetails.get("password")).size());
			List<User> userList = userrepository.findByUserNameAndPassword(userDetails.get("uname"), userDetails.get("password"));
			isSuccess = (userList.size() > 0);
			log.info(isSuccess);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;		
	}	

}
