package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	//http://localhost:8080/blog/temp/home
	@GetMapping("/temp/jsp")
	public String tempHome() {
		return "index";
	}
}
