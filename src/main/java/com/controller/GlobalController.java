package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
@RequestMapping("/global")
public class GlobalController {

	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List sayHello(@RequestParam(value="directory", required=false, defaultValue="c:/") String directory) {
        List fileList = new ArrayList();
		try {
	    String readLine = "";
		Process p1 = Runtime.getRuntime().exec("ls "+directory);
        p1.waitFor();	
        BufferedReader processOutput = new BufferedReader(new InputStreamReader(p1.getInputStream()));
		while ((readLine = processOutput.readLine()) != null){
			fileList.add(readLine);
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return fileList;
 }
}