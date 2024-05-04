package com.software.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Maincontroller {

	@GetMapping("/api")
	public String index() {
		return "Hi, it is the home page";
	}
	
	@GetMapping("/")
	public String ldapindex() {
		return "Hi, it is the ldap home page";
	}
}
