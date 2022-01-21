package com.jamesheld.oboestore.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
		
    @GetMapping("/login")
    public String showLoginForm(Model model) {
    	// prevent logged in user from accessing login page again (prevent double login)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
 
        return "redirect:/";
    }
	
	@GetMapping("/user")
	public String userIndex() {
		return "user/index";
	}

}
