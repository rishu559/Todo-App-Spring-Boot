package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Web Request Handler
public class SayHelloController {
	
	@RequestMapping("/say-hello")
	@ResponseBody // To return the string as it is
	public String sayHello() {
		return "Hello! Rishu";
	}
	
	@RequestMapping("/say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>");
		sb.append("My First Html Page");
		sb.append("</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("Welcome to Spring Boot !!! (By Html");
		sb.append("</body>");
		sb.append("<html>");
		
		return sb.toString();
	}
	
	// 1. JSP is popular view tehnology
	// 2. We add tomcat-embed-jasper jar to view jsp's
	// 3. Path : src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	// 4. Add prefix and suffix in application.properties
	@RequestMapping("/say-hello-jsp")
	public String sayHelloJSP() {
		return "sayHello";
	}
}

