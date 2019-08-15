package com.dbexport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yuntian 317526763@qq.com
 */
@EnableConfigurationProperties
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
