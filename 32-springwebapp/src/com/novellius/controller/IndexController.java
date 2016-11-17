package com.novellius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
//@SessionAttributes({"resultado","mensaje"})
@SessionAttributes("resultado")
public class IndexController {

	@RequestMapping("/")
	public String showIndex(Model model) {
		model.addAttribute("resultado", "Resultado desde Session");
		return "index";
	}
	
	@RequestMapping("/about")
	public String showAbout(SessionStatus sessionStatus) throws Exception {
		sessionStatus.setComplete();
		
		if(true){
			throw new Exception();
		}
		
		return "about";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		return "/";
	}
	
}
