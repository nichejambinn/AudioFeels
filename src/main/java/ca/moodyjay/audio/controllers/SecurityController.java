package ca.moodyjay.audio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/login")
	public String goLogin() {
		return "login.html";
	}
	
	@GetMapping("/access-denied")
	public String goAccessDenied() {
		return "accessdenied.html";
	}
}
