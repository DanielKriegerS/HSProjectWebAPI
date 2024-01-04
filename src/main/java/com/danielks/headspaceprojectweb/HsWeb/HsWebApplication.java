package com.danielks.headspaceprojectweb.HsWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class HsWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(HsWebApplication.class, args);
	}

}
