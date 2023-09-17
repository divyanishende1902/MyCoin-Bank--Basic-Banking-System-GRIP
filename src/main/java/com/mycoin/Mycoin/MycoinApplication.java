package com.mycoin.Mycoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages = "com.mycoin.Mycoin")

public class MycoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycoinApplication.class, args);
	}

}
