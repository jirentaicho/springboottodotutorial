package com.volkruss.mytodo.app.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@GetMapping("/")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.",locale);
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(
				DateFormat.LONG,DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(new Date());
		model.addAttribute("serverTime", formattedDate);
		
		return "welcome/home";
	}

}
