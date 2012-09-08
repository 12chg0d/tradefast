package org.fasttrading.server;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fasttrading.server.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login( Model model) {
		return "login";
	}
	@RequestMapping(value = "/loginbean", method = RequestMethod.POST)
	public String loginbean(@RequestParam("userName") String userName,@RequestParam("password") String password,Model model) {
		model.addAttribute("userName", userName);
		model.addAttribute("password", password);
		return "loginbean";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register( Model model) {
		return "register";
	}
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome( Model model) {
		return "welcome";
	}
	@RequestMapping(value = "/getprofile", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody Member getProfile() {
	    	return new Member("Ranatchai","Chernbamrung","2/427","086-782-8889","12chg0d@gmail.com");
	}
}
