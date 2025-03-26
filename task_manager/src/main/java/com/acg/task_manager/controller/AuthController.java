package com.acg.task_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/auth/login")
	public String loginPage() {
		return "forward:/auth/login.html";
	}

	@GetMapping("/auth/register")
	public String registerPage() {
		return "forward:/auth/register.html";
	}
}
