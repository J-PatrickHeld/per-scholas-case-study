package com.jamesheld.oboestore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@GetMapping("/contact")
	public String showContactForm() {
		return "contact";
	}
	
	@PostMapping("/contact")
	public String submitContact(HttpServletRequest request) {
		String fullName = request.getParameter("contact_name");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		
		String mailSubject = fullName + " has sent a message";
		String mailContent = "Sender Name: " + fullName + "\n"
							+ "Sender Email: " + email + "\n"
							+ "Content: " + content;
		
		// could use MimeMessage and MimeMessageHelper here to
		// create HTML message and/or send attachments,
		// but that functionality is not needed.
		// if we do, it throws MessagingExcetion
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("abby.oboe.reeds@gmail.com"); //for MimeMessage, set second param (String) to custom name of sender
		message.setTo("abby.oboe.reeds@gmail.com");
		message.setSubject(mailSubject);
		message.setText(mailContent); //for MimeMessage set second param to true (allows html)
		
		javaMailSender.send(message);
		
		return "redirect:/contact?success";
	}
	
}
