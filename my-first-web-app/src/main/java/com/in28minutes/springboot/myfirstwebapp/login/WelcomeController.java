package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
//	to make the method handle only GET request
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name",getLoggedinUsername());
		return "welcomePage";
	}
	
	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
//	@Autowired
//	private AuthenticationService authenticator;
//	
//	@RequestMapping(value="/login-jsp",method=RequestMethod.POST)
//	public String welcome(@RequestParam String name,@RequestParam String password,ModelMap model) {
//		
//		
//		if(authenticator.authenticate(name, password)) {
//			model.put("name",name);
//			return "welcomePage";
//		}
//		model.put("errorMessage", "Invalid Credentials!");
//		return "loginPage";
//	}
	
//	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	@RequestMapping("/login-jsp")
//	public String login(@RequestParam String name,ModelMap model) {
//		model.put("name",name);
//		System.out.println("Request Param is "+name); NOT RECOMMENDED FOR PRODUCTION CODE
//
//		logger.info("Request Param is {} and {}",name);
//		
//		return "loginPage";
//	}
}
