package com.pomzwj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaowenjie<1513041820@qq.com>
 */
@SpringBootApplication
@Controller
public class DatabaseExportApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseExportApplication.class, args);
	}

	@GetMapping("/")
	String hello(){
		return "redirect:web/index.html";
	}
}
