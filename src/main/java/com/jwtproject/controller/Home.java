package com.jwtproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	
	
	//for testing purpose only
	@RequestMapping("/test")
	public String welcome() {
		return "this is private page this page is not allowed to unauthenticated users"; 
	}
}
