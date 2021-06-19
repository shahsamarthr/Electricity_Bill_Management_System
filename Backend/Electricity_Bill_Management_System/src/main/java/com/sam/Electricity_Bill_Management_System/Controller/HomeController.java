package com.sam.Electricity_Bill_Management_System.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return("<h1> Welcome</h1>");
	}
}
